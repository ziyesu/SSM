package com.su.hadoop;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

// Iterable<Text> values  的数据其实就是以key为准的数据： 比如说hadoop     <hadoop,1>的所有键值对存放在values里面
//reduce阶段，注意要继承Reducer并且要重写reduce方法
//reduce阶段的输入其实就是map阶段的输出
public class WordCountReduce extends Reducer<Text,LongWritable ,Text,LongWritable> {
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        //统计单词的出现次数，定义count变量来统计次数
        int count = 0;
        //进行遍历和累加和
        for (LongWritable item:values ) {
            count +=item.get();
        }
        //将结果进行输出
        context.write(key,new LongWritable(count));
    }
}

