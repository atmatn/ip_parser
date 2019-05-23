package hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordReduce_v2 extends Reducer<Text, IntWritable, Text,Text> {

    protected void reduce(Text key, Iterable<IntWritable> values, Context context
    ) throws IOException, InterruptedException {
        int index = 1;
        StringBuffer stringBuffer = new StringBuffer();

        for (IntWritable v : values) {
            if(v.get()==index){
                stringBuffer.append(key);
                index++;
            }
        }
        context.write(key, new Text(""));

    }
}

