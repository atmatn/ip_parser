package Test_join_onMapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Test_joinOnMapper_Mapper extends Mapper<LongWritable, Text, Text, NullWritable> {

    Map<String, String> map = new HashMap<String, String>();

    String readline = null;

    @Override
    protected void setup(Context context) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(

                new FileReader(new File("E:/input/join/product.txt")

                )
        );
        while ((readline = bufferedReader.readLine()) != null) {

            String[] split = readline.split("\t");

            map.put(split[0], split[1]);

        }
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        StringBuffer line = new StringBuffer(value.toString());

        int from = line.indexOf("\t");

        int to = line.indexOf("\t", from + 1);

        String substring = line.substring(from + 1, to);

        line.replace(from + 1, to, map.get(substring));

        context.write(new Text(line.toString()), NullWritable.get());

    }
}
