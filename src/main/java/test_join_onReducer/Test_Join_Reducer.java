package test_join_onReducer;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;

public class Test_Join_Reducer extends Reducer<Text, Table, Text, NullWritable> {

    //01,101 01  1
    //01,01  java
    //01,102 01  1
    //01,103 01  3

    String pid = null;

    Text result = new Text();

    ArrayList<Table> arrayList = new ArrayList<Table>();

    @Override
    protected void reduce(Text key, Iterable<Table> value, Context context) {

        for (Table table : value) {

            if (table.getPname() != null) {

                pid = table.getPname();

            } else {

                arrayList.add(table);

            }

        }

        if (arrayList.size() != 0) {

            for (int i = 0; i < arrayList.size(); i++) {

                result.set(arrayList.get(i).getId() + "\t" + pid + "\t" + arrayList.get(i).getCount() + "\r\n");

            }

        }

        try {
            context.write(result, NullWritable.get());

        } catch (IOException e) {

            e.printStackTrace();

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

    }

}
