package com.flab.demo.security;

import org.springframework.stereotype.Component;

@Component
public interface Cryptographic {

    String digest(String value, String algorithm);
}
