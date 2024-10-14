package com.nusiss.inventoryservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nusiss.inventoryservice.client.UserClient;
import com.nusiss.inventoryservice.domain.entity.Inventory;
import com.nusiss.inventoryservice.mapper.InventoryMapper;

import com.nusiss.inventoryservice.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;
    @Autowired
    private UserClient userClient;


    /**
     * save
     * @param productId
     * @param availableStock
     */
    @Override
    public void save(Long productId, int availableStock) {
        Inventory inventory = new Inventory();
        inventory.setProductId(productId);
        inventory.setAvailableStock(availableStock);
        inventory.setCreateUser(userClient.queryCurrentUser());
        inventory.setUpdateUser(userClient.queryCurrentUser());

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
        remove(queryWrapper);
    }

    /**
     * update
     * @param productId
     * @param availableStock
     */
    @Override
    public void update(Long productId, int availableStock) {
        UpdateWrapper<Inventory> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("product_id", productId);
        Inventory inventory = new Inventory();
        inventory.setAvailableStock(availableStock);
        update(inventory, updateWrapper);
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
            update(productId, availableStock - num);
            return true;
        }
        return false;
    }
}
