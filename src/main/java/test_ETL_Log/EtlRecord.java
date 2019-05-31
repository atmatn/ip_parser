package test_ETL_Log;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.BufferedReader;
import java.io.IOException;

public class EtlRecord extends RecordReader<Text, Text> {

    private Text key = new Text();

    private Text value = new Text();

    private boolean isProgress = false;

    private FileSplit fileSplit;

    private Configuration configuration;

    @Override
    public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {

        this.fileSplit= (FileSplit) split;

        this.configuration= context.getConfiguration();

    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {

        FileSystem fileSystem = null;

        FSDataInputStream fsDataInputStream = null;

        Path path = fileSplit.getPath();

        byte[] bytes = new byte[(int) fileSplit.getLength()];

        fileSystem = path.getFileSystem(configuration);

        fsDataInputStream=fileSystem.open(path);

        IOUtils.readFully(fsDataInputStream,bytes,0,bytes.length);

        BytesWritable bytesWritable=new BytesWritable();

        bytesWritable.set(bytes,0,bytes.length);

        StringBuffer stringBuffer=new StringBuffer(bytesWritable.toString());

        //stringBuffer.

        return false;
    }

    @Override
    public Text getCurrentKey() throws IOException, InterruptedException {
        return key;
    }

    @Override
    public Text getCurrentValue() throws IOException, InterruptedException {
        return value;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return isProgress ? 1f : 0f;
    }

    @Override
    public void close() throws IOException {

    }
}
