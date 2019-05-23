package myhadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MyhadoopMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    static IntWritable intWritable = new IntWritable(1);
    static StringBuffer stringBuffer = new StringBuffer();
    final static String TAB = " ";
    int from = 0;
    int to = 0;

    @Override
    protected void map(LongWritable longWritable, Text text, Context context) {
        stringBuffer.append(text.toString());
        while (stringBuffer.indexOf(TAB) >= 0) {
            to = stringBuffer.indexOf(TAB);
            try {
                text.set(stringBuffer.substring(from, to));
                context.write(text, intWritable);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stringBuffer.replace(from, to + 1, "");
            to++;
        }
    }

//    protected void filter(LongWritable longWritable, Text text, Context context) {
//        String s = text.toString();
//        String[] arr = s.split(" ");
//        for (String elment : arr) {
//            try {
//                context.write(new Text(elment), intWritable);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public void run(Context context) throws IOException, InterruptedException {
//        setup(context);
//        try {
//            while (context.nextKeyValue()) {
//                filter(context.getCurrentKey(), context.getCurrentValue(), context);
//                System.out.println("_________________________________________________________________________");
//            }
//        } finally {
//            cleanup(context);
//        }
//    }
}
