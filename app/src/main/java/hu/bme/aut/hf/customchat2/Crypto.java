package hu.bme.aut.hf.customchat2;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Balázs on 2015.05.17..
 */
public class Crypto {
    private static MessageDigest md5;
    private static byte[] bytesOfPass;

    public static String genHash(String input){
        try {

            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            bytesOfPass = input.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] digest = md5.digest(bytesOfPass);
        BigInteger bigInt = new BigInteger(1,digest);
        String hashtext = bigInt.toString(16);
        return hashtext;
    }

    public static boolean checkHash(String input, String referencehash){
        if (genHash(input).equals(referencehash))
            return true;
        else return false;
    }
}
