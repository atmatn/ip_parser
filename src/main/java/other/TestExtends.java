package other;

public class TestExtends extends TestInterfect {
    @Override
    public void getMes(){
        System.out.println("find it");
    }
}
class doTest{
    public static void main(String[] args) {
        TestExtends t=new TestExtends();
        t.getMes();
    }
}