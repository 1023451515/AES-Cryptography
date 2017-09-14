package javaaes;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class javaaes {
    public static void main(String[] args) throws Exception{
        byte[] a = AesEncrypt("f51-*f4ad".getBytes(),"d82f*a+df");
        System.out.println(new String(Base64.getEncoder().encodeToString(a)));
    }
    //AES����
    public static byte[] AesEncrypt(byte[] byteContent, String password) throws Exception {
        
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(password.getBytes());
        kgen.init(128, secureRandom);

        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();//AES����ʵ�ʵ�Keyֵ        
        //���ֱ��enCodeFormat=password.getBytes(),��.NETֱ�ӾͿ��Խ���

        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");

        Cipher cipher = Cipher.getInstance("AES");

        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(byteContent);
    }
    //AES����
    public static byte[] AesDecrypt(byte[] byteContent, String password) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(password.getBytes());
        kgen.init(128, secureRandom);
        
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(byteContent);
    }

}