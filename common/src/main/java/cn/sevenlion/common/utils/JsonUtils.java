package cn.sevenlion.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author: qimeiwen
 * @create: 2021-11-19
 */
public class JsonUtils {

    public static ObjectMapper objectMapper = SpringApplicationContext.getBean(ObjectMapper.class);

    public static String obj2Str(Object obj) {
        String str = null;
        try {
            str = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static <T> T str2List(String str, Class<T> clazz) {
        JavaType javaType = objectMapper.getTypeFactory().constructType(clazz);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T str2Obj(String str, Class<T> clazz) {
        return objectMapper.convertValue(str, clazz);
    }

}
