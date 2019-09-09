package com.su.zshop.common;

public class util {
    public static String selectMapper(String path){
        StringBuffer root=new StringBuffer("com.su.mapper.");
        return root.append(path).toString();
    }
}
