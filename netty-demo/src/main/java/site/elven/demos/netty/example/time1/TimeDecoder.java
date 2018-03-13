package site.elven.demos.netty.example.time1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class TimeDecoder extends ByteToMessageDecoder {// (1)
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception { // (2)
        if (in.readableBytes() < 4) {
            return; // (3)
        }

//        out.add(in.readBytes(4)); // (4)
        out.add(new UnixTime(in.readUnsignedInt()));
    }
}