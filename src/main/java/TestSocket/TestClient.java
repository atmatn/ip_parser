package TestSocket;

import java.io.*;
import java.net.Socket;

public class TestClient {
    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 8001);

        OutputStream os = socket.getOutputStream();

        PrintWriter pw = new PrintWriter(os);

        pw.write("Hello World!");

        pw.flush();

        socket.shutdownOutput();

        InputStream is = socket.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String info = null;
        while ((info=br.readLine())!= null) {
            System.out.println("From Server: " + info);
        }

        br.close();
        is.close();
        pw.close();
        os.close();
        socket.close();

    }
}
