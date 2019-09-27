package com.su.hadoop;

import com.su.hadoop.bean.WebLogBean;
import com.su.hadoop.util.WebLogParser;

import java.util.HashSet;
import java.util.Set;

public class test {
    public static void main(String[] args) {
        String line = "192.168.14.3 - - [24/Sep/2019:22:56:48 -0400] \"GET /demo-1.0/a HTTP/1.1\" 200 115 \"-\" \"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36\"\n";
        WebLogBean webLogBean = WebLogParser.parser(line);

        Set<String> pages = new HashSet<>();
        pages.add("/a");
        pages.add("/b");
        pages.add("/c");

        WebLogParser.filtStaticResource(webLogBean, pages);
        System.out.println(webLogBean.toString());
    }
}
