package com.example.demo.core.exceptionHandler;

import java.util.Locale;

public enum ResponseLangType {
    FA(new Locale("fa")), EN(Locale.ENGLISH),
    AR(new Locale("ar")), CH(new Locale("zh", "CN"));

    private Locale locale;

    private ResponseLangType(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocaleLanguage() {
        return locale;
    }

}
