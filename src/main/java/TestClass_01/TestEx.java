package TestClass_01;

import org.apache.hadoop.mapreduce.*;

import java.io.IOException;
import java.util.List;

public abstract class TestEx extends ByEx{

    public List<InputSplit> getSplits(JobContext context) throws IOException, InterruptedException {
        System.out.println();
        return null;
    }
}
abstract class ByEx extends InputFormat {
    public void test01(){
        System.out.println();
    }
    public abstract void test02();
}
class finalEx extends TestEx{

    public void test02() {

    }

    public RecordReader createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        return null;
    }
}