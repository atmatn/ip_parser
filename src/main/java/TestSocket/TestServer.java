package TestSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {
    public static void main(String[] args) throws Exception {
        ServerSocket s = new ServerSocket(8001);//建立服务器来监听某个端口号

        Socket socket = s.accept();//监听客户端

        InputStream is = socket.getInputStream();

        InputStreamReader isr = new InputStreamReader(is);

        BufferedReader br = new BufferedReader(isr);

        String info = null;

        while ((info=br.readLine()) != null) {

            System.out.println("From Clinet:" + info);
        }

        socket.shutdownInput();//close

        OutputStream os = socket.getOutputStream();

        PrintWriter pw = new PrintWriter(os);

        pw.write("welcome!");
        pw.flush();
        pw.close();
        os.close();
        br.close();
        isr.close();
        is.close();
        socket.close();
        s.close();
    }
}
