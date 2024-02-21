package com.github.loanshark.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.loanshark.exceptionhandling.exceptions.ConvertJsonException;

public class ConvertJsonUtil<T> {

    public ConvertJsonUtil() { }

    private static ObjectMapper mapper;

    public static String toJson(final Object object) {
        try {
            var mapper = getMapper();
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new ConvertJsonException(e.getMessage());
        }
    }

    public T toObject(final String json, final Class<T> clazz) {
        try {
            var mapper = getMapper();
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new ConvertJsonException(e.getMessage());
        }
    }

    private static ObjectMapper getMapper() {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }

        return mapper;
    }
}
