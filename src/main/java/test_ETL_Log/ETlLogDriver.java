package test_ETL_Log;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import utils.DropDirByPath;

import java.io.IOException;

public class ETlLogDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        DropDirByPath d = new DropDirByPath();

        d.drop("E:/output");

        Configuration configuration=new Configuration();

        Job job= Job.getInstance(configuration);

        job.setJarByClass(ETlLogDriver.class);

        job.setMapperClass(ETL_Log_Mapper_dropLF.class);
        job.setNumReduceTasks(0);

        job.setMapOutputKeyClass(EtlLog.class);
        job.setMapOutputValueClass(NullWritable.class);

        FileInputFormat.setInputPaths(job,"E:/input/test.txt");
        TextOutputFormat.setOutputPath(job,new Path("E:/output"));

        boolean b = job.waitForCompletion(true);

        System.exit(b?0:1);

    }
}
