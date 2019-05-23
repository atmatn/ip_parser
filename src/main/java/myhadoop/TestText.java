package myhadoop;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.nio.charset.CharacterCodingException;

public class TestText {
    public static void main(String[] args) {
        Text text=new Text();
        text.set("hello");
//        text.getInputSplit();
        System.out.println(text);
        System.out.println(text.find("ll"));
        byte[] b=new byte[]{2,3};
        try {
            System.out.println(text.decode(b));
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
    }
}
