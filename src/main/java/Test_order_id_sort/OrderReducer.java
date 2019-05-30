package Test_order_id_sort;

import org.apache.avro.mapred.AvroKey;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class OrderReducer extends Reducer<AvroKey<Orders>, NullWritable, Text, NullWritable> {

    @Override
    protected void reduce(AvroKey<Orders> key,Iterable<NullWritable> value,Context context){

        try {

            context.write(new Text(key.toString()),NullWritable.get());

        } catch (IOException e) {

            e.printStackTrace();

        } catch (InterruptedException e) {

            e.printStackTrace();

        }
    }

}
