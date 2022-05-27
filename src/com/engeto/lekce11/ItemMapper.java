package com.engeto.lekce11;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;


public interface ItemMapper {
    @Select("SELECT id, part_no AS partNo, serial_no AS serialNo, name, description, number_in_stock AS numberInStock, price FROM engeto.item WHERE id = #{id}")
    Item loadItemById(Integer id);

    @Delete("DELETE FROM engeto.item WHERE number_in_stock=0")
    void deleteAllOutOfStockItems();

    @Select("SELECT id, part_no AS partNo, serial_no AS serialNo, name, description, number_in_stock AS numberInStock, price FROM engeto.item WHERE number_in_stock>0")
    List<Item> loadAllAvailableItems();

    @Insert("INSERT INTO engeto.item (part_no,serial_no,name,description,number_in_stock,price) VALUES (#{partNo},#{serialNo},#{name},#{description},#{numberInStock},#{price})")
    void saveItem(Item item);

    @Update("UPDATE engeto.item set price = #{price} WHERE id = #{id}")
    void updatePrice(Integer id, BigDecimal newPrice);
}


