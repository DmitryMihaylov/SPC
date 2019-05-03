package com.SecurityDataBaseSystems.Crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/** АЛГОРИТМ ШИФРОВАНИЯ
 * AES - Симметричный алгоритм блочного шифрования,
 * принятый в качестве стандарта шифрования правительством США
 * по результатам конкурса AES.
 *
 */

public class AES {

    private final static String HEX = "0123456789ABCDEF";

    static  String  BytesToString(byte[] bytes) {
       StringBuilder SB = new StringBuilder();
       for (int i=0; i<bytes.length; i++)
            SB.append(bytes[i]+" ");
    return SB.toString();
    }

    public static byte[] encrypt(String key, byte[] data, String iv) {

        final byte[]keyBytes=key.getBytes();

        final byte[] ivBytes=iv.getBytes();

        try {
            byte[] result;
            SecretKeySpec sks=new SecretKeySpec(keyBytes,"AES");

            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, sks, new IvParameterSpec(ivBytes));
            result=c.doFinal(data);

            return result;

        }
        catch (Exception e){
            System.out.println("Ошибка шифрования! - "+ e);
        }
        return null;
    }

    public static byte[] decrypt (String key, byte[]data, String iv) {
        byte[] result;


        final byte[]keyBytes=key.getBytes();


        final byte[]ivBytes=iv.getBytes();


        try {
            SecretKeySpec sks=new SecretKeySpec(keyBytes, "AES");

            Cipher c=Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE,sks, new IvParameterSpec(ivBytes));
            result=c.doFinal(data);

            return result;
        }
        catch (Exception e) {
            System.out.println("Ошибка дешифровки! - "+e);
        }
        return null;
    }

}