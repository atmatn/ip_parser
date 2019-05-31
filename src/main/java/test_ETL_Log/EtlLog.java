package test_ETL_Log;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class EtlLog implements Writable {

    String time;

    String status;

    String message;

    @Override
    public void write(DataOutput out) throws IOException {

        out.writeUTF(time);
        out.writeUTF(status);
        out.writeUTF(message);

    }

    @Override
    public void readFields(DataInput in) throws IOException {

        time=in.readUTF();
        status=in.readUTF();
        message=in.readUTF();

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
