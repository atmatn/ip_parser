package test_weather_sort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class WeatherWritable implements WritableComparable<WeatherWritable> {

    IntWritable year =new IntWritable(Integer.MIN_VALUE);

    IntWritable temper=new IntWritable(Integer.MIN_VALUE);

    public WeatherWritable() {
        super();
    }

    public WeatherWritable(IntWritable year, IntWritable temper) {
        this.year = year;
        this.temper = temper;
    }

    public void write(DataOutput out) throws IOException {

        out.writeUTF(year.toString());

        out.writeUTF(temper.toString());
    }

    public void readFields(DataInput in) throws IOException {

        year.set(Integer.parseInt(in.readUTF()));

        temper.set(Integer.parseInt(in.readUTF()));

    }

    public IntWritable getYear() {
        return year;
    }

    public void setYear(IntWritable year) {
        this.year = year;
    }

    public IntWritable getTemper() {
        return temper;
    }

    public void setTemper(IntWritable temper) {
        this.temper = temper;
    }

    public int compareTo(WeatherWritable o) {
        int i = this.temper.compareTo(o.getTemper());
        return i;
    }
}
