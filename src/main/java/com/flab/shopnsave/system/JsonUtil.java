package com.flab.shopnsave.system;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJsonString(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch(JsonProcessingException exception){
            throw new RuntimeException(exception.getCause());
        }
    }

    public static<T> T toObject(String key, Class<T> valueType) {
        try {
            return objectMapper.readValue(key, valueType);
        } catch(JsonProcessingException exception){
            throw new RuntimeException(exception.getCause());
        }
    }
}