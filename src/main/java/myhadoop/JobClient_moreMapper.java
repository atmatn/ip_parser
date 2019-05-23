package myhadoop;

import hadoop.WordReduce;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class JobClient_moreMapper {
    public static void main(String[] args) throws IOException, InterruptedException {
        //long start=System.currentTimeMillis();

        //获取conf对象并设置本地运行模式
        Configuration configuration = new Configuration();
        configuration.set("mapreduce.framework.name", "local");
        configuration.set("fs.defaultFS", "file:///");

        Job job = Job.getInstance();
        job.setJarByClass(JobClient_moreMapper.class);

        //设置mapper和reduce对应的实现类
        //job.setMapperClass(MyhadoopMapper.class);
        job.setReducerClass(WordReduce.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置输入和输出的数据类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //设置输入输出的路径
        //FileInputFormat.setInputPaths(job, new Path(args[0]));
        //FileOutputFormat.setOutputPath(job, new Path(args[1]));
        MultipleInputs.addInputPath(job, new Path(args[0]),FileInputFormat.class,MyhadoopMapper.class);
        MultipleInputs.addInputPath(job, new Path(args[1]),FileInputFormat.class,MyhadoopMapper.class);
        FileOutputFormat.setOutputPath(job, new Path(args[2]));
        boolean res = false;

        try {
            res = job.waitForCompletion(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            //long end =System.currentTimeMillis();
            //System.out.println(end-start);
            System.exit(res ? 0 : 1);
        }
    }
}
