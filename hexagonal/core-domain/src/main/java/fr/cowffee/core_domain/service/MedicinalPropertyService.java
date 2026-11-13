package fr.cowffee.core_domain.service;

import fr.cowffee.core_domain.exception.MedicinalPropertyInUseException;
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
        if(propRepo.isUsedByEssentialOil(id)){
            throw new MedicinalPropertyInUseException(lH.i18n("Msg_MedProp_DeleteById_Error_InUse"));
        }
        boolean success = propRepo.deleteById(id);
        if(!success){
            throw new MedicinalPropertyNotDeletedException(lH.i18n("Msg_MedProp_DeleteById_Error"));
        }
    }

    public MedicinalProperty save(MedicinalProperty propToSave){
        if(propToSave.getId() == null){
            MedicinalProperty propertyToCreate = new MedicinalProperty(
                    null,
                    propToSave.getTitle()
            );
            return propRepo.save(propertyToCreate);        }
        MedicinalProperty foundProp = propRepo.findById(propToSave.getId());
        if(foundProp != null){
            foundProp.setTitle(propToSave.getTitle());
            return propRepo.save(foundProp);
        }
        propToSave.setId(null);
        return propRepo.save(propToSave);
    }
}
