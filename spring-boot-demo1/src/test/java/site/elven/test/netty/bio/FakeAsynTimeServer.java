package site.elven.test.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static site.elven.test.util.PrintUtil.println;

public class FakeAsynTimeServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        ServerSocket server = null;

        try {
            server = new ServerSocket(port);
            println("The time server is start in port: "+port);
            Socket socket = null;

            ExecutorService executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), 50, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1000));

            while (true){
                socket = server.accept();

                executor.execute(new TimeServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(server != null){
                println("The time server close");
                server.close();
                server = null;
            }
        }
    }
}
