package MrWeatherSort;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

import static MrWeatherSort.RealLogDriver.logMessage;

public class RealLogMapper extends Mapper<Text, BytesWritable, Text, NullWritable> {

    @Override
    protected void map(Text key, BytesWritable value, Context context) {

        String line = new String(value.getBytes()).trim();

        logMessage.setMessage("mapper get line\t");

        try {

            context.write(new Text(line), NullWritable.get());

            logMessage.setMessage("mapper create context: " + context);

        } catch (IOException e) {

            e.printStackTrace();

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

    }
}
