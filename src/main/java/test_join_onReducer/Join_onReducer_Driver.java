package test_join_onReducer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import utils.DropDirByPath;

import java.io.IOException;

public class Join_onReducer_Driver {

    public static void main(String[] args) {

        DropDirByPath d = new DropDirByPath();

        d.drop("E:/output");

        Configuration configuration=new Configuration();

        try {

            Job job=Job.getInstance(configuration);
            job.setJarByClass(Join_onReducer_Driver.class);

            job.setMapperClass(Test_join_Mapper.class);
            job.setReducerClass(Test_Join_Reducer.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Table.class);

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(NullWritable.class);

            FileInputFormat.setInputPaths(job,new Path("E:/input/join"));
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
