package com.gec.zshop.pojo;

import java.io.Serializable;

/**
 * Date：2018-04-26 11:41
 * Description：<描述>
 */
public class ProductType extends PageModel implements Serializable{

    private Integer id;
    private String name;
    private Integer status;

    @Override
    public String toString() {
        return "ProductType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
