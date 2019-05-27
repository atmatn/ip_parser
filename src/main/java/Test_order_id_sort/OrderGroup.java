package Test_order_id_sort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class OrderGroup extends WritableComparator {

    public OrderGroup (){
        super(Orders.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b){

        Orders oa= (Orders) a;
        Orders ob= (Orders) b;

        if(oa.getOrder_id() == ob.getOrder_id()){

            return 0;

        }else if(oa.getOrder_id() > ob.getOrder_id()){

            return 1;

        }else {

            return -1;

        }

    }

}
