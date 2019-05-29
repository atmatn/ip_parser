package other;

public abstract class TestInterfect {
    public static void main(String[] args) {
        check c=new docheck();
        c.log();
    }

    public abstract void getMes();
}
interface check{
    void log();
}
class docheck implements check{

    public void log() {
        System.out.println("hello");
    }
}