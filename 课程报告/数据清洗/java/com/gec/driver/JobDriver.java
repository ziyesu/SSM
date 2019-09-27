package com.gec.driver;


import com.gec.bean.WebLogBean;
import com.gec.mapper.LogParSerPreMapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class JobDriver {
    public static void main(String[] args) throws IOException {
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        job.setJarByClass(JobDriver.class);

        job.setMapperClass(LogParSerPreMapper.class);

        job.setMapOutputKeyClass(WebLogBean.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setNumReduceTasks(0);

        //指定要处理的数据所在的位置
        FileInputFormat.setInputPaths(job, "hdfs://hadoop-001:9000/weblog/flume-collection");
        //指定处理完成之后的结果所保存的位置
        FileOutputFormat.setOutputPath(job, new Path("hdfs://hadoop-001:9000/output3"));
        //向yarn集群提交这个job
        boolean res = false;
        try {
            res = job.waitForCompletion(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.exit(res ? 0 : 1);
    }
}
