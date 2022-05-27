package com.engeto.lekce11;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;

public interface GoodsMapper {
    Item loadItemById(Integer id);
    void deleteAllOutOfStockItems();
    List<Item> loadAllAvailableItems();
    void saveItem(Item item);
    void updatePrice(Integer id, BigDecimal newPrice);
}
