package test_ETL_Log;

public class Mergeog {

    static StringBuffer logMessage=new StringBuffer();

    public static StringBuffer getLogMessage() {
        return logMessage;
    }

    public static void setLogMessage(StringBuffer logMessage) {
        Mergeog.logMessage = logMessage;
    }
}
