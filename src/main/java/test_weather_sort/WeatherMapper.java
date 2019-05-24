package test_weather_sort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WeatherMapper extends Mapper<LongWritable, Text, Text, WeatherWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) {

        String line = value.toString();

        String[] split = line.split(" |\t");

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
