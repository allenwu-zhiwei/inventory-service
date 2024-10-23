package com.nusiss.inventoryservice.domain.dto;

import lombok.Data;

@Data
public class InventoryMessage {
    private Long productId;
    private int quantity;
}
