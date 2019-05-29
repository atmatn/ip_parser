package Test_telephone_sort;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TeleMapper extends Mapper<LongWritable, Text,Text,Text> {

    @Override
    protected void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException {

        String line =value.toString();

        String[]  arr=line.split(" |\t");

        if("true".equals(arr[1])){

            context.write(new Text(arr[0]),new Text(arr[2]));

        }
    }
}
