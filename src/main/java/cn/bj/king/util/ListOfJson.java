package cn.bj.king.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class ListOfJson<T> implements ParameterizedType {

    private Class<?> mType;

    public ListOfJson(Class<T> pType) {
        this.mType = pType;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return new Type[]{mType};
    }

    @Override
    public Type getOwnerType() {
        return null;
    }

    @Override
    public Type getRawType() {
        return List.class;
    }
}