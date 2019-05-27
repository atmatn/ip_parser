package MrWeatherSort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import static MrWeatherSort.RealLogDriver.logMessage;

public class RealLogGroup extends WritableComparator {

    public RealLogGroup (){
        super(RealLog.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {

        logMessage.setMessage("start RealLogGroup");

        RealLog ra= (RealLog) a;

        RealLog rb= (RealLog) b;

        logMessage.setMessage("stop RealLogGroup");

        return ra.compareTo(rb);
    }
}
