package site.elven.test.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static site.elven.test.util.PrintUtil.*;

public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        ServerSocket server = null;

        try {
            server = new ServerSocket(port);
            println("The time server is start in port: "+port);
            Socket socket = null;
            while (true){
                socket = server.accept();

                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(server != null){
                try {
                    println("The time server close");
                    server.close();
                    server = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
