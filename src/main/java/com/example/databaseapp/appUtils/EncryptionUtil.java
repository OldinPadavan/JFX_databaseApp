package com.example.databaseapp.appUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionUtil {

    public String encryptString(String inputString) {
        String returnString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte [] messageDigest = md.digest(inputString.getBytes());
            BigInteger bigInteger = new BigInteger(1, messageDigest);
            returnString = bigInteger.toString(16);
            return returnString;
        } catch (NoSuchAlgorithmException exception) {
           throw new RuntimeException("Couldn't encrypt input string", exception);
        } finally {
            return returnString;
        }
    }
}
