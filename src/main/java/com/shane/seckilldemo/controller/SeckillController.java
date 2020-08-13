package com.shane.seckilldemo.controller;

import com.shane.seckilldemo.common.CommonResult;
import com.shane.seckilldemo.entity.Product;
import com.shane.seckilldemo.service.OrderService;
import com.shane.seckilldemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class SeckillController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static ConcurrentHashMap<Long,Boolean> productSoldOutMap=new ConcurrentHashMap<>();

    public static ConcurrentHashMap<Long,Boolean> getProductSoldOutMap(){
        return productSoldOutMap;
    }

    @PostConstruct
    public void init(){
        List<Product> products = productService.getAll();
        for (Product product : products) {
            stringRedisTemplate.opsForValue().set("product_stock_"+product.getProductId(),product.getStock()+"");
        }
    }

    @PostMapping("/seckill/{productId}")
    public CommonResult seckill(@PathVariable("productId") Long productId){
        if(productSoldOutMap.get(productId)!=null){
            return CommonResult.error().message("商品已售罄");
        }
        Long stock = stringRedisTemplate.opsForValue().decrement("product_stock_" + productId);
        if (stock<0){
            productSoldOutMap.put(productId,true);
            stringRedisTemplate.opsForValue().increment("product_stock_" + productId);
            return CommonResult.error().message("商品已售罄");
        }
        try {
            orderService.seckill(productId);
        } catch (Exception e) {
            stringRedisTemplate.opsForValue().increment("product_stock_" + productId);
            if(productSoldOutMap.get(productId)!=null){
                productSoldOutMap.remove(productId);
            }
            e.printStackTrace();
            return CommonResult.error().message("秒杀失败");
        }
        return CommonResult.ok();
    }
}
