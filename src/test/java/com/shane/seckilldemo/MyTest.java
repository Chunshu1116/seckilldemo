package com.shane.seckilldemo;

import com.shane.seckilldemo.dao.OrderDao;
import com.shane.seckilldemo.dao.ProductDao;
import com.shane.seckilldemo.entity.Product;
import com.shane.seckilldemo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MyTest {

    @Autowired
    ProductDao productDao;

    @Autowired
    OrderDao orderDao;

    @Autowired
    private ProductService productService;
    @Test
    public void testOrderDao(){
//        Product product = productDao.selectById(1L);
//        System.out.println(product);
//        List<Product> products = productDao.selectAll();
//        for (Product product : products) {
//            System.out.println(product);
//        }
//        productDao.updateStockById(1L);
//        orderDao.insertOrder(1L);

        List<Product> products = productService.getAll();
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
