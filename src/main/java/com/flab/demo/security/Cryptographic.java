package com.flab.demo.security;

public interface Cryptographic {
    String digest(String value, String algorithm);
}
