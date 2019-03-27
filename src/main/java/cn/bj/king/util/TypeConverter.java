package cn.bj.king.util;

import org.springframework.beans.BeanUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ARongking
 * @date 2018/5/2
 */
public class TypeConverter {

    protected static <TARGET, SOURCE> void convert(SOURCE from, TARGET to, @Nullable String... ignoreProperties) {
        BeanUtils.copyProperties(from, to);
    }

    public static <TARGET, SOURCE> TARGET convert(SOURCE from, Class<TARGET> clazz, @Nullable String... ignoreProperties) {

        if (from == null) {
            return null;
        }

        TARGET to = null;

        try {
            to = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        convert(from, to, ignoreProperties);

        return to;
    }

    public static <TARGET, SOURCE> List<TARGET> convert(List<SOURCE> fromList, Class<TARGET> clazz, @Nullable String... ignoreProperties) {
        if (CollectionUtils.isEmpty(fromList)) {
            return null;
        }

        List<TARGET> toList = new ArrayList<>();

        for (SOURCE from : fromList) {
            toList.add(convert(from, clazz, ignoreProperties));
        }

        return toList;
    }

    public static <TARGET, SOURCE> TARGET marge(TARGET to, SOURCE... fromList) {
        if (fromList == null) {
            return null;
        }

        for (SOURCE from : fromList) {
            if (from == null) {
                return null;
            }

            convert(from, to);
        }

        return to;
    }

    public static <TARGET, SOURCE> TARGET marge(Class<TARGET> clazz, SOURCE... fromList) {
        TARGET to = null;

        try {
            to = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return marge(to, fromList);
    }

    public static Object fill(Map<String, String> data, Class<?> clazz) {
        Object to = null;
        try {
            to = clazz.newInstance();

            Field[] fields = clazz.getDeclaredFields();




        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return to;
    }
}
