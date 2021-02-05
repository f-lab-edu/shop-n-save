package com.flab.demo.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256 implements Hash {
    public static final String SHA_256 = "SHA-256";

    public String digest(String input, String algorithm) {
        MessageDigest md;

        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }

        byte[] digest = md.digest(input.getBytes());
        return bytesToHex(digest);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();

        for (byte data : bytes) {
            sb.append(String.format("%02x", data));
        }

        return sb.toString();
    }
}
