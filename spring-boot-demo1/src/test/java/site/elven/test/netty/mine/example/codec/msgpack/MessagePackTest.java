package site.elven.test.netty.mine.example.codec.msgpack;

import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static site.elven.test.util.PrintUtil.println;

public class MessagePackTest {
    public static void main(String[] args) throws IOException {
        List<String> src = new ArrayList<>();
        src.add("marshalling");
        src.add("msgpack");
        src.add("pojo");
        src.add("protobuf");
        MessagePack messagePack = new MessagePack();
        byte[] raw = messagePack.write(src);

        List<String> dst = messagePack.read(raw, Templates.tList(Templates.TString));

        println("dst.get(0): " + dst.get(0));
        println("dst.get(1): " + dst.get(1));
        println("dst.get(2): " + dst.get(2));
        println("dst.get(3): " + dst.get(3));
    }
}
