package fr.cowffee.adapter_jpa.essential_oil;

import fr.cowffee.adapter_jpa.medicinal_property.MedicinalPropertyJPA;
import fr.cowffee.adapter_jpa.medicinal_property.MedicinalPropertyRepositoryJPA;
import fr.cowffee.core_domain.exception.MedicinalPropertyNotFoundException;
import fr.cowffee.core_domain.model.EssentialOil;
import fr.cowffee.core_domain.model.MedicinalProperty;
import fr.cowffee.core_domain.repository.EssentialOilRepositoryCore;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Profile("jpa")
public class EssentialOilJpaAdapter implements EssentialOilRepositoryCore {
    private final EssentialOilRepositoryJPA repository;
    private final MedicinalPropertyRepositoryJPA propRepo;

    public EssentialOilJpaAdapter(EssentialOilRepositoryJPA repository, MedicinalPropertyRepositoryJPA propRepo) {
        this.repository = repository;
        this.propRepo = propRepo;
    }

    @Override
    public List<EssentialOil> findAll() {
        return repository.findAll()
                .stream()
                .map(EssentialOilJpaMapper::toDomain)
                .toList();
    }

    @Override
    public EssentialOil findById(Long id) {
        return repository.findById(id).map(EssentialOilJpaMapper::toDomain).orElse(null);
    }

    @Override
    public boolean deleteById(Long id) {
        boolean exist = repository.existsById(id);
        if (!exist) return false;
        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public EssentialOil save(EssentialOil oilToSave) {
        Set<MedicinalPropertyJPA> properties = new HashSet<>();
        if(oilToSave.getPropertySet() != null){
            for(MedicinalProperty prop: oilToSave.getPropertySet()){
                if(prop.getId() == null){
                    throw new MedicinalPropertyNotFoundException("Chaque propriété médicinale doit avoir un identifiant associé.");
                }
                MedicinalPropertyJPA existingProp =
                        propRepo.findById(prop.getId())
                                .orElseThrow(() -> new MedicinalPropertyNotFoundException("propriété introuvable : " + prop.getTitle()));
                properties.add(existingProp);
            }
        }
        EssentialOilJPA oilToSaveJPA = new EssentialOilJPA(
                oilToSave.getId(),
                oilToSave.getName(),
                properties
        );
        EssentialOilJPA oilJPA = repository.save(oilToSaveJPA);
        return EssentialOilJpaMapper.toDomain(oilJPA);
    }
}
