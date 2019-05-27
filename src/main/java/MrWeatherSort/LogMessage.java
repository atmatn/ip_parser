package MrWeatherSort;

public class LogMessage {

    static StringBuffer message=new StringBuffer("");

    public static StringBuffer getMessage() {
        return message;
    }

    public static void setMessage(String s) {
       message.append(s+"\n");
    }

}
