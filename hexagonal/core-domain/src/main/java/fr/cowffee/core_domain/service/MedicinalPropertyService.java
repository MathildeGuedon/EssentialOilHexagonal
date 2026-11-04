package fr.cowffee.core_domain.service;

import fr.cowffee.core_domain.exception.MedicinalPropertyNotDeletedException;
import fr.cowffee.core_domain.exception.MedicinalPropertyNotFoundException;
import fr.cowffee.core_domain.locale.IMessagePort;
import fr.cowffee.core_domain.model.MedicinalProperty;
import fr.cowffee.core_domain.repository.MedicinalPropertyRepositoryCore;

import java.util.List;

public class MedicinalPropertyService {
    private final MedicinalPropertyRepositoryCore propRepo;
    private final IMessagePort lH;

    public MedicinalPropertyService(MedicinalPropertyRepositoryCore propRepo, IMessagePort lH) {
        this.propRepo = propRepo;
        this.lH = lH;
    }

    public List<MedicinalProperty> getAll(){
        return propRepo.findAll().stream().toList();
    }

    public MedicinalProperty getById(Long id){
        MedicinalProperty prop = propRepo.findById(id);
        if(prop == null){
            throw new MedicinalPropertyNotFoundException(lH.i18n("Msg_MedProp_FindById_NotFound"));
        }
        return prop;
    }

    public void deleteById(Long id){
        boolean success = propRepo.deleteById(id);
        if(!success){
            throw new MedicinalPropertyNotDeletedException(lH.i18n("Msg_MedProp_DeleteById_Error"));
        }
    }

    public MedicinalProperty save(MedicinalProperty propToSave){
        MedicinalProperty foundProp = propRepo.findById(propToSave.getId());
        if(foundProp != null){
            foundProp.setTitle(propToSave.getTitle());
            MedicinalProperty propSaved = propRepo.save(foundProp);
            return propSaved;
        }
        propToSave.setId(null);
        MedicinalProperty propSaved = propRepo.save(propToSave);
        return propSaved;
    }
}
