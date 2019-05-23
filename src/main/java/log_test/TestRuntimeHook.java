package log_test;

public class TestRuntimeHook {
    public static void main(String[] args) {
        new DoThingsWhenJVMEnd().doThing();

    }

}

class DoThingsWhenJVMEnd {
    public void doThing() {
        Runtime.getRuntime().addShutdownHook(
                new Thread(){

                }
        );
    }
    public boolean  result(){
        System.out.println("END");
        return  true;
    }
}