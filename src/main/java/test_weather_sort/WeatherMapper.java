package test_weather_sort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WeatherMapper extends Mapper<LongWritable, Text, Text, WeatherWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) {

        Counter counter_big = context.getCounter("最高气温", "大于0度");

        Counter counter_small = context.getCounter("最高气温", "小于0度");

        String line = value.toString();

        String[] split = line.split(" |\t");

        if(Integer.parseInt(split[1])>=0){

            counter_big.increment(1);

        }else{

            counter_small.increment(1);

        }

        WeatherWritable weatherWritable =

                new WeatherWritable(

                        new IntWritable(Integer.parseInt(split[0])),
                        new IntWritable(Integer.parseInt(split[1]))

                );

        try {

            context.write(new Text(split[0]), weatherWritable);

        } catch (IOException e) {

            e.printStackTrace();

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }
}
