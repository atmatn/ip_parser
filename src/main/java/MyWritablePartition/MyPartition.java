package MyWritablePartition;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;



public class MyPartition extends Partitioner<Text, Logger> {

    public int getPartition(Text key, Logger value, int numPartitions) {

    if("ke".equals(key.toString())){

        numPartitions =0;


    }else if("ynote".equals(key.toString())){

        numPartitions =1;

    }else{

        numPartitions =2;

    }

        return numPartitions;

    }
}
