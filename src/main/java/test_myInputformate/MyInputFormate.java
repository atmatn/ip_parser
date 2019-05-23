package test_myInputformate;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.ByteWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

public class MyInputFormate extends FileInputFormat<NullWritable, ByteWritable> {

    @Override
    protected boolean isSplitable(JobContext context, Path filename){//重写isSpiltable，不对文件进行分片

        return false;

    }

    // 重写创建recordReader的方法，引入自己定义的recordreader类，并执行初始化
    public RecordReader<NullWritable, ByteWritable> createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {

        MyRecordReader myRecordReader=new MyRecordReader();

        myRecordReader.initialize(split,context);

        return null;
    }
}
