package fr.cowffee.adapter_jpa.medicinal_property;

import fr.cowffee.core_domain.model.MedicinalProperty;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MedicinalPropertyJpaMapper {
    private static MedicinalProperty toDomain(MedicinalPropertyJPA propertyJPA) {
        return new MedicinalProperty(
                propertyJPA.getId(),
                propertyJPA.getTitle()
        );
    }

    public static Set<MedicinalProperty> toDomainProperties(Set<MedicinalPropertyJPA> propertyJPAList) {
        if (propertyJPAList == null) {
            return new HashSet<>();
        }

        return propertyJPAList.stream()
                .map(MedicinalPropertyJpaMapper::toDomain)
                .collect(Collectors.toCollection(HashSet::new));
    }

    private static MedicinalPropertyJPA toEntity(MedicinalProperty medicinalProperty) {
        return new MedicinalPropertyJPA(
                medicinalProperty.getId(),
                medicinalProperty.getTitle()
        );
    }

    public static Set<MedicinalPropertyJPA> toEntityProperties(Set<MedicinalProperty> propertySet) {
        if (propertySet == null){
            return new HashSet<>();
        }

        return propertySet.stream()
                .map(MedicinalPropertyJpaMapper::toEntity)
                .collect(Collectors.toCollection(HashSet::new));
    }
}
