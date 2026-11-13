package fr.cowffee.hexagonal.config;

import fr.cowffee.core_domain.locale.IMessagePort;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class SpringMessagePort implements IMessagePort {
    private final MessageSource messageSource;

    public SpringMessagePort(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String i18n(String key) {

        return messageSource.getMessage(
                key, null, key, Locale.FRENCH
        );
    }
}
