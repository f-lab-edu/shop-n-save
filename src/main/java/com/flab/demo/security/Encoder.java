package com.flab.demo.security;

public interface Encoder {

    String encrypt(String value, Cryptographic cryptographic);
}
