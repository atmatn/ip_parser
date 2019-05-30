package Test_order_id_sort;

import org.apache.avro.Schema;
import org.apache.avro.mapreduce.AvroJob;
import org.apache.avro.mapreduce.AvroKeyValueOutputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import utils.DropDirByPath;

import java.io.IOException;

public class OrderDriver {
    public static void main(String[] args) {

        DropDirByPath d = new DropDirByPath();

        d.drop("E:/output");

        Configuration configuration=new Configuration();

        try {

            Job job=Job.getInstance(configuration);
            String jobname="分组排序找出order中最大的price";
            job.setJobName(jobname);

            job.setJarByClass(OrderDriver.class);
            job.setMapperClass(OrderMapper.class);
            job.setReducerClass(OrderReducer.class);

            job.setMapOutputKeyClass(Orders.class);
            job.setMapOutputValueClass(NullWritable.class);

            job.setOutputKeyClass(Orders.class);
            job.setOutputValueClass(NullWritable.class);

            job.setGroupingComparatorClass(OrderGroup.class);

            job.setOutputFormatClass(AvroKeyValueOutputFormat.class);

            TextInputFormat.setInputPaths(job,new Path("src/main/java/input/order"));
            AvroKeyValueOutputFormat.setOutputPath(job,new Path("E:/output"));

            AvroJob.setOutputKeySchema(job, Schema.create(Schema.Type.STRING));
            AvroJob.setOutputValueSchema(job, Schema.create(Schema.Type.NULL));

            boolean b = false;
            try {

                b = job.waitForCompletion(true);

            } catch (InterruptedException e) {

                e.printStackTrace();

            } catch (ClassNotFoundException e) {

                e.printStackTrace();
            }

            System.exit(b?0:1);

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
