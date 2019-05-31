package test_join_onReducer;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Test_Join_Reducer extends Reducer<Text, Table, Text, NullWritable> {

    /*
     * 01,101 01 1
     * 02,102 02 1
     * 01,01 java
     * */

    Map map = new HashMap();



    @Override
    protected void reduce(Text key, Iterable<Table> value, Context context) {

        ArrayList<Table> list = new ArrayList<Table>();

        String result = "";

        try {

            for (Table o : value) {

                if ("".equals(o.getId())) {

                    map.put(o.getPid(), o.getPname());

                } else {

                    Table temp = new Table();

                    BeanUtils.copyProperties(temp,o);

                    list.add(temp);

                }

            }
            for (int i = 0; i < list.size(); i++) {

                result += list.get(i).getId() + "\t" + map.get(list.get(i).getPid()) + "\t" + list.get(i).getCount() + "\r"+"\n";

            }

            context.write(new Text(result), NullWritable.get());

        } catch (IOException e) {

            e.printStackTrace();

        } catch (InterruptedException e) {

            e.printStackTrace();

        } catch (IllegalAccessException e) {

            e.printStackTrace();

        } catch (InvocationTargetException e) {

            e.printStackTrace();

        }

    }

}
