package fr.cowffee.core_domain.service;

import fr.cowffee.core_domain.exception.EssentialOilNotDeletedException;
import fr.cowffee.core_domain.exception.EssentialOilNotFoundException;
import fr.cowffee.core_domain.locale.IMessagePort;
import fr.cowffee.core_domain.model.EssentialOil;
import fr.cowffee.core_domain.model.MedicinalProperty;
import fr.cowffee.core_domain.repository.EssentialOilRepositoryCore;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EssentialOilService {
    private final EssentialOilRepositoryCore oilRepo;
    private final MedicinalPropertyService propService;
    private final IMessagePort lH;

    public EssentialOilService(EssentialOilRepositoryCore oilRepo, MedicinalPropertyService propService, IMessagePort lH) {
        this.oilRepo = oilRepo;
        this.propService = propService;
        this.lH = lH;
    }

    public List<EssentialOil> getAll(){
        return oilRepo.findAll().stream().toList();
    }

    public EssentialOil getById(Long id){
        EssentialOil oil = oilRepo.findById(id);
        if(oil == null){
            throw new EssentialOilNotFoundException(lH.i18n("Msg_Oil_FindById_NotFound"));
        }
        return oil;
    }

    public void deleteById(Long id){
        boolean success = oilRepo.deleteById(id);
        if(!success){
            throw new EssentialOilNotDeletedException(lH.i18n("Msg_Oil_DeleteById_Error"));
        }
    }

    public EssentialOil save(EssentialOil oilToSave){
        if(oilToSave.getId() == null){
            EssentialOil oilToCreate = new EssentialOil(null, oilToSave.getName(), oilToSave.getPropertySet());
            return oilRepo.save(oilToCreate);
        }
        EssentialOil foundOil = oilRepo.findById(oilToSave.getId());
        if(foundOil != null){
            foundOil.setName(oilToSave.getName());
            foundOil.setPropertySet(oilToSave.getPropertySet());
            EssentialOil oilSaved = oilRepo.save(foundOil);
            return oilSaved;
        }
        oilToSave.setId(null);
        EssentialOil oilSaved = oilRepo.save(oilToSave);
        return oilSaved;
    }

    public EssentialOil addAMedicinalProperty(Long idOil, Long idProp){
        EssentialOil foundOil = getById(idOil);
        MedicinalProperty foundProp = propService.getById(idProp);
        Set<MedicinalProperty> propertySet = new HashSet<>();
        if(foundOil.getPropertySet() != null){
            propertySet.addAll(foundOil.getPropertySet());
        }
        propertySet.add(foundProp);
        EssentialOil oilToUpdate = new EssentialOil(
                foundOil.getId(),
                foundOil.getName(),
                propertySet
        );
        return oilRepo.save(oilToUpdate);
    }
}
