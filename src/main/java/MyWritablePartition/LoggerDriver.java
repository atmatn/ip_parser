package MyWritablePartition;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

public class LoggerDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration configuration=new Configuration();

        Job job=Job.getInstance(configuration);

        job.setJarByClass(LoggerDriver.class);
        job.setMapperClass(LoggerMapper.class);
        job.setReducerClass(LoggerReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Logger.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Logger.class);

        //引用并设置partition
        job.setPartitionerClass(MyPartition.class);
        job.setNumReduceTasks(3);

        TextInputFormat.setInputPaths(job,new Path("E://input/logger"));
        TextOutputFormat.setOutputPath(job,new Path("E://output"));

        boolean b=job.waitForCompletion(true);

        System.exit(b?0:1);
    }
}
