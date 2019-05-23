package other;

public class TestInterfect {
    public static void main(String[] args) {
        check c=new docheck();
        c.log();
    }
}
interface check{
    void log();
}
class docheck implements check{

    public void log() {
        System.out.println("hello");
    }
}