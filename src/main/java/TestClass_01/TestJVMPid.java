package TestClass_01;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class TestJVMPid {
    public static int a=5;
}
class TestJVMPid_test01{
    public static void main(String[] args) {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        String name = runtimeMXBean.getName();
        System.out.println("name:"+name+"\t"+TestJVMPid.a);
    }
}
class TestJVMPid_test02{
    public static void main(String[] args) {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        String name = runtimeMXBean.getName();
        TestJVMPid.a++;
        System.out.println("name:"+name+"\t"+TestJVMPid.a);
    }
}