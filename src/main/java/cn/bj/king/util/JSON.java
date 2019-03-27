package cn.bj.king.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Eric
 * @date 2018/2/15
 */
public class JSON {
    private static BaseConverter converter = new BaseConverter(new ObjectMapper());

    public static <T> T parse(String source, final Class<T> clazz) {
        return converter.parse(source, clazz);
    }

    public static <T> T parse(String source, T defaultValue, final Class<T> clazz) {
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
            return stringify(source);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static HashMap<String, Object> introspect(Object obj) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        BeanInfo info = Introspector.getBeanInfo(obj.getClass());
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            String key = pd.getName();

            if (key.equals("class")) {
                continue;
            }

            Method reader = pd.getReadMethod();
            if (reader != null) {

                Object value = reader.invoke(obj);

                if (value != null && (value.getClass() == ArrayList.class || value.getClass() == List.class || value.getClass() == LinkedList.class)) {
                    List<?> list = (List<?>) value;
                    ArrayList<HashMap<String, Object>> array = new ArrayList<>();
                    for (Object item : list) {
                        array.add(introspect(item));
                    }
                    value = array;
                }

                result.put(key, value);
            }
        }
        return result;
    }

}
