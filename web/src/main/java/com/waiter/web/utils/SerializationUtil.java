package com.waiter.web.utils;

import java.io.*;

/**
 * @ClassName SerializationUtil
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/9 11:17
 * @Version 1.0
 */
public class SerializationUtil {
    public static byte[] toBytes(Serializable obj) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        ) {
            objectOutputStream.writeObject(obj);
            return byteArrayOutputStream.toByteArray();
        }
    }

    public static Serializable toObj(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        ) {
            Object o = objectInputStream.readObject();
            return (Serializable) o;
        }
    }
}
