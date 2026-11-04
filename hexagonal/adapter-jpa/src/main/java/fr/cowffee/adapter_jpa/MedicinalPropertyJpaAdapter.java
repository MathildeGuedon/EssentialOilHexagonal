package fr.cowffee.adapter_jpa;

import fr.cowffee.core_domain.model.MedicinalProperty;
import fr.cowffee.core_domain.repository.MedicinalPropertyRepositoryCore;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("jpa")
public class MedicinalPropertyJpaAdapter implements MedicinalPropertyRepositoryCore {
    private final MedicinalPropertyRepositoryJPA repository;

    public MedicinalPropertyJpaAdapter(MedicinalPropertyRepositoryJPA repository) {
        this.repository = repository;
    }

    @Override
    public List<MedicinalProperty> findAll() {
        List<MedicinalProperty> propList = new ArrayList<>();
        for(MedicinalPropertyJPA propJPA : repository.findAll()){
            propList.add(new MedicinalProperty(propJPA.getId(), propJPA.getTitle()));
        }
        return propList;
    }

    @Override
    public MedicinalProperty findById(Long id) {
        return repository.findById(id)
                .map(property -> new MedicinalProperty(property.getId(), property.getTitle()))
                .orElse(null);
    }

    @Override
    public boolean deleteById(Long id) {
        boolean exist = repository.existsById(id);
        if (!exist) return false;
        try{
            repository.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public MedicinalProperty save(MedicinalProperty propToSave) {
        MedicinalPropertyJPA propToSaveJPA = new MedicinalPropertyJPA(propToSave.getId(), propToSave.getTitle());
        MedicinalPropertyJPA propJPA = repository.save(propToSaveJPA);
        return new MedicinalProperty(propJPA.getId(), propJPA.getTitle());
    }
}
