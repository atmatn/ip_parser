package MrWeatherSort;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

import static MrWeatherSort.RealLogDriver.logMessage;

public class RealLogPartition extends Partitioner<RealLog, NullWritable> {

    public int getPartition(RealLog realLog, NullWritable nullWritable, int numPartitions) {

        logMessage.setMessage("start getPartition");

        if("android".equals(realLog.getVisitKey())){

            numPartitions =0;

        }else if("iphonepro".equals(realLog.getVisitKey())){

            numPartitions =1;

        }else{

            numPartitions =2;

        }

        return numPartitions;

    }
}
