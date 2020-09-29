package com.internet.shop.util;

import com.internet.shop.exceptions.UnsecuredPasswordStoring;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashUtil {
    private static final String HASHING_ALGORITHM = "SHA-512";

    public static byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static String hashPassword(String password, byte[] salt) {
        StringBuilder hashedPassword = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASHING_ALGORITHM);
            messageDigest.update(salt);
            byte[] digest = messageDigest.digest(password.getBytes());
            for (byte word : digest) {
                hashedPassword.append(String.format("%02x", word));
            }
            return hashedPassword.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new UnsecuredPasswordStoring("Password hashing failed! ", e);
        }
    }
}
