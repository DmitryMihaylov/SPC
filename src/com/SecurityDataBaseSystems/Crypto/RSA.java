package com.SecurityDataBaseSystems.Crypto;

import java.security.*;

public class RSA {

    public Key PublicKey;	 //Публичный ключ (RSA)
    public Key PrivateKey;  //Закрытый ключ (RSA)

    public void GenerateKeyPairRSA()
    {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048); //Длина ключа шифрования
            KeyPair kp = kpg.generateKeyPair();

            PublicKey = kp.getPublic();
            PrivateKey = kp.getPrivate();

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }

    }

}
