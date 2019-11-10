package com.imooc.Controller;

import com.imooc.Utils.ResultVOUtils;
import com.imooc.VO.ProductInfoVO;
import com.imooc.VO.ProductVO;
import com.imooc.VO.ResultVO;
import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductInfo;
import com.imooc.service.ProductCategoryService;
import com.imooc.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class ProductInfoController {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVO List(){

        //1.查询所有的上架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        //2.查询类目
        List<Integer> categoryList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> ProductCategoryLsit = productCategoryService.findByCategoryTypeIn(categoryList);
        //3.数据封装
        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory productCategory:ProductCategoryLsit){
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(productCategory,productVO);
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo :productInfoList){
                if(productInfo.getCategoryType().equals(productVO.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtils.success(productVOList);
    }
}
