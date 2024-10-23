package com.nusiss.inventoryservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryMessage{
    private Long productId;
    private int quantity;
    private Long orderId;
}
