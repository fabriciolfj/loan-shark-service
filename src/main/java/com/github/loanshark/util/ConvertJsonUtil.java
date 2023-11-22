package com.github.loanshark.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertJsonUtil {

    private ConvertJsonUtil() { }

    private static ObjectMapper mapper;

    public static String toJson(final Object object) {
        try {
            var mapper = getMapper();
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static ObjectMapper getMapper() {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }

        return mapper;
    }
}
