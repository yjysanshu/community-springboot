package com.lxl.common.util.encrypt;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.Arrays;

public class BASE {
    public static String encrypt(String str) {
       return (new BASE64Encoder()).encodeBuffer(str.getBytes());
    }

    public static String decrypt(String str) throws IOException {
        return Arrays.toString((new BASE64Decoder()).decodeBuffer(str));
    }
}
