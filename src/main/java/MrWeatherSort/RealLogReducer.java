package MrWeatherSort;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

import static MrWeatherSort.RealLogDriver.logMessage;

public class RealLogReducer extends Reducer<RealLog, NullWritable,RealLog, NullWritable> {

    @Override
    protected void reduce(RealLog key, Iterable<NullWritable> value , Context context){

        logMessage.setMessage("start reduce");

        try {

            context.write(key,NullWritable.get());

        } catch (IOException e) {

            e.printStackTrace();

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

        logMessage.setMessage("stop reduce");
    }
}
