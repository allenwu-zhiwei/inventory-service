package com.nusiss.inventoryservice.domain.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class CategoryDTO implements Serializable {

    private Long categoryId;

    private String categoryName;
}

