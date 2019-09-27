package com.su.hadoop;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


//主要有四个泛型  keyin,valuein  keyout,valueout
//keyin:表示mapper数据输入的时候的数据类型，默认情况下是一行一行读取数据的，表示的是偏移值
//valuein:表示数据在输入的时候的数据类型，是字符串的类型
//keyout:表示map结果输出的时候的数据类型，在当前的案例中是单词，所以是Text类型
//valueout:表示结果输出的时候的数据类型，单词的出现的次数所以是long或者int
//map阶段，注意要继承Mapper并且要重写map方法

public class WordCountMap extends Mapper<LongWritable, Text, Text, LongWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //数据读取是一行一行地读取
        String input = value.toString();
        //提取每一行的单词
        String[] words = input.split(" ");
        //遍历数组:将每一次出现的单词标记为1 相当于装换成key--value的类型  <hello,1>
        for (String item:words) {
            //通过context来输出结果   结果就是一个键值对
            context.write(new Text(item),new LongWritable(1));
        }

    }
}
