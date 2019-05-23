package other;

import org.junit.Test;

public class TestClient {
    @Test
    public void apiMkdir() {
        final String TAB = " ";
        int from = 0;
        int to = 0;
        StringBuffer stringBuffer = new StringBuffer("23333 hadoop china python mysql");
        while (stringBuffer.indexOf(TAB) >= 0) {
            to = stringBuffer.indexOf(TAB);
            System.out.println(stringBuffer.substring(from, to));
            stringBuffer.replace(from, to + 1, "");
            to++;
        }
    }

    @Test
    public void doit() {
        String info = "2018/08/31 17:54:32.508 +0800 INFO [YanagishimaServer] [Yanagishima] Loading yanagishima settings file from bin/../conf\n" +
                "2018/08/31 17:54:32.509 +0800 INFO [YanagishimaServer] [Yanagishima] Loading yanagishima properties file\n" +
                "2018/08/31 17:54:33.444 +0800 INFO [YanagishimaServer] [Yanagishima] Yanagishima Server started...\n" +
                "2018/08/31 17:54:34.989 +0800 INFO [YanagishimaServer] [Yanagishima] Yanagishima Server running port 48082.";
        info.replace("[0-9]{4}/[0-9]{2}/[0-9]{2}", "\n[0-9]{4}/[0-9]{2}/[0-9]{2}");
        System.out.println(info);
    }
}
