package MrWeatherSort;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class RealLogOutputFormat extends FileOutputFormat<RealLog, NullWritable> {


    public RecordWriter<RealLog, NullWritable> getRecordWriter(TaskAttemptContext job) throws IOException, InterruptedException {

        FileSystem fileSystem=FileSystem.get(job.getConfiguration());

        Path path_android=new Path("E:/output_new/android.log");

        Path path_iphone=new Path("E:/output_new/iphone.log");

        Path path_other=new Path("E:/output_new/other.log");

        FSDataOutputStream fs_android=fileSystem.create(path_android);

        FSDataOutputStream fs_iphone=fileSystem.create(path_iphone);

        FSDataOutputStream fs_other=fileSystem.create(path_other);

        RealLogRecodeWriter testRecodeWriter=new RealLogRecodeWriter(fs_android,fs_iphone,fs_other);

        return testRecodeWriter;

    }
}
