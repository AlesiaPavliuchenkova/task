package com.task.task.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestUtil {

    public static byte[] convertObjectToByteArray(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

    public static Map<String, Long> getCollectionObjects(final MvcResult mvcResult) throws Exception {
        ObjectMapper jsonObjectMapper = new ObjectMapper();
        return jsonObjectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                new TypeReference<HashMap<String, Long>>(){{}});
    }
}
