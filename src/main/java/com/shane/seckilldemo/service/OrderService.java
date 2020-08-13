package com.shane.seckilldemo.service;

import com.shane.seckilldemo.dao.OrderDao;
import com.shane.seckilldemo.dao.ProductDao;
import com.shane.seckilldemo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductService productService;

    @Transactional
    public void addOrder(Long productId){
        orderDao.insertOrder(productId);
    }

    @Transactional
    public void seckill(Long productId){
        Product product = productService.getById(productId);
        if(product.getStock()<=0){
            throw new RuntimeException("商品已售罄");
        }
        //创建秒杀订单
        addOrder(productId);

        //减库存
        int i = productService.deductProductStock(productId);
        if(i<=0){
            throw new RuntimeException("商品已售罄");
        }


    }
}
