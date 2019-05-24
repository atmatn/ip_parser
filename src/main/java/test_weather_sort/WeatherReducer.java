package test_weather_sort;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;


public class WeatherReducer extends Reducer<Text, WeatherWritable, Text, Text> {

    @Override
    protected void reduce(Text key, Iterable<WeatherWritable> value, Context context) {

        WeatherWritable w = new WeatherWritable();

        for (WeatherWritable weatherWritable : value) {

            if (weatherWritable.compareTo(w) > 0) {

                w = weatherWritable;

            }

        }

        try {

            context.write(new Text(w.getYear().toString()), new Text(w.getTemper().toString()));

        } catch (IOException e) {

            e.printStackTrace();

        } catch (InterruptedException e) {

            e.printStackTrace();

        }
    }
}
