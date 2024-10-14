package com.nusiss.inventoryservice.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nusiss.inventoryservice.domain.entity.Inventory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InventoryMapper extends BaseMapper<Inventory> {

}
