package MrWeatherSort;


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

import java.io.IOException;

import static MrWeatherSort.RealLogDriver.logMessage;

public class RealLogRecordReader extends RecordReader<Text, BytesWritable> {

    private Text key = new Text();

    private BytesWritable value = new BytesWritable();

    private boolean isProgress = false;

    private FileSplit fileSplit;

    private Configuration configuration;

    public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {

        fileSplit = (FileSplit) split;

        configuration = context.getConfiguration();

        logMessage.setMessage("RealLogRecordReader start init");

    }

    public boolean nextKeyValue() {

        logMessage.setMessage("RealLogRecordReader start nextKeyValue ");

        FileSystem fileSystem = null;

        FSDataInputStream fsDataInputStream = null;

        while (!isProgress) {

            Path path = fileSplit.getPath();//根据切片获取path

            byte[] bytes = new byte[(int) fileSplit.getLength()];

            try {

                fileSystem = path.getFileSystem(configuration);

                fsDataInputStream = fileSystem.open(path);

                IOUtils.readFully(fsDataInputStream, bytes, 0, bytes.length);

                key.set(path.toString());

                value.set(bytes, 0, bytes.length);

//                String s = new String(value.getBytes());
//
//                System.out.println(s);

            } catch (IOException e) {

                e.printStackTrace();

            } finally {

                IOUtils.closeStream(fsDataInputStream);

                IOUtils.closeStream(fileSystem);
            }

            isProgress = true;

            logMessage.setMessage("RealLogRecordReader stop nextKeyValue ");

            return true;
        }

        return false;
    }

    public Text getCurrentKey() throws IOException, InterruptedException {

        return key;
    }

    public BytesWritable getCurrentValue() throws IOException, InterruptedException {

        return value;
    }

    public float getProgress() throws IOException, InterruptedException {

        return isProgress ? 1f : 0f;
    }

    public void close() throws IOException {
        //nothing
    }
}
