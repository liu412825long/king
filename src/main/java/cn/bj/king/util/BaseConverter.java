package cn.bj.king.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;

public class BaseConverter {
    private ObjectMapper mapper;

    public BaseConverter(ObjectMapper mapper) {
        this.mapper = mapper;
        this.mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public <T> T parse(String source, Class<T> clazz) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }

        JavaType type = mapper.getTypeFactory().constructType(clazz);

        try {
            return mapper.readValue(source, type);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public <T> List<T> parseList(String source, final Class<T> clazz) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }

        JavaType type = mapper.getTypeFactory().constructType(new ListOfJson<T>(clazz));

        try {
            return mapper.readValue(source, type);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String stringify(Object source) {
        if (source == null) {
            return null;
        }

        try {
            return mapper.writeValueAsString(source);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
