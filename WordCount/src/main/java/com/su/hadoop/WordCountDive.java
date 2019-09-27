package com.su.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

//调度类，驱动类
public class WordCountDive {
    //提供一个调度类来确认作业的调度过程
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //定义一个全局配置类
        Configuration configuration = new Configuration();
        //获取一个作业类
        Job job = Job.getInstance(configuration);

        //指定驱动类：
        job.setJarByClass(WordCountDive.class);
        //指定本次调度的mapper类和reducer类
        job.setMapperClass(WordCountMap.class);
        job.setReducerClass(WordCountReduce.class);

        //指定结果的输出的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        //指定文件的来源和结果的输出
        FileInputFormat.setInputPaths(job,"hdfs://hadoop-001:9000/weblog/flume-collection/access_log.1569380308822");
        FileOutputFormat.setOutputPath(job,new Path("hdfs://hadoop-001:9000/output3"));

        //提交作业,并且打印监控记录
        boolean ret = job.waitForCompletion(true);
        System.exit(ret?0:1);
    }
}
