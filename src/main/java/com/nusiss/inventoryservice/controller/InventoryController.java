package com.nusiss.inventoryservice.controller;

import com.nusiss.inventoryservice.service.InventoryService;
import com.nusiss.inventoryservice.config.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@Tag(name = "product inventory", description = "These APIs provide operations related to inventory management.")
@Slf4j
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    /**
     * add inventory
     * @param productId
     * @param availableStock
     * @return
     */
    @PostMapping
    @Operation(summary = "add inventory")
    public ApiResponse<String> save(@RequestHeader("authToken") String authToken, Long productId, int availableStock){
        log.info("add inventory：productId{},stock{}", productId, availableStock);
        inventoryService.save(authToken, productId, availableStock);
        return ApiResponse.success();
    }

    /**
     * query
     * @param productId
     * @return
     */
    @GetMapping
    @Operation(summary = "query for stock by productId")
    public int query(Long productId){
        log.info("query productId{}", productId);
        int availableStock = inventoryService.query(productId);
        return availableStock;
    }

    /**
     * delete inventory
     * @param productId
     * @return
     */
    @DeleteMapping
    @Operation(summary = "delete inventory")
    public ApiResponse<String> deleteById(Long productId){
        log.info("delete productId{}", productId);
        inventoryService.delete(productId);
        return ApiResponse.success();
    }

    /**
     * update
     * @param productId
     * @param availableStock
     * @return
     */
    @PutMapping
    @Operation(summary = "update")
    public ApiResponse<String> update(@RequestHeader("authToken") String authToken, Long productId, int availableStock){
        log.info("update productId{}, availableStock{}", productId, availableStock);
        inventoryService.update(authToken, productId, availableStock);
        return ApiResponse.success();
    }

    @PutMapping("/deduct")
    @Operation(summary = "deduct stock after order success")
    public Boolean deductStock(Long productId, Integer num){
        log.info("deduct stock productId{}, num{}", productId, num);
        return inventoryService.deductStock(productId, num);
    }

    @GetMapping("/check")
    @Operation(summary = "check stock for order")
    public Boolean checkStock(Long productId, int num){
        log.info("check stock productId{}, num{}", productId, num);
        return inventoryService.checkStock(productId, num);
    }

}
