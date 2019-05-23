package MyInpuFormat;

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

public class WholeRecordReader extends RecordReader<Text, BytesWritable> {

    private FileSplit fileSplit;

    private Configuration configuration;

    private Text key = new Text();

    private BytesWritable value = new BytesWritable();

    private boolean progress = false;

    @Override
    public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {

        this.fileSplit = (FileSplit) split;

        this.configuration = context.getConfiguration();

    }

    @Override
    public boolean nextKeyValue() {

        FileSystem fileSystem=null;
        FSDataInputStream fsDataInputStream=null;

        while (!progress) {

            Path path = fileSplit.getPath();

            byte[] bytes=new byte[(int)fileSplit.getLength()];

            try {

                fileSystem = path.getFileSystem(configuration);

                fsDataInputStream = fileSystem.open(path);

                IOUtils.readFully(fsDataInputStream, bytes, 0, bytes.length);

                key.set(path.toString());

                value.set(bytes,0,bytes.length);

            }catch(Exception e){

                e.printStackTrace();

            }finally{

                IOUtils.closeStream(fsDataInputStream);

                IOUtils.closeStream(fileSystem);

            }

            progress = true;

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
        return progress ? 0.0f : 1.0f;
    }

    public void close() throws IOException {

        //nothing
    }

}
