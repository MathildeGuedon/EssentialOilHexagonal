package fr.cowffee.hexagonal.locale;

import fr.cowffee.adapter_jpa.MedicinalPropertyJpaAdapter;
import fr.cowffee.adapter_jpa.MedicinalPropertyRepositoryJPA;
import fr.cowffee.core_domain.locale.IMessagePort;
import fr.cowffee.core_domain.repository.MedicinalPropertyRepositoryCore;
import fr.cowffee.core_domain.service.MedicinalPropertyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.util.Locale;

@Configuration
public class LocaleConfig {
    @Bean
    public LocaleResolver localeResolver() {
        return new FixedLocaleResolver(Locale.FRENCH);
    }

    @Bean
    public MedicinalPropertyRepositoryCore medicinalPropertyRepositoryCore(
            MedicinalPropertyRepositoryJPA repository) {
        return new MedicinalPropertyJpaAdapter(repository);
    }

    @Bean
    public MedicinalPropertyService medicinalPropertyService(
            MedicinalPropertyRepositoryCore repository,
            IMessagePort messagePort) {
        return new MedicinalPropertyService(repository, messagePort);
    }
}
