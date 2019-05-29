package MrWeatherSort;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

public class RealLogRecodeWriter extends RecordWriter<RealLog, NullWritable> {

    FSDataOutputStream androidfs=null;

    FSDataOutputStream iphonefs=null;

    FSDataOutputStream otherfs=null;

    public RealLogRecodeWriter(FSDataOutputStream androidfs, FSDataOutputStream iphonefs, FSDataOutputStream otherfs){

        this.androidfs=androidfs;

        this.iphonefs=iphonefs;

        this.otherfs=otherfs;

    }

    public void write(RealLog key, NullWritable value) throws IOException, InterruptedException {

        if("android".equals(key.getVisitKey())){

            androidfs.write(key.toString().getBytes());


        }else if("iphonepro".equals(key.getVisitKey())){

            iphonefs.write(key.toString().getBytes());

        }else{

            otherfs.write(key.toString().getBytes());

        }

    }

    public void close(TaskAttemptContext context) throws IOException, InterruptedException {

        IOUtils.closeStream(androidfs);

        IOUtils.closeStream(iphonefs);

        IOUtils.closeStream(otherfs);

    }
}
