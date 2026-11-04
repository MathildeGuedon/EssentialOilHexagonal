package fr.cowffee.core_domain.repository;

import fr.cowffee.core_domain.model.EssentialOil;

import java.util.List;

public interface EssentialOilRepositoryCore {
    List<EssentialOil> findAll();

    EssentialOil findById(Long id);

    /**
     * return false lorsque id est null ou n'existe pas
     */
    boolean deleteById(Long id);

    EssentialOil save(EssentialOil oil);
}
