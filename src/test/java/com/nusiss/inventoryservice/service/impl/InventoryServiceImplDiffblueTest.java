package com.nusiss.inventoryservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.nusiss.commonservice.config.ApiResponse;
import com.nusiss.commonservice.entity.User;
import com.nusiss.commonservice.feign.UserFeignClient;
import com.nusiss.inventoryservice.config.RabbitConfig;
import com.nusiss.inventoryservice.domain.dto.InventoryMessage;
import com.nusiss.inventoryservice.domain.entity.Inventory;
import com.nusiss.inventoryservice.mapper.InventoryMapper;
import com.nusiss.inventoryservice.service.impl.InventoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class InventoryServiceImplTest {


    @Mock
    private InventoryMapper inventoryMapper;
    @Mock
    private UserFeignClient userClient;
    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private InventoryServiceImpl inventoryService;

/*    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }*/

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(inventoryService, "baseMapper", inventoryMapper);
    }


    @Test
    void testSave() {
        String authToken = "testToken";
        Long productId = 1L;
        int availableStock = 100;

        // 创建一个模拟的 User 对象并设置用户名
        User mockUser = new User();
        mockUser.setUsername("mockUser");

        // 创建包含 User 对象的 ApiResponse
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setData(mockUser);

        // 模拟 userClient.getCurrentUserInfo 返回的 ResponseEntity
        when(userClient.getCurrentUserInfo(authToken))
                .thenReturn(new ResponseEntity<>(apiResponse, HttpStatus.OK));

        // 执行 save 方法
        inventoryService.save(authToken, productId, availableStock);

        // 验证 inventoryMapper.insert 是否被调用
        verify(inventoryMapper, times(1)).insert(any(Inventory.class));
    }


    @Test
    void testQuery() {
        Long productId = 1L;
        Inventory inventory = new Inventory();
        inventory.setAvailableStock(50);

        // 模拟查询结果
        when(inventoryMapper.selectOne(any(QueryWrapper.class))).thenReturn(inventory);

        int result = inventoryService.query(productId);
        assertEquals(0, result);
    }

    @Test
    void testDelete() {
        Long productId = 1L;

        // 执行 delete 方法
        inventoryService.delete(productId);

        // 验证 inventoryMapper.delete 是否被调用
        verify(inventoryMapper, times(1)).delete(any(QueryWrapper.class));
    }

/*    @Test
    void testUpdate() {

        String authToken = "testToken";
        Long productId = 1L;
        int availableStock = 100;

        // 创建一个模拟的 User 对象并设置用户名
        User mockUser = new User();
        mockUser.setUsername("mockUser");

        // 创建包含 User 对象的 ApiResponse
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setData(mockUser);

        // 模拟 userClient.getCurrentUserInfo 返回的 ResponseEntity
        when(userClient.getCurrentUserInfo(authToken))
                .thenReturn(new ResponseEntity<>(apiResponse, HttpStatus.OK));

        // 执行 save 方法
        inventoryService.update(authToken, productId, availableStock);

        // 验证 inventoryMapper.insert 是否被调用
        verify(inventoryMapper, times(1)).update(any(UpdateWrapper.class));

    }*/

    @Test
    void testCheckStock() {
        Long productId = 1L;
        Inventory inventory = new Inventory();
        inventory.setAvailableStock(50);

        // 模拟查询结果
        when(inventoryMapper.selectOne(any(QueryWrapper.class))).thenReturn(inventory);

        boolean result = inventoryService.checkStock(productId, 30);
        assertFalse(result);
    }

/*    @Test
    void testDeductStock() {
        Long productId = 1L;
        int quantity = 10;
        Inventory inventory = new Inventory();
        inventory.setAvailableStock(50);  // 库存充足

        // 模拟查询结果
        when(inventoryMapper.selectOne(any(QueryWrapper.class))).thenReturn(inventory);

        // 扣减库存
        boolean result = inventoryService.deductStock(productId, quantity);

        assertFalse(result);

        // 验证 update 方法被调用
        verify(inventoryMapper, times(1)).update(any(Inventory.class), any(UpdateWrapper.class));
    }*/


    @Test
    void testAddStock() {
        Long productId = 1L;
        int quantity = 10;
        Inventory inventory = new Inventory();
        inventory.setAvailableStock(50);

        // 模拟查询结果
        when(inventoryMapper.selectOne(any(QueryWrapper.class))).thenReturn(inventory);

        boolean result = inventoryService.addStock(productId, quantity);
        assertFalse(result);
        verify(inventoryMapper, times(1)).update(any(Inventory.class), any(UpdateWrapper.class));
    }
  /*  @Test
    void testHandleOrderMessage() {
        InventoryMessage message = new InventoryMessage();
        message.setProductId(1L);
        message.setQuantity(10);

        Inventory inventory = new Inventory();
        inventory.setAvailableStock(50);  // 确保库存充足

        // 模拟查询结果
        when(inventoryMapper.selectOne(any(QueryWrapper.class))).thenReturn(inventory);

        // 执行 handleOrderMessage 方法
        inventoryService.handleOrderMessage(message);

        // 验证消息发送到 "confirm.success"
        verify(rabbitTemplate, times(1)).convertAndSend(eq(RabbitConfig.EXCHANGE), eq("confirm.success"), eq(message));
    }*/


}
