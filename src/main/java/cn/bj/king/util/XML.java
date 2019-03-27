package cn.bj.king.util;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.util.List;

public class XML {
    private static BaseConverter converter = new BaseConverter(new XmlMapper());

    public static <T> T parse(String source, Class<T> clazz) {
        return converter.parse(source, clazz);
    }

    public static <T> T parse(String source, T defaultValue, Class<T> clazz) {
        try {
            return parse(source, clazz);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static <T> List<T> parseList(String source, final Class<T> clazz) {
        return converter.parseList(source, clazz);
    }

    public static <T> List<T> parseList(String source, List<T> defaultValue, final Class<T> clazz) {
        try {
            return parseList(source, clazz);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static String stringify(Object source) {
        return converter.stringify(source);
    }

    public static String stringify(Object source, String defaultValue) {
        try {
            return converter.stringify(source);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
