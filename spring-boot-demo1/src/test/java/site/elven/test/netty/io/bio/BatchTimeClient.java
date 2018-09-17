package site.elven.test.netty.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static site.elven.test.util.PrintUtil.println;

public class BatchTimeClient {
    public static void main(String[] args) {
        int port = 8080;

        for(int i=0; i<10000; i++){
            Socket socket = null;

            BufferedReader in = null;
            PrintWriter out = null;

            try {
                socket = new Socket("127.0.0.1", port);

                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                out.println("query current time");

                println("Send message to server succeed");

                String resp = in.readLine();
                println("Now is: "+resp);

            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if(in != null){
                    try {
                        in.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

                if(out != null){
                    out.close();
                    out = null;
                }

                if(socket != null){
                    try {
                        socket.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    socket = null;
                }
            }
        }

    }
}
