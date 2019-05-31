package test_ETL_Log;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ETL_Log_Mapper_dropLF extends Mapper<LongWritable, Text,EtlLog, NullWritable> {

    //  2018/08/31 17:54:32.508 +0800

    @Override
    protected void map(LongWritable key,Text value ,Context context){

        StringBuffer stringBuffer = new StringBuffer(value.toString());

        //boolean b = value.toString().startsWith("[0-9]{4}/[0-9]{2}/[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}\\.[0-9]{3} \\+0800");

        Pattern pattern =
                Pattern.compile("[0-9]{4}/[0-9]{2}/[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}\\.[0-9]{3} \\+0800");
        Matcher m = pattern.matcher(value.toString());

        StringBuffer sb = m.appendTail(stringBuffer);

        System.out.println(sb);
    }
}
