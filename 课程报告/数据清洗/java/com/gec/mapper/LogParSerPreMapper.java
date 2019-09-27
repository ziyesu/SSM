package com.gec.mapper;

import com.gec.bean.WebLogBean;
import com.gec.utils.WebLogParser;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class LogParSerPreMapper extends Mapper<LongWritable, Text, WebLogBean, NullWritable> {

    private String pages="/hadoop?id=";

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        WebLogBean webLogBean = WebLogParser.parser(line);

        if (webLogBean != null) {
//           WebLogParser.filtStaticResource(webLogBean, pages);
            if (webLogBean.getRequest().contains(pages)) {
                context.write(webLogBean, NullWritable.get());
            }
        }
    }
}
