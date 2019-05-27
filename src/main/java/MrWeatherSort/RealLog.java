package MrWeatherSort;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class RealLog implements WritableComparable<RealLog> {

    private long ts;
    private String visitKey;
    private String message;


    public int compareTo(RealLog o) {//for compare ts

        if (ts > o.getTs()) {

            return 1;

        } else if (ts < o.getTs()) {

            return -1;

        } else {

            return 0;

        }
    }

    public void write(DataOutput out) throws IOException {

        out.writeLong(ts);
        out.writeUTF(visitKey);
        out.writeUTF(message);

    }

    public void readFields(DataInput in) throws IOException {

        ts = in.readLong();
        visitKey = in.readUTF();
        message = in.readUTF();

    }

    public RealLog() {
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public String getVisitKey() {
        return visitKey;
    }

    public void setVisitKey(String visitKey) {
        this.visitKey = visitKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ts=" + ts +
                "\tvisitKey=" + visitKey +
                "\t" + message;
    }
}
