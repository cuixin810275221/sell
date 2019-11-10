package com.imooc.service.impl;


import com.imooc.dataobject.ProductInfo;
import com.imooc.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {
    @Autowired
    private ProductInfoServiceImpl productInfoServiceImpl;

    @Test
    public void findOne() {
        ProductInfo productInfo = productInfoServiceImpl.findOne("123456");
        Assert.assertEquals(productInfo.getProductId(),"123456");
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = productInfoServiceImpl.findUpAll();
        Assert.assertNotEquals(0,productInfoList.size());
    }

    @Test
    public void findAll() {
        Pageable pageable = new PageRequest(0,2);
        Page<ProductInfo> productInfoPage = productInfoServiceImpl.findAll(pageable);
        Assert.assertNotEquals(0,productInfoPage.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1234567");
        productInfo.setProductName("鸡蛋");
        productInfo.setProductPrice(BigDecimal.valueOf(1.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好吃的鸡蛋");
        productInfo.setProductIcon("http://123xx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfo.setCategoryType(2);

        ProductInfo productInfo1 = productInfoServiceImpl.save(productInfo);
        Assert.assertEquals(productInfo1.getProductId(),"1234567");
    }
}
