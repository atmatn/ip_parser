package Test_order_id_sort;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Orders implements WritableComparable<Orders> {//订单类

    private Long id;

    private Long order_id;

    private Double price;


    public int compareTo(Orders o) {

        if (order_id > o.getOrder_id()){
            return 1;
        }else if(order_id < o.getOrder_id()){
            return -1;
        }else{
            if(price > o.getPrice()){
                return -1;
            }else if(price < o.getPrice()){
                return 1;
            }else {
                return 0;
            }
        }
    }

    public void write(DataOutput out) throws IOException {

        out.writeLong(id);
        out.writeLong(order_id);
        out.writeDouble(price);

    }

    public void readFields(DataInput in) throws IOException {

        id=in.readLong();
        order_id=in.readLong();
        price=in.readDouble();

    }

    public Orders() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return id +"\t"+order_id +"\t"+ price;
    }
}
