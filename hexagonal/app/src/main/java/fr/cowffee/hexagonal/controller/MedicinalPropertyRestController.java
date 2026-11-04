package fr.cowffee.hexagonal.controller;

import fr.cowffee.core_domain.model.MedicinalProperty;
import fr.cowffee.core_domain.service.MedicinalPropertyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/med-prop")
public class MedicinalPropertyRestController {
    private final MedicinalPropertyService propService;

    public MedicinalPropertyRestController(MedicinalPropertyService propService) {
        this.propService = propService;
    }

    @GetMapping("/get-all")
    public List<MedicinalProperty> getAll(){
        return propService.getAll();
    }

    @GetMapping("/get-id/{id}")
    public MedicinalProperty getById(@PathVariable Long id){
        return propService.getById(id);
    }
    @DeleteMapping("/delete-id/{id}")
    public void deleteById(@PathVariable Long id){
        propService.deleteById(id);
    }

    @PostMapping("/save")
    public MedicinalProperty save(@RequestBody MedicinalProperty medicinalProperty){
        return propService.save(medicinalProperty);
    }
}
