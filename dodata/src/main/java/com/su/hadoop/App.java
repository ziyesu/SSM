package com.su.hadoop;

import com.su.hadoop.Mapper.LogMapper;
import com.su.hadoop.Reducer.LogReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {

        Configuration conf = new Configuration();
        Job wcjob = Job.getInstance(conf);
        //指定我这个job所在的jar包
        //wcjob.setJar("/home/hadoop/wordcount.jar");
        wcjob.setJarByClass(App.class);


        wcjob.setMapperClass(LogMapper.class);
        wcjob.setReducerClass(LogReducer.class);

        //设置我们的业务逻辑Mapper类的输出key和value的数据类型
        wcjob.setMapOutputKeyClass(Text.class);
        wcjob.setMapOutputValueClass(IntWritable.class);

//        wcjob.setNumReduceTasks(0);
//
//        //设置我们的业务逻辑Reducer类的输出key和value的数据类型
//        wcjob.setOutputKeyClass(Text.class);
//        wcjob.setOutputValueClass(IntWritable.class);

        //指定要处理的数据所在的位置
        FileInputFormat.setInputPaths(wcjob, "hdfs://hadoop-001:9000/weblog/flume-collection");
        //指定处理完成之后的结果所保存的位置
        FileOutputFormat.setOutputPath(wcjob, new Path("hdfs://hadoop-001:9000/output3"));


        //向yarn集群提交这个job
        boolean res = false;
        try {
            res = wcjob.waitForCompletion(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.exit(res ? 0 : 1);

    }
}
