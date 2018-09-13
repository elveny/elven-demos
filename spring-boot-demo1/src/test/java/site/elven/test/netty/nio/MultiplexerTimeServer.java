package site.elven.test.netty.nio;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import static site.elven.test.util.PrintUtil.*;

public class MultiplexerTimeServer implements Runnable {
    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private volatile boolean stop;

    public MultiplexerTimeServer(int port) {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            println("The time server is start in port: "+port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void stop(){
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop){
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                SelectionKey key = null;
                while (iterator.hasNext()){
                    key = iterator.next();
                    iterator.remove();
                    try {
                        handleInput(key);
                    }
                    catch (Exception e){
                        if(key != null){
                            key.cancel();
                            if(key.channel() != null){
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if(key.isValid()){
            if(key.isAcceptable()){
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                sc.register(selector, SelectionKey.OP_READ);
            }

            if(key.isReadable()){
                SocketChannel sc = (SocketChannel) key.channel();

                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if(readBytes > 0){
                    // 读到正常字节
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String request = new String(bytes, "UTF-8");
                    println(String.format("The time server receive request: %s",  request));

                    String currentTime = "query current time".equals(request) ? new Date().toString() : "BAD REQUEST";

                    doWrite(sc, currentTime);
                }
                else if(readBytes < 0){
                    // readBytes = -1：链路已关闭
                    key.cancel();
                    sc.close();
                }
                else {
                    // 读到0字节，忽略
                }

            }
        }
    }

    private void doWrite(SocketChannel channel, String response) throws IOException {
        if(StringUtils.isNotBlank(response)){
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }
}
