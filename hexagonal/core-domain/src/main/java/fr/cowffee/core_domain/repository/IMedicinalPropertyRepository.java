package fr.cowffee.core_domain.repository;

import fr.cowffee.core_domain.model.MedicalProperty;

import java.util.List;

public interface IMedicalPropertyRepository {
    List<MedicalProperty> findAll();

    MedicalProperty findById(Long id);

    /**
     * return false lorsque id est null ou n'existe pas
     */
    boolean deleteById(Long id);

    MedicalProperty save(MedicalProperty prop);
}
