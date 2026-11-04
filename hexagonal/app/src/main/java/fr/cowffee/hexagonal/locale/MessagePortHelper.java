package fr.cowffee.hexagonal.locale;

import fr.cowffee.core_domain.locale.IMessagePort;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessagePortHelper implements IMessagePort {
    private final MessageSource messageSource;

    public MessagePortHelper(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String i18n(String key) {
        return "";
    }
}
