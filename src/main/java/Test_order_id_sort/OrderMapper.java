package Test_order_id_sort;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class OrderMapper extends Mapper<LongWritable, Text, Orders, NullWritable> {

    Orders orders=new Orders();

    @Override
    protected void map(LongWritable key,Text value,Context context){

        String line = value.toString();

        String[] split = line.split(" |\t");

        orders.setId(Long.parseLong(split[0]));

        orders.setOrder_id(Long.parseLong(split[1]));

        orders.setPrice(Double.parseDouble(split[2]));

        try {

            context.write(orders,NullWritable.get());

        } catch (IOException e) {

            e.printStackTrace();

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

    }
}
