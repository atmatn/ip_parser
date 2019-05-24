package test_weather_sort;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class WeatherPartiton extends Partitioner<Text, WeatherWritable> {

    public int getPartition(Text text, WeatherWritable weatherWritable, int numPartitions) {

        numPartitions=Integer.parseInt(weatherWritable.getYear().toString());

        return numPartitions;

    }

}
