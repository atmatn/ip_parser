package MyInpuFormat;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WholeMapper extends Mapper<Text, BytesWritable, Text, BytesWritable> {

//    private String filepath;

//    @Override
//    protected void setup(Context context) {
//
//        InputSplit inputSplit = context.getInputSplit();
//
//        Path path = ((FileSplit) inputSplit).getPath();
//
//        filepath = path.toString();
//    }

    @Override
    protected void map(Text key, BytesWritable value, Context context) {

        try {

            context.write(key,value);

        } catch (IOException e) {

            e.printStackTrace();

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

    }
}
