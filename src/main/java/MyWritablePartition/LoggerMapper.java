package MyWritablePartition;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class LoggerMapper extends Mapper<LongWritable, Text,Text,Logger> {

    private Logger logger=new Logger();

    @Override
    protected void map(LongWritable longWritable,Text text,Context context){

        String line=text.toString();

        String[] split = line.split("\t");

        //logger.setProduct(new Text(split[0]));

        logger.setVisitKey(new Text(split[1]));

        logger.setMessage(new Text(split[2]));

        try {
            context.write(new Text(split[0]),logger);

        } catch (IOException e) {

            e.printStackTrace();

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

    }
}
