package com.piaoyou.module.plugin;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

class Hook {

    @RequiresApi(api = Build.VERSION_CODES.N)

    public static void check(Object obj) {
        if (null == obj) return;
        System.out.println(obj.getClass());
        if (obj instanceof CharSequence ||
                obj instanceof Number ||
                obj instanceof Boolean) {
        } else if (obj instanceof List) {
            ((List<?>) obj).forEach((Consumer<Object>) o -> check(o));
        } else if (obj instanceof Map) {
            ((Map<?, ?>) obj).values().forEach((Consumer<Object>) o -> check(o));
        } else {
            throw new IllegalArgumentException("post args must be basic type");
        }
    }
}