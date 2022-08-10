package com.example.projectmonitoringapp;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Crypt {
    /**
     * AES ECB 加密
     * @param message 需要加密的字符串
     * @param key   密匙
     * @return  返回加密后密文，编码为base64
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String encryptECB(String message, String key) {
        final String cipherMode = "AES/ECB/PKCS5Padding";
        final String charsetName = "UTF-8";
        try {
            byte[] content = new byte[0];
            content = message.getBytes(charsetName);
            //
            byte[] keyByte = key.getBytes(charsetName);
            SecretKeySpec keySpec = new SecretKeySpec(keyByte, "AES");

            Cipher cipher = Cipher.getInstance(cipherMode);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] data = cipher.doFinal(content);
            final Base64.Encoder encoder = Base64.getEncoder();
            final String result = encoder.encodeToString(data);
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return null;
    }
    /**
     * AES ECB 解密
     * @param messageBase64 密文，base64编码
     * @param key   密匙，和加密时相同
     * @return  解密后数据
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String decryptECB(String messageBase64, String key) {
        final String cipherMode = "AES/ECB/PKCS5Padding";
        final String charsetName = "UTF-8";
        try {
            final Base64.Decoder decoder = Base64.getDecoder();
            byte[] messageByte = decoder.decode(messageBase64);

            //
            byte[] keyByte = key.getBytes(charsetName);
            SecretKeySpec keySpec = new SecretKeySpec(keyByte, "AES");

            Cipher cipher = Cipher.getInstance(cipherMode);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] content = cipher.doFinal(messageByte);
            String result = new String(content, charsetName);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private static final String RSA = "RSA";
    private static final String UTF8 = "utf-8";
    /**
     * 私钥加密
     *
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String encrypt(String str, String publicKey) throws Exception {
        /**base64解码公钥*/
        byte[] decoded = Base64.getDecoder().decode(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(RSA).generatePublic(new X509EncodedKeySpec(decoded));
        /**RSA加密*/
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] content = cipher.doFinal(str.getBytes(UTF8));
        /**base64将字节转字符*/
        String outStr = Base64.getEncoder().encodeToString(content);
        return outStr;
    }
    /**
     * 随机生成密钥
     *
     */
    //length用户要求产生字符串的长度
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

}
