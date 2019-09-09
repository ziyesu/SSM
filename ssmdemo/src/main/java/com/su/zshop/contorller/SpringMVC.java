package com.su.zshop.contorller;

import com.su.zshop.pojo.ProductType;
import com.su.zshop.pojo.ResponseResult;
import com.su.zshop.service.ProductTypeService;
import com.su.zshop.service.impl.ProductTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class SpringMVC {

    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping("/findProductType")
    public String findProductType(ProductType productType, Model model)
    {
        List<ProductType> productTypeList=productTypeService.queryProductType(productType);

        //将数据对象存储到request作用域
        model.addAttribute("productTypeList",productTypeList);

        return "producttypelist";

    }

    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(ProductType productType)
    {
        ResponseResult responseResult=new ResponseResult();

        productType.setStatus(1);

        int result=productTypeService.insert(productType);

        if(result>0)
        {
            responseResult.setStatus(1);
            responseResult.setMessage("添加成功");
        }else {
            responseResult.setStatus(0);
            responseResult.setMessage("添加失败");
        }

        return responseResult;
    }

    @RequestMapping("/setStatus")
    @ResponseBody
    public ResponseResult setStatus(String id)
    {
        ResponseResult responseResult=new ResponseResult();

        ProductType productType=new ProductType();
        productType.setId(Integer.parseInt(id));

        System.out.println(id);

        List<ProductType> dts=productTypeService.queryProductType(productType);

        boolean result=false;
        int status=0;
        if(!dts.isEmpty()){
            // 反转状态
            productType = dts.get(0);
            status = dts.get(0).getStatus()==1?0:1;
            productType.setStatus(status);
            productTypeService.update(productType);

            System.out.println(productType.toString());
            result=true;
        }

        if(true)
        {
            responseResult.setStatus(1);
            StringBuffer mess=new StringBuffer(status==1?"禁用":"启用");
            mess.append("成功");
            responseResult.setMessage(mess.toString());
        }else {
            responseResult.setStatus(0);
            responseResult.setMessage("操作失败");
        }

        return responseResult;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResponseResult setDelete(String id)
    {
        ResponseResult responseResult=new ResponseResult();

        ProductType productType=new ProductType();
        productType.setId(Integer.parseInt(id));

        System.out.println(id);

        int dts=productTypeService.delete(productType);

        if(dts>0)
        {
            responseResult.setStatus(1);
            responseResult.setMessage("删除成功");
        }else {
            responseResult.setStatus(0);
            responseResult.setMessage("操作失败");
        }

        return responseResult;
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResponseResult setUpdate(String id,String name,String status)
    {
        ResponseResult responseResult=new ResponseResult();

        ProductType productType=new ProductType();

        productType.setId(Integer.parseInt(id));
        productType.setName(name);
        productType.setStatus(Integer.parseInt(status));

        System.out.println(productType.toString());

        int dts=productTypeService.update(productType);



        if(dts>0)
        {
            responseResult.setStatus(1);
            responseResult.setMessage("ok");
        }else {
            responseResult.setStatus(0);
            responseResult.setMessage("操作失败");
        }

        return responseResult;
    }


    @RequestMapping("/selectDelete")
    @ResponseBody
    public ResponseResult selectDelete(@RequestParam("ids[]") List<String> ids)
    {

        ResponseResult responseResult=new ResponseResult();
        System.out.println(ids);
//
//        ProductType productType=new ProductType();
//        ProductTypeService productTypeService=new ProductTypeServiceImpl();
//
//        productType.setId(Integer.parseInt(id));
//        productType.setName(name);
//        productType.setStatus(Integer.parseInt(status));
//
//        System.out.println(name);
//
//        int dts=productTypeService.update(productType);

        if(true)
        {
            responseResult.setStatus(1);
            responseResult.setMessage("ok");
        }else {
            responseResult.setStatus(0);
            responseResult.setMessage("操作失败");
        }

        return responseResult;
    }
}
