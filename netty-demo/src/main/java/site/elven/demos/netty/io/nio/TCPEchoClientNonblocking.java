package site.elven.demos.netty.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author elven
 */
public class TCPEchoClientNonblocking {
    public static void main(String[] args) throws IOException {
        if ((args.length < 2) || (args.length > 3)) {
            args = new String[]{"127.0.0.1", "hello nio socket", "8888"};
        }
        //第一个参数作为要连接的服务端的主机名或IP
        String server = args[0];
        //第二个参数为要发送到服务端的字符串
        byte[] argument = args[1].getBytes();
        //如果有第三个参数，则作为端口号，如果没有，则端口号设为7
        int serverPort = (args.length == 3) ? Integer.parseInt(args[2]) : 7;
        //创建一个信道，并设为非阻塞模式
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        //向服务端发起连接
        if (!socketChannel.connect(new InetSocketAddress(server, serverPort))){
            //不断地轮询连接状态，直到完成连接
            while (!socketChannel.finishConnect()){
                //在等待连接的时间里，可以执行其他任务，以充分发挥非阻塞IO的异步特性
                //这里为了演示该方法的使用，只是一直打印"."
                System.out.print(".");
            }
        }
        //为了与后面打印的"."区别开来，这里输出换行符
        System.out.print("\n");
        //分别实例化用来读写的缓冲区
        ByteBuffer writeBuffer = ByteBuffer.wrap(argument);
        ByteBuffer readBuffer = ByteBuffer.allocate(argument.length);
        //接收到的总的字节数
        int totalBytesReceived = 0;
        //每一次调用read（）方法接收到的字节数
        int bytesReceived;
        //循环执行，直到接收到的字节数与发送的字符串的字节数相等
        while (totalBytesReceived < argument.length){
            //如果用来向通道中写数据的缓冲区中还有剩余的字节，则继续将数据写入信道
            if (writeBuffer.hasRemaining()){
                socketChannel.write(writeBuffer);
            }
            //如果read（）接收到-1，表明服务端关闭，抛出异常
            if ((bytesReceived = socketChannel.read(readBuffer)) == -1){
                throw new SocketException("Connection closed prematurely");
            }
            //计算接收到的总字节数
            totalBytesReceived += bytesReceived;
            //在等待通信完成的过程中，程序可以执行其他任务，以体现非阻塞IO的异步特性
            //这里为了演示该方法的使用，同样只是一直打印"."
            System.out.print(".");
        }
        //打印出接收到的数据
        System.out.println("Received: " +  new String(readBuffer.array(), 0, totalBytesReceived));
        //关闭信道
        socketChannel.close();
    }
}
