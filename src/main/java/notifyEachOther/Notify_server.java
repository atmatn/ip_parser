package notifyEachOther;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Notify_server {
    public static void main(String[] args) {

        String serverName="Server:\t";

        try {

            ServerSocket serverSocket = new ServerSocket(8001); //create socket object

            final Socket socket=serverSocket.accept();

            final InputStream inputStream = socket.getInputStream();

            final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;

            while ((line=bufferedReader.readLine()) != "exit") {

                System.out.println(line);

            }



            Runtime.getRuntime().addShutdownHook(new Thread() {

                @Override
                public void run() {
                    try {
                        bufferedReader.close();

                        inputStreamReader.close();

                        inputStream.close();

                        socket.close();

                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
