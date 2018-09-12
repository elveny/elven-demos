package site.elven.test.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import static site.elven.test.util.PrintUtil.*;

public class TimeServerHandler implements Runnable {
    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);

            String currentTime = null;
            String body = null;
            while (true){
                body = in.readLine();
                if(body == null){
                    break;
                }

                println(Thread.currentThread().getName()+":The server receive body: "+body);

                currentTime = "query current time".equals(body) ? new Date().toString() : "BAD BODY";

                out.println(currentTime);
            }


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

            if(this.socket != null){
                try {
                    this.socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                this.socket = null;
            }
        }
    }
}
