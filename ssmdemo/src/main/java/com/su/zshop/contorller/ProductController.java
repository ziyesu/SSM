package com.su.zshop.contorller;

import com.su.zshop.pojo.Product;
import com.su.zshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;



    @RequestMapping("")
    public String product(Product product, Model model){
        List<Product> productList= productService.queryProduct(product);

        //将数据对象存储到request作用域
        model.addAttribute("productTypeList",productList);
        return "product";
    }
//
//    @RequestMapping("/find")
//    public String find(ProductType productType, Model model){
//        List<ProductType> productTypeList=productTypeService.queryProductType(productType);
//
//        //将数据对象存储到request作用域
//        model.addAttribute("productTypeList",productTypeList);
//        return "product";
//    }


//    @RequestMapping("/delete")
//    public String delete(ProductType productType, Model model){
//        List<ProductType> productTypeList=productTypeService.queryProductType(productType);
//
//        //将数据对象存储到request作用域
//        model.addAttribute("productTypeList",productTypeList);
//        return "product";
//    }
//
//    @RequestMapping("/update")
//    public String update(ProductType productType, Model model){
//        List<ProductType> productTypeList=productTypeService.queryProductType(productType);
//
//        //将数据对象存储到request作用域
//        model.addAttribute("productTypeList",productTypeList);
//        return "product";
//    }
//
//    @RequestMapping("/add")
//    public String add(ProductType productType, Model model){
//        List<ProductType> productTypeList=productTypeService.queryProductType(productType);
//
//        //将数据对象存储到request作用域
//        model.addAttribute("productTypeList",productTypeList);
//        return "product";
//    }





}
