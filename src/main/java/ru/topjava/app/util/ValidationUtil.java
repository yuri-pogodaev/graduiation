package ru.topjava.app.util;

import java.util.UUID;

public class ValidationUtil {
    public static <T> T checkNotFoundWithId(T object, UUID uuid) {
        return checkNotFound(object, "id=" + uuid);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }
}