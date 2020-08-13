package com.shane.seckilldemo;

import com.shane.seckilldemo.dao.ProductDao;
import com.shane.seckilldemo.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class SeckilldemoApplicationTests {

    @Autowired
    DataSource dataSource;


    @Autowired
    ProductDao productDao;
    @Test
    void contextLoads() throws SQLException {

        System.out.println(dataSource.getClass());
        System.out.println(dataSource.getConnection());
    }


    @Test
    void test1(){
        List<Product> products = productDao.selectAll();
        for (Product product : products) {
            System.out.println(product);
        }
    }

}
