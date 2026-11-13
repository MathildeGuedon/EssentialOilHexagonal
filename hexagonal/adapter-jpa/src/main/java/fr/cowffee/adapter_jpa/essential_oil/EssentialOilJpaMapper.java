package fr.cowffee.adapter_jpa.essential_oil;

import fr.cowffee.adapter_jpa.medicinal_property.MedicinalPropertyJpaMapper;
import fr.cowffee.core_domain.model.EssentialOil;

public class EssentialOilJpaMapper {
    private EssentialOilJpaMapper() {
    }

    public static EssentialOil toDomain(EssentialOilJPA oilJPA) {
        return new EssentialOil(
                oilJPA.getId(),
                oilJPA.getName(),
                MedicinalPropertyJpaMapper.toDomainProperties(oilJPA.getPropertySet())
        );
    }

    public static EssentialOilJPA toEntity(EssentialOil oil) {
        return new EssentialOilJPA(
                oil.getId(),
                oil.getName(),
                MedicinalPropertyJpaMapper.toEntityProperties(oil.getPropertySet())
        );
    }
}
