package dev.mattrm.mc.modularmachines.common.util;

public class CastUtils {
    @SuppressWarnings("unchecked")
    public static <T> Class<T> generify(Class<?> cls) {
        return (Class<T>) cls;
    }
}
