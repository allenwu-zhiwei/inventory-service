package com.nusiss.inventoryservice.controller;

import com.nusiss.inventoryservice.service.InventoryService;
import com.nusiss.inventoryservice.config.ApiResponse;
import com.nusiss.inventoryservice.domain.entity.Inventory;
import com.nusiss.commonservice.feign.UserFeignClient;
import com.nusiss.commonservice.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class InventoryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private InventoryService inventoryService;

    @InjectMocks
    private InventoryController inventoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(inventoryController).build();
    }

    @Test
    void testSaveInventory() throws Exception {
        String authToken = "validAuthToken";
        Long productId = 1L;
        int availableStock = 100;

        // Perform the POST request
        mockMvc.perform(post("/inventory")
                        .header("authToken", authToken)
                        .param("productId", String.valueOf(productId))
                        .param("availableStock", String.valueOf(availableStock))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verify that save method was called
        verify(inventoryService, times(1)).save(authToken, productId, availableStock);
    }

    @Test
    void testQueryInventory() throws Exception {
        Long productId = 1L;
        int availableStock = 100;

        // Mock the response from InventoryService
        when(inventoryService.query(productId)).thenReturn(availableStock);

        // Perform the GET request
        mockMvc.perform(get("/inventory")
                        .param("productId", String.valueOf(productId)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(availableStock)));

        // Verify the query method was called
        verify(inventoryService, times(1)).query(productId);
    }

    @Test
    void testDeleteInventory() throws Exception {
        Long productId = 1L;

        // Perform the DELETE request
        mockMvc.perform(delete("/inventory")
                        .param("productId", String.valueOf(productId)))
                .andExpect(status().isOk());

        // Verify that delete method was called
        verify(inventoryService, times(1)).delete(productId);
    }

    @Test
    void testUpdateInventory() throws Exception {
        String authToken = "validAuthToken";
        Long productId = 1L;
        int availableStock = 100;

        // Perform the PUT request
        mockMvc.perform(put("/inventory")
                        .header("authToken", authToken)
                        .param("productId", String.valueOf(productId))
                        .param("availableStock", String.valueOf(availableStock))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verify that update method was called
        verify(inventoryService, times(1)).update(authToken, productId, availableStock);
    }

    @Test
    void testDeductStock() throws Exception {
        Long productId = 1L;
        int num = 50;

        // Mock the deductStock method
        when(inventoryService.deductStock(productId, num)).thenReturn(true);

        // Perform the PUT request for deducting stock
        mockMvc.perform(put("/inventory/deduct/{productId}/{num}", productId, num))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        // Verify that deductStock method was called
        verify(inventoryService, times(1)).deductStock(productId, num);
    }

    @Test
    void testCheckStock() throws Exception {
        Long productId = 1L;
        int num = 50;

        // Mock the checkStock method
        when(inventoryService.checkStock(productId, num)).thenReturn(true);

        // Perform the GET request for checking stock
        mockMvc.perform(get("/inventory/check/{productId}/{num}", productId, num))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        // Verify that checkStock method was called
        verify(inventoryService, times(1)).checkStock(productId, num);
    }

    @Test
    void testCompensateStock() throws Exception {
        Long productId = 1L;
        int num = 50;

        // Mock the addStock method
        when(inventoryService.addStock(productId, num)).thenReturn(true);

        // Perform the PUT request to compensate stock
        mockMvc.perform(put("/inventory/compensate")
                        .param("productId", String.valueOf(productId))
                        .param("num", String.valueOf(num)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        // Verify that addStock method was called
        verify(inventoryService, times(1)).addStock(productId, num);
    }
}
