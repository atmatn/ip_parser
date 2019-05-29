package MrWeatherSort;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class RealLogMapper_new extends Mapper<LongWritable,Text,RealLog, NullWritable> {

    private RealLog realLog = new RealLog();

    @Override
    protected void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException {

        String line=value.toString();

        if(line.trim() != null){

            realLog.setTs(Long.parseLong(line.substring(0,14)));

            int index_secondSpace=line.substring(15).indexOf(("\t"));

            realLog.setVisitKey(line.substring(15,15+index_secondSpace));

            realLog.setMessage(line.substring(16+index_secondSpace));

            context.write(realLog,NullWritable.get());

        }

    }
}
