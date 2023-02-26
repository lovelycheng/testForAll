package com.example.tests.raft.transfer;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.tests.raft.protocol.Serializer;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.Setter;

/**
 * @author chengtong
 * @date 2023/2/25 15:40
 *
 * 0-4: magicNum
 * 4-8: version
 * 8-12: 序列化类型type
 * 12-16: data长度
 * 16: body
 */
@Data
public class Packet implements Serializable {

    private Serializer serializer = new Serializer();
    @Data
    private static class Header {
        private int magicNum = 0xBABECAFE;//8 bit max 32
        private int magicVersion = 0x11110001;
        private int serialType = 0xAAAABBBB;
        //is this needed?
        private int dataLength;
    }

    private Header header;
    private Body body;

    @Data
    private static class Body {
        /**
         *
         */
        private Integer id;
        /**
         * 0: ping
         * 1: election
         * 2: broadCast
         */
        private int type;

        /**
         * ping : ping||pong
         * election : votes
         */
        private Object data;

    }

    public Packet() {
        this.header = new Header();
        this.body = new Body();
    }

    public Packet(Integer id, int type, Object data) {
        this.header = new Header();
        this.body = new Body();
        this.body.id = id;
        this.body.type = type;
        this.body.data = data;
    }

    @Override
    public String toString() {
        return "Packet{" + "header=" + header + ", body=" + body + '}';
    }

    public void encode(ByteBuf msg) {
        msg.writeInt(this.getHeader().magicNum);
        msg.writeInt(this.getHeader().magicVersion);
        msg.writeInt(this.getHeader().serialType);
        byte[] bytes = serializer.serialize(this.body);
        this.header.dataLength = bytes.length;
        msg.writeInt(this.getHeader().dataLength);
        msg.writeBytes(bytes);
    }

    public void decode(ByteBuf msg) {
        this.getHeader().magicNum = msg.readInt();
        this.getHeader().magicVersion = msg.readInt();
        this.getHeader().serialType = msg.readInt();
        this.getHeader().dataLength = msg.readInt();
        byte[] data = new byte[this.getHeader().dataLength];
        msg.readBytes(data, 0, data.length);
        this.body = serializer.deserialize(data,this.body.getClass());
    }

}
