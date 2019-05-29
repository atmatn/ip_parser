package Test_telephone_sort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import utils.DropDirByPath;


import java.io.IOException;

public class TeleDriver { //no reduce

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {


        DropDirByPath d = new DropDirByPath();

        System.out.println(d.drop("E:/output"));
        System.out.println(d.drop("E:/output_re"));

        Configuration configuration=new Configuration();

        Job job= Job.getInstance(configuration);

        job.setJarByClass(TeleDriver.class);
        job.setMapperClass(TeleMapper.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        TextInputFormat.setInputPaths(job,new Path("src/main/java/input/tele"));
        TextOutputFormat.setOutputPath(job,new Path("E:/output"));
////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Job job_re=Job.getInstance(configuration);

        job_re.setJarByClass(TeleDriver.class);
        job_re.setMapperClass(TeleMapper.class);

        job_re.setMapOutputKeyClass(Text.class);
        job_re.setMapOutputValueClass(Text.class);

        TextInputFormat.setInputPaths(job_re,new Path("E:/output"));
        TextOutputFormat.setOutputPath(job_re,new Path("E:/output_re"));

        if(job.waitForCompletion(true)){

            System.exit(job_re.waitForCompletion(true)?0:1);

        }
    }
}
