package com.shane.seckilldemo.dao;

import com.shane.seckilldemo.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Mapper
public interface ProductDao {

    @Update(value = "update product set stock=stock-1 where product_id=#{productId} and stock>0")
    int updateStockById(@Param("productId") Long productId);


    @Select("select * from product where product_id = #{productId}")
    Product selectById(@Param("productId") Long productId);

    @Select("select * from product")
    List<Product> selectAll();

}
