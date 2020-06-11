package com.wheny.whenylibrary.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class JsonUtils {
    public static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }
}
