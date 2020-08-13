package com.shane.seckilldemo.service;

import com.shane.seckilldemo.dao.ProductDao;
import com.shane.seckilldemo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;


    @Transactional
    public Product getById(Long productId){
        return productDao.selectById(productId);
    }

    @Transactional
    public List<Product> getAll(){
        return productDao.selectAll();
    }

    @Transactional
    public int deductProductStock(Long productId){
        return productDao.updateStockById(productId);
    }


}
