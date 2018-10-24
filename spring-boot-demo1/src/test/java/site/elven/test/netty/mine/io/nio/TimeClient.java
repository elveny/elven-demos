package site.elven.test.netty.mine.io.nio;

public class TimeClient {
    public static void main(String[] args) {
        int port = 8080;
        new Thread(new TimeClientHandle(null, port), "TimeClient-001").start();
    }
}

