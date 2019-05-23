package MyInpuFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

import java.io.IOException;

public class WholeDriver {
    public static void main(String[] args) {

        Configuration configuration=new Configuration();

        try {
            Job job=Job.getInstance(configuration);

            job.setMapperClass(WholeMapper.class);
            job.setReducerClass(WholeReducer.class);

            job.setJarByClass(WholeDriver.class);

            job.setInputFormatClass(WholeInputFormat.class);
            job.setOutputFormatClass(SequenceFileOutputFormat.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(BytesWritable.class);

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(BytesWritable.class);

            WholeInputFormat.setInputPaths(job,new Path("E://input"));
            SequenceFileOutputFormat.setOutputPath(job,new Path("E://output"));

            boolean b = job.waitForCompletion(true);
            System.exit(b?0:1);

        } catch (IOException e) {

            e.printStackTrace();

        } catch (InterruptedException e) {

            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }
    }
}
