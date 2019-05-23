package test_myInputformate;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * RecordReader是用来break数据in key value 对
 *
 * @author gaocheng
 */
public class MyRecordReader extends RecordReader<NullWritable, Text> {

    private InputSplit inputSplit;

    private Text value=new Text();

    private Configuration configuration;

    private boolean progressed=false;

    public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {

            this.inputSplit=(FileSplit)split;

            this.configuration=context.getConfiguration();

    }

    //重点方法
    public boolean nextKeyValue() throws IOException, InterruptedException {
        while (!progressed){

            //创建一个byte数组来读取文件
            byte[] contents=new byte[(int)inputSplit.getLength()];





            progressed=true;

            return true;
        }

        return false;
    }

    public NullWritable getCurrentKey() throws IOException, InterruptedException {

        return NullWritable.get();
    }

    public Text getCurrentValue() throws IOException, InterruptedException {

        return value;
    }

    public float getProgress() throws IOException, InterruptedException {

        return progressed?1.0f:0.0f;
    }

    public void close() throws IOException {

    }
}
