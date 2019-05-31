package test_join_onReducer;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Table implements Writable {

    private String id ="";

    private String pid="";

    private String count="";

    private String pname="";


    @Override
    public void write(DataOutput out) throws IOException {

        out.writeUTF(id);

        out.writeUTF(pid);

        out.writeUTF(count);

        out.writeUTF(pname);

    }

    @Override
    public void readFields(DataInput in) throws IOException {

        id=in.readUTF();

        pid=in.readUTF();

        count=in.readUTF();

        pname=in.readUTF();

    }

    public Table() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
}
