package test_ETL_Log;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class PartitionByStatus extends Partitioner<EtlLog, NullWritable> {

    @Override
    public int getPartition(EtlLog etlLog, NullWritable nullWritable, int numPartitions) {

        if("INFO".equals(etlLog.getStatus())){

            numPartitions=0;

        }else if("ERROR".equals(etlLog.getStatus())){

            numPartitions=1;

        }else{

            numPartitions=2;

        }
        return numPartitions;
    }
}
