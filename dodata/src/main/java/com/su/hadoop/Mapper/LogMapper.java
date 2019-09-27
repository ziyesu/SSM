package com.su.hadoop.Mapper;

import com.su.hadoop.util.WebLogParser;
import com.su.hadoop.bean.WebLogBean;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class LogMapper extends Mapper<LongWritable, Text,Text, IntWritable>
{
    // 用来存储网站url分类数据
    Set<String> pages = new HashSet<>();
    Text k = new Text();
//    NullWritable v = NullWritable.get();

    /**
     * 从外部加载网站url分类数据
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        pages.add("/a");
        pages.add("/b");
        pages.add("/c");

    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        WebLogBean webLogBean = WebLogParser.parser(line);

        if(webLogBean!=null){
            // 过滤js/图片/css等静态资源 , 即数据清洗
            WebLogParser.filtStaticResource(webLogBean, pages);
            //如果是标记为无效的数据，就不输出
            if (webLogBean.isValid()) {

                k.set(webLogBean.getRequest());
                context.write(k, new IntWritable(1));
            }
        }

    }
}
