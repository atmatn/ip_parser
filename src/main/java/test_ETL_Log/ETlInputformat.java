package test_ETL_Log;

import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

public class ETlInputformat extends FileInputFormat {
    @Override
    public RecordReader createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {

        EtlRecord etlRecord=new EtlRecord();

        etlRecord.initialize(split,context);

        return etlRecord;
    }
}
