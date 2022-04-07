package com.example.tests;

import org.apache.jute.BinaryInputArchive;
import org.apache.jute.BinaryOutputArchive;

import javax.net.ServerSocketFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @author chengtong
 * @date 2020/7/13 10:16
 */
public class JuteTest {

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        byte array[] = null;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BinaryOutputArchive boa = BinaryOutputArchive.getArchive(baos);

        boa.writeInt(-1, "len");
//        new PicInfo(10, 100, "http://xxx.jpg", "11111-22222-3333-444",
//                "ITEM", 0).serialize(boa, "tag" + 1);
        array = baos.toByteArray();
        long endTime = System.currentTimeMillis();
        for (byte b :array) {
            System.out.print(b);
        }
        System.out.println("");
        System.out.println("字节数大小:" + array.length + ",序列化花费时间:"
                + (endTime - startTime) + "ms");

        ByteBuffer byteBuffer = ByteBuffer.wrap(baos.toByteArray());

        byteBuffer.putInt(byteBuffer.capacity()-4);


//        for (int i = 0; i < 50000; i++) {
//            ByteArrayInputStream bais = new ByteArrayInputStream(array);
//            BinaryInputArchive bia = BinaryInputArchive.getArchive(bais);
//            PicInfo newBean = new PicInfo();
//            newBean.deserialize(bia, "tag1");
//        }
//        long endTime2 = System.currentTimeMillis();
//        System.out.println("反序列化花费时间:" + (endTime2 - endTime) + "ms");
    }

}
