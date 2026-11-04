package fr.cowffee.core_domain.repository;

import fr.cowffee.core_domain.model.MedicinalProperty;

import java.util.List;

public interface MedicinalPropertyRepositoryCore {
    List<MedicinalProperty> findAll();

    MedicinalProperty findById(Long id);

    /**
     * return false lorsque id est null ou n'existe pas
     */
    boolean deleteById(Long id);

    MedicinalProperty save(MedicinalProperty prop);
}
