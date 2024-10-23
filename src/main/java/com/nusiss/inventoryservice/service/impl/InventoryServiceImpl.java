package com.nusiss.inventoryservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nusiss.commonservice.feign.UserFeignClient;
import com.nusiss.inventoryservice.config.RabbitConfig;
import com.nusiss.inventoryservice.domain.dto.InventoryMessage;
import com.nusiss.inventoryservice.domain.entity.Inventory;
import com.nusiss.inventoryservice.mapper.InventoryMapper;

import com.nusiss.inventoryservice.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.nusiss.commonservice.entity.User;
import com.nusiss.commonservice.config.ApiResponse;
import org.springframework.util.CollectionUtils;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@Service
@Slf4j
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;
    @Autowired
    private UserFeignClient userClient;
    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * save
     * @param productId
     * @param availableStock
     */
    @Override
    public void save(String authToken, Long productId, int availableStock) {
        Inventory inventory = new Inventory();
        inventory.setProductId(productId);
        inventory.setAvailableStock(availableStock);
        inventory.setCreateUser(queryCurrentUser(authToken) );
        inventory.setUpdateUser(queryCurrentUser(authToken) );
        inventory.setCreateDatetime(Timestamp.valueOf(LocalDateTime.now()));
        inventory.setUpdateDatetime(Timestamp.valueOf(LocalDateTime.now()));

        inventoryMapper.insert(inventory);
    }

    /**
     * query by productId
     * @param productId
     * @return
     */
    @Override
    public int query(Long productId) {
        QueryWrapper<Inventory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        Inventory inventory = this.getOne(queryWrapper);
        if(inventory != null) {
            return inventory.getAvailableStock();
        }
        return 0;
    }

    /**
     * delete
     * @param productId
     */
    @Override
    public void delete(Long productId) {
        QueryWrapper<Inventory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        inventoryMapper.delete(queryWrapper);
    }

    /**
     * update
     * @param productId
     * @param availableStock
     */
    @Override
    public void update(String authToken, Long productId, int availableStock) {
        UpdateWrapper<Inventory> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("product_id", productId);
        Inventory inventory = new Inventory();
        inventory.setAvailableStock(availableStock);
        inventory.setCreateUser(queryCurrentUser(authToken) );
        inventory.setUpdateUser(queryCurrentUser(authToken) );
        inventory.setUpdateDatetime(Timestamp.valueOf(LocalDateTime.now()));
        inventoryMapper.update(inventory, updateWrapper);
    }

    /**
     * check if the stock number is available for order
     * @param productId
     * @param num
     * @return
     */
    @Override
    public Boolean checkStock(Long productId, int num) {
        int availableStock = query(productId);
        return availableStock >= num;
    }

    /**
     * deductStock after order made
     * @param productId
     * @param num
     * @return
     */
    @Override
    public Boolean deductStock(Long productId, Integer num) {
        int availableStock = query(productId);
        if(availableStock >= num) {
            UpdateWrapper<Inventory> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("product_id", productId);
            Inventory inventory = new Inventory();
            inventory.setAvailableStock(availableStock - num);
            inventoryMapper.update(inventory, updateWrapper);
            return true;
        }
        return false;
    }

    /**
     * add num stock for product
     * @param productId
     * @param num
     * @return
     */
    @Override
    public Boolean addStock(Long productId, Integer num) {
        int availableStock = query(productId);
        UpdateWrapper<Inventory> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("product_id", productId);
        Inventory inventory = new Inventory();
        inventory.setAvailableStock(availableStock + num);
        return inventoryMapper.update(inventory, updateWrapper) > 0;
    }

    /**
     * get user
     * @param authToken
     * @return
     */
    public String queryCurrentUser(String authToken) {
        ResponseEntity<ApiResponse<User>> res = userClient.getCurrentUserInfo(authToken);
        // Check the response status code
        if (res.getStatusCode() == HttpStatus.OK) {
            // Get the ApiResponse object
            ApiResponse<User> apiResponse = res.getBody();

            // Check if apiResponse is not null and extract the User object
            if (apiResponse != null) {
                User user = apiResponse.getData();
                return user.getUsername();
            }
            return "system";
        }
        return "system";
    }


    /**
     * notification
     * @param
     */
    @RabbitListener(queues = RabbitConfig.INVENTORY_QUEUE)
    public void handleOrderMessage(InventoryMessage inventoryMessage) {

        // Long productId = Long.valueOf((String) param.get(0));
        // Integer num = Integer.valueOf((String) param.get(1));
        //
        // if (checkStock(productId, num)) {
        //
        //     deductStock(productId, num);
        //
        //     rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, "confirm.order", true);
        // } else {
        //
        //     rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, "rollback.order", false);
        // }
        Long productId = inventoryMessage.getProductId();
        Integer num = inventoryMessage.getQuantity();
        if(checkStock(productId, num)){
            deductStock(productId, num);
            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, "confirm.success", inventoryMessage);
            System.out.println("发送确认消息: " + inventoryMessage);
        }else {
            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, "rollback.failure", inventoryMessage);
            System.out.println("发送回滚消息: " + inventoryMessage);
        }

    }

}
