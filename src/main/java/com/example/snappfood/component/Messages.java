package com.example.snappfood.component;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Messages implements MessageProvider {

    private final MessageSource messageSource;
    private static final Locale DEFAULT_LOCALE = new Locale("i18n");
    private static ApplicationContext applicationContext;

    public Messages(ApplicationContext applicationContext, MessageSource messageSource) {
        Messages.applicationContext = applicationContext;
        this.messageSource = messageSource;
    }

    public static Messages getInstance() {
        return applicationContext.getBean(Messages.class);
    }

    @Override
    public String getMessage(String code) {
        return StringEscapeUtils.unescapeJava(messageSource.getMessage(code, null, DEFAULT_LOCALE));
    }

    @Override
    public String getMessage(String code, Object... args) {
        return StringEscapeUtils.unescapeJava(messageSource.getMessage(code, args, DEFAULT_LOCALE));
    }

}