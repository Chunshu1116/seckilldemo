package com.shane.seckilldemo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Mapper
public interface OrderDao {

//    @Insert("insert into seckill_order (order_id,product_id) values (#{orderId},#{productId})")
//    int insertOrder(@Param("orderId") Long orderId,@Param("productId") Long productId);

    @Insert("insert into seckill_order (product_id) values (#{productId})")
    int insertOrder(@Param("productId") Long productId);


}
