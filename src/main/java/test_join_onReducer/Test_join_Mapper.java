package test_join_onReducer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class Test_join_Mapper extends Mapper<LongWritable, Text,Text, Table> {

    Table table=new Table();

    @Override
    protected  void map(LongWritable key,Text value,Context context){

        String line =value.toString();

        String[] split = line.split("\t");

        try {

            FileSplit fileSplit=(FileSplit) context.getInputSplit();

            String s=fileSplit.getPath().getName();

            if(s.startsWith("order")){

                table.setId(split[0]);

                table.setPid(split[1]);

                table.setCount(split[2]);

                context.write(new Text(table.getPid()),table);

            }else{

                table.setPid(split[0]);

                table.setPname(split[1]);

                context.write(new Text(table.getPid()),table);
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
