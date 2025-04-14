package com.example.demo.core.config;

import java.util.Locale;

public class RequestLanguageContext {
    private static final ThreadLocal<Locale> LANG_CONTEXT = new ThreadLocal<>();

    public static void set(Locale locale) {
        LANG_CONTEXT.set(locale);
    }

    public static Locale get() {
        return LANG_CONTEXT.get() != null ? LANG_CONTEXT.get() : Locale.getDefault();
    }

    public static void clear() {
        LANG_CONTEXT.remove();
    }
}
