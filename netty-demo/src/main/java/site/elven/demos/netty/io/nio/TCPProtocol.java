package site.elven.demos.netty.io.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;

public interface TCPProtocol {
    /**
     * accept I/O形式
     * @param key
     * @throws IOException
     */
    void handleAccept(SelectionKey key) throws IOException;

    /**
     * read I/O形式
     * @param key
     * @throws IOException
     */
    void handleRead(SelectionKey key) throws IOException;

    /**
     * write I/O形式
     * @param key
     * @throws IOException
     */
    void handleWrite(SelectionKey key) throws IOException;
}
