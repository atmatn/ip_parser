package test_weather_sort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import utils.DropDirByPath;

public class WeatherDriver {

    public static void main(String[] args) {

        DropDirByPath d = new DropDirByPath();

        d.drop("E:/output");

        Configuration configuration=new Configuration();

        try {

            Job job= Job.getInstance(configuration);

            job.setJarByClass(WeatherDriver.class);
            job.setMapperClass(WeatherMapper.class);
            job.setReducerClass(WeatherReducer.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(WeatherWritable.class);

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);

            job.setPartitionerClass(WeatherPartiton.class);

            TextInputFormat.setInputPaths(job,new Path("E:/input/sort"));
            TextOutputFormat.setOutputPath(job,new Path("E:/output"));

            boolean b = job.waitForCompletion(true);
            System.exit(b?0:1);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
