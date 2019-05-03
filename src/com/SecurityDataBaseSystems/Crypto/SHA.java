package com.SecurityDataBaseSystems.Crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SHA {

    public String StringToSHA1(String text) //Хеширование SHA-1
    {
        StringBuilder builder = new StringBuilder();

        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            byte [] bytes = sha1.digest(text.getBytes());
            for (byte b: bytes)
                builder.append(String.format("%02X", b).toLowerCase());
        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        return 	builder.toString().toUpperCase();
    }

}
