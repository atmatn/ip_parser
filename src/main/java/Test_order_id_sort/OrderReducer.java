package Test_order_id_sort;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class OrderReducer extends Reducer<Orders, NullWritable,Orders, NullWritable> {

    @Override
    protected void reduce(Orders key,Iterable<NullWritable> value,Context context){

        try {

            context.write(key,NullWritable.get());

        } catch (IOException e) {

            e.printStackTrace();

        } catch (InterruptedException e) {

            e.printStackTrace();

        }
    }

}
