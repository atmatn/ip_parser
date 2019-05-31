package Test_join_onMapper;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import utils.DropDirByPath;

import java.io.IOException;

public class Join_onMapper_Driver {

    public static void main(String[] args) {

        DropDirByPath d = new DropDirByPath();

        d.drop("E:/output");

        Configuration configuration=new Configuration();

        try {

            Job job=Job.getInstance(configuration);
            job.setJarByClass(Join_onMapper_Driver.class);

            job.setMapperClass(Test_joinOnMapper_Mapper.class);
            job.setNumReduceTasks(0);

            job.setMapOutputKeyClass(Text.class);
            //job.setMapOutputValueClass(NullWritable.get());

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(NullWritable.class);

            FileInputFormat.setInputPaths(job,new Path("E:/input/join/order.txt"));
            TextOutputFormat.setOutputPath(job, new Path("E:/output"));

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
