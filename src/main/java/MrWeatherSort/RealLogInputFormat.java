package MrWeatherSort;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

import static MrWeatherSort.RealLogDriver.logMessage;

public class RealLogInputFormat extends FileInputFormat<Text, BytesWritable> {

    @Override
    protected boolean isSplitable(JobContext context, Path filename){

        logMessage.setMessage("set isSplitable = false");

        return false;

    }

    @Override
    public RecordReader<Text, BytesWritable> createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {

        RealLogRecordReader rrr = new RealLogRecordReader();

        logMessage.setMessage("create RealLogRecordReader");

        return rrr;

    }
}
