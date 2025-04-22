package com.example.demo.core.logger;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class UppercaseLevelLoggerEncoder extends PatternLayoutEncoder {

    @Override
    public void start() {
        PatternLayout patternLayout = new PatternLayout() {
            @Override
            public String doLayout(ILoggingEvent event) {
                String original = super.doLayout(event);
                // فقط سطح لاگ و نام لاگر را به حروف بزرگ تبدیل کن
                String level = event.getLevel().toString().toUpperCase();
                String logger = event.getLoggerName().toUpperCase();

                return original
                        .replace(event.getLevel().toString(), level)
                        .replace(event.getLoggerName(), logger);
            }
        };
        patternLayout.setContext(context);
        patternLayout.setPattern(getPattern());
        patternLayout.start();
        this.layout = patternLayout;
        super.start();
    }

}