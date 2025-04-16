package com.example.demo.core.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Locale;

public class LanguageFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;

            String langHeader = httpRequest.getHeader("Accept-Language");
            Locale locale = (langHeader != null && !langHeader.isBlank())
                    ? Locale.forLanguageTag(langHeader)
                    : Locale.getDefault();

            RequestLanguageContext.set(locale);
            chain.doFilter(request, response);
        } finally {
            RequestLanguageContext.clear();
        }
    }

}
