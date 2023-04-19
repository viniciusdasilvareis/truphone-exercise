package com.truphone.device.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.List;

public class TestUtils {

    public static String objectToJson(Object obj) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(obj);
    }

    public static <T> T jsonToObject(String json, Class<T> classOf) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, classOf);
    }

    @SuppressWarnings("rawtypes")
    public static List jsonToList(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonArray = objectMapper.readValue(json, JsonNode.class);
        String jsonArrayAsString = objectMapper.writeValueAsString(jsonArray);
        return objectMapper.readValue(jsonArrayAsString, new TypeReference<>() {});
    }
}
