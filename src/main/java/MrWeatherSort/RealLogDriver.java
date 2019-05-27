package MrWeatherSort;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

public class RealLogDriver {

    static LogMessage logMessage=new LogMessage();

    public static void main(String[] args) {

        Configuration configuration=new Configuration();

        try {
            Job job=Job.getInstance(configuration);

            logMessage.setMessage("create job instance");

            job.setJarByClass(RealLogDriver.class);
            job.setJobName("all coming");
            job.setMapperClass(RealLogMapper.class);
            job.setReducerClass(RealLogReducer.class);

            job.setMapOutputValueClass(RealLog.class);
            job.setMapOutputValueClass(NullWritable.class);

            job.setOutputKeyClass(RealLog.class);
            job.setOutputValueClass(NullWritable.class);

            job.setInputFormatClass(RealLogInputFormat.class);

            job.setPartitionerClass(RealLogPartition.class);
            job.setNumReduceTasks(3);

            job.setGroupingComparatorClass(RealLogGroup.class);

            job.setCombinerClass(RealLogCombiner.class);

            RealLogInputFormat.setInputPaths(job,new Path("src/main/java/input/real_logs"));
            TextOutputFormat.setOutputPath(job,new Path("src/main/java/output"));

            try {

                boolean b = job.waitForCompletion(true);

                System.out.println("-------------------------------------------------");

                System.out.println(logMessage.getMessage());

                System.exit(b?0:1);

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
