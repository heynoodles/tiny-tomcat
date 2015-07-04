package socket;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author gaoxin.wei
 */
public class SocketTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 8080);

        boolean autoFlush = true;
        PrintWriter out = new PrintWriter(socket.getOutputStream(), autoFlush);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // send a Http request to server
        out.println("GET /index.jsp HTTP/1.1");
        out.println("Host: localhost:8080");
        out.print("Connection: Close");
        out.println();

        // read the response
        boolean loop = true;
        StringBuffer sb = new StringBuffer(8096);
        while (loop) {
            if (in.ready()) {
                int i = 0;
                while (i != -1) {
                    i = in.read();
                    sb.append((char)i);
                }
                loop =false;
            }
            Thread.currentThread().sleep(50);
        }

        // print the response to console
        System.out.println(sb.toString());
        socket.close();

    }
}
