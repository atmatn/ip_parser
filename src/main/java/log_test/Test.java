package log_test;

public class Test {
    public static void main(String[] args) {
        StringBuffer t=new StringBuffer("abcdanbcd");
        //System.out.println(t.indexOf("a"));
        String log="xyz";
        t.replace(0,"abc".length(),log);
        System.out.println(t.toString());
      }
}
