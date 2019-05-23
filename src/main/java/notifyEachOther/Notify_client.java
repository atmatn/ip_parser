package notifyEachOther;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import static com.sun.deploy.trace.Trace.flush;

public class Notify_client {
    public static void main(String[] args) {

        String clientName="Client:\t";

        Scanner scanner=new Scanner(System.in);

        try {

            final Socket socket = new Socket("localhost", 8001);

            final OutputStream outputStream = socket.getOutputStream();

            final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

            // outputStreamWriter.write("oneLine!");

            String message=new String();

            while((message=scanner.next()) !="exit"){

                outputStreamWriter.write(message);

                System.out.println(message);

                flush();

                message=null;

            }

            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    try {

                        outputStreamWriter.close();

                        outputStream.close();

                        socket.close();

                    } catch (IOException e) {

                        e.printStackTrace();

                    }

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
