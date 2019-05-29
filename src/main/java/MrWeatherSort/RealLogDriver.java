package MrWeatherSort;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import utils.DropDirByPath;

import java.io.IOException;

public class RealLogDriver {

    static LogMessage logMessage = new LogMessage();

    public static void main(String[] args) {

        DropDirByPath d = new DropDirByPath();

        d.drop("E:/output");
        d.drop("E:/output_new");

        Configuration configuration = new Configuration();

        try {
            Job job = Job.getInstance(configuration);

            logMessage.setMessage("create job instance");

            job.setJarByClass(RealLogDriver.class);
            job.setJobName("all coming");
            job.setMapperClass(RealLogMapper.class);

            job.setMapOutputValueClass(Text.class);
            job.setMapOutputValueClass(NullWritable.class);

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(NullWritable.class);

            job.setInputFormatClass(RealLogInputFormat.class);

            RealLogInputFormat.setInputPaths(job, new Path("src/main/java/input/real_logs"));
            TextOutputFormat.setOutputPath(job, new Path("E:/output"));

//////////////////////////////////////////////////////////////////////////////////////////////////////////

            Job job_new = Job.getInstance(configuration);
            job_new.setJobName("all next");
            job_new.setMapperClass(RealLogMapper_new.class);
            job_new.setReducerClass(RealLogReducer.class);

            job_new.setMapOutputKeyClass(RealLog.class);
            job_new.setMapOutputValueClass(NullWritable.class);

            job_new.setOutputKeyClass(RealLog.class);
            job_new.setOutputValueClass(NullWritable.class);

            job_new.setPartitionerClass(RealLogPartition.class);
            job_new.setNumReduceTasks(3);

            job_new.setGroupingComparatorClass(RealLogGroup.class);

            job_new.setCombinerClass(RealLogCombiner.class);

            TextInputFormat.setInputPaths(job_new, new Path("E:/output"));
            TextOutputFormat.setOutputPath(job_new, new Path("E:/output_new"));

            try {

                if(job.waitForCompletion(true)){

                    System.out.println("-------------------------------------------------");

                    System.out.println(logMessage.getMessage());

                    System.exit(job_new.waitForCompletion(true)? 0 : 1);
                }

            } catch (InterruptedException e) {

                e.printStackTrace();

            } catch (ClassNotFoundException e) {

                e.printStackTrace();
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}
