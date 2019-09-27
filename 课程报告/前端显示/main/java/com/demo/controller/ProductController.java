package com.demo.controller;

import com.demo.pojo.Product;
import com.demo.pojo.ProductType;
import com.demo.pojo.ResponseResult;
import com.demo.service.ProductService;
import com.demo.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping("/main")
    public String mainPage(Product product, Model model){

        List<Product> productList = productService.queryProduct(product);
        model.addAttribute("productList", productList);

        System.out.println(product.toString());

        ProductType productType = new ProductType();
        productType.setStatus(1);

        List<ProductType> productTypeList = productTypeService.queryProductType(productType);
        model.addAttribute("productTypes", productTypeList);
        return "index";
    }

    @RequestMapping("/findProduct")
    public String queryProduct(Product product, Model model) {
        List<Product> productList = productService.queryProduct(product);
        model.addAttribute("productList", productList);

        System.out.println(product.toString());

        ProductType productType = new ProductType();
        productType.setStatus(1);

        List<ProductType> productTypeList = productTypeService.queryProductType(productType);
        model.addAttribute("productTypes", productTypeList);
        return "index";
    }

    @RequestMapping("/hadoop")
    @ResponseBody
    public ResponseResult hadoop(String name){
        ResponseResult responseResult = new ResponseResult();

        System.out.println(name);

        if (name!=null) {
            responseResult.setStatus(1);
            responseResult.setMessage("访问成功");
        } else {
            responseResult.setStatus(0);
            responseResult.setMessage("访问失败");
        }

        return responseResult;

    }
}
