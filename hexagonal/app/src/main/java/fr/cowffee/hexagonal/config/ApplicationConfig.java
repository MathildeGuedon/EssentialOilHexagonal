package fr.cowffee.hexagonal.config;

import fr.cowffee.adapter_jpa.essential_oil.EssentialOilJpaAdapter;
import fr.cowffee.adapter_jpa.essential_oil.EssentialOilRepositoryJPA;
import fr.cowffee.adapter_jpa.medicinal_property.MedicinalPropertyJpaAdapter;
import fr.cowffee.adapter_jpa.medicinal_property.MedicinalPropertyRepositoryJPA;
import fr.cowffee.core_domain.locale.IMessagePort;
import fr.cowffee.core_domain.repository.EssentialOilRepositoryCore;
import fr.cowffee.core_domain.repository.MedicinalPropertyRepositoryCore;
import fr.cowffee.core_domain.service.EssentialOilService;
import fr.cowffee.core_domain.service.MedicinalPropertyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.util.Locale;

@Configuration
public class ApplicationConfig {
    @Bean
    public LocaleResolver localeResolver() {
        return new FixedLocaleResolver(Locale.FRENCH);
    }

    @Bean
    public MedicinalPropertyRepositoryCore medicinalPropertyRepositoryCore(
            MedicinalPropertyRepositoryJPA propRepo, EssentialOilRepositoryJPA oilRepo) {
        return new MedicinalPropertyJpaAdapter(propRepo, oilRepo);
    }

    @Bean
    public EssentialOilRepositoryCore essentialOilRepositoryCore(EssentialOilRepositoryJPA oilRepo, MedicinalPropertyRepositoryJPA propRepo){
        return new EssentialOilJpaAdapter(oilRepo, propRepo);
    }

    @Bean
    public MedicinalPropertyService medicinalPropertyService(
            MedicinalPropertyRepositoryCore repository,
            IMessagePort messagePort) {
        return new MedicinalPropertyService(repository, messagePort);
    }

    @Bean
    EssentialOilService oilService(EssentialOilRepositoryCore repo, MedicinalPropertyService propService, IMessagePort messagePort) {
        return new EssentialOilService(repo,propService, messagePort);
    }
}
