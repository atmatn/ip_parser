package test_join_onReducer;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Table implements Writable {

    String id;

    String pname;

    String pid;

    int count;

    String table_key;

    public void write(DataOutput out) throws IOException {

        out.writeUTF(id);

        out.writeUTF(pname);

        out.writeUTF(pid);

        out.write(count);

        out.writeUTF(table_key);

    }

    public void readFields(DataInput in) throws IOException {

        id=in.readUTF();

        pname=in.readUTF();

        pid=in.readUTF();

        count=in.readInt();

        table_key=in.readUTF();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTable_key() {
        return table_key;
    }

    public void setTable_key(String table_key) {
        this.table_key = table_key;
    }
}
