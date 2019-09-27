package com.gec.mapreduce.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class LogParserMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String fileds[] = line.split("\001");

        String request = fileds[4];

        Text keyOut = new Text();
        keyOut.set(fileds[4]);
        IntWritable valueOut = new IntWritable(1);

        context.write(keyOut, valueOut);
    }
}
