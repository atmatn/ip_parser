package hadoop;

import myhadoop.MyhadoopMapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        int i=1;
        IntWritable intWritable = new IntWritable(i);
        String info = value.toString();
//        String[] info_bysplit = info.split(" ");
//        for (String s : info_bysplit) {
//            context.write(new Text(s), intWritable);
//        }
        //2018/08/31
        info.replace("\n","");
        info.replace("[0-9]{4}/[0-9]{2}/[0-9]{2}","\n[0-9]{4}/[0-9]{2}/[0-9]{2}");
        context.write(new Text(info),intWritable);
        i++;
    }

    public static void main(String[] args) throws Exception{
        //long start=System.currentTimeMillis();

        //获取conf对象并设置本地运行模式
        Configuration configuration = new Configuration();
        configuration.set("mapreduce.framework.name", "local");
        configuration.set("fs.defaultFS", "file:///");

        Job job = Job.getInstance();
        job.setJarByClass(JobClient.class);

        //设置mapper和reduce对应的实现类
        job.setMapperClass(WordMapper.class);
        job.setReducerClass(WordReduce_v2.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置输入和输出的数据类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //设置输入输出的路径
        TextInputFormat.setInputPaths(job, new Path("E:\\yanagishima.log"));
        FileOutputFormat.setOutputPath(job, new Path("E:\\date\\"));

        boolean res = false;

        try {
            res = job.waitForCompletion(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
//            long end =System.currentTimeMillis();
//            System.out.println(end-start);
            System.exit(res ? 0 : 1);
        }

    }
}
