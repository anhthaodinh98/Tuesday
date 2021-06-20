package com.testarchitect.utils.helpers;

import java.util.HashMap;

public class CacheHelper {
    private static ThreadLocal<HashMap<String, Object>> CURRENT_CONTEXT = ThreadLocal.withInitial(HashMap::new);

    public static <T> T getValue(String key, Class<T> type) {
        return type.cast(CURRENT_CONTEXT.get().get(key));
    }

    public static void setValue(String key, Object value) {
        CURRENT_CONTEXT.get().put(key, value);
    }

    public static boolean containsKey(String key) {
        return CURRENT_CONTEXT.get().containsKey(key);
    }

    public static void cleanUp() {
        CURRENT_CONTEXT.remove();
    }

    public static String overwriteValueIfExist(String key) {
        return CURRENT_CONTEXT.get().containsKey(key) ? getValue(key, String.class) : key;
    }

    public static <T> String overwriteValueIfExist(String key, Class<T> type) {
        return CURRENT_CONTEXT.get().containsKey(key) ? String.valueOf(getValue(key, type)) : key;
    }
}