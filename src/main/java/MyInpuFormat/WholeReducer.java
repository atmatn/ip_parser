package MyInpuFormat;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WholeReducer extends Reducer<Text, BytesWritable, Text,BytesWritable > {

    @Override
    protected void reduce(Text key, Iterable<BytesWritable> value, Context context) {
        try {
            for (BytesWritable text : value) {

                context.write(key, text);

            }
        } catch (IOException e) {

            e.printStackTrace();

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

    }
}
