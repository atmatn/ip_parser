package MyWritablePartition;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class Logger implements WritableComparable<Logger> {

    private Text product;

    private Text visitKey;

    private Text message;


    public int compareTo(Logger o) {
        return 0;
    }

    public void write(DataOutput out) throws IOException {//序列化到输出流

        out.writeUTF(visitKey.toString());
        out.writeUTF(message.toString());
    }

    public void readFields(DataInput in) throws IOException {//从输出流中反序列化对象

        visitKey=new Text(in.readUTF());
        message=new Text(in.readUTF());

    }

    public Logger() {
        super();
    }


    public Text getProduct() {
        return product;
    }

    public void setProduct(Text product) {
        this.product = product;
    }

    public Text getVisitKey() {
        return visitKey;
    }

    public void setVisitKey(Text visitKey) {
        this.visitKey = visitKey;
    }

    public Text getMessage() {
        return message;
    }

    public void setMessage(Text message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "visitKey='" + visitKey + '\'' +
                "\tmessage='" + message + '\'' ;
    }
}
