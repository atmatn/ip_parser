package MyWritablePartition;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class LoggerReducer extends Reducer<Text,Logger,Text,Logger> {

    @Override
    protected void reduce(Text text,Iterable<Logger> logger,Context context) throws IOException, InterruptedException {
        for (Logger l:logger) {

            context.write(text,l);

        }

    }
}
