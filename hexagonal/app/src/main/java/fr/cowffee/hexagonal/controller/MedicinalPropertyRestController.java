package fr.cowffee.hexagonal.controller;

import fr.cowffee.core_domain.model.MedicinalProperty;
import fr.cowffee.core_domain.service.MedicinalPropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(
        name = "Propriétés médicinales",
        description = "Gestion des propriétés médicinales associables aux huiles essentielles"
)
@RestController
@RequestMapping("/api/med-prop")
public class MedicinalPropertyRestController {
    private final MedicinalPropertyService propService;

    public MedicinalPropertyRestController(MedicinalPropertyService propService) {
        this.propService = propService;
    }

    @Operation(
            summary = "Lister toutes les propriétés médicinales",
            description = "Retourne la liste complète des propriétés médicinales disponibles."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Liste des propriétés récupérée avec succès"
            )
    })
    @GetMapping
    public List<MedicinalProperty> getAll(){
        return propService.getAll();
    }

    @Operation(
            summary = "Consulter une propriété médicinale",
            description = "Retourne le détail d’une propriété médicinale à partir de son identifiant."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Propriété trouvée"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Propriété introuvable"
            )
    })
    @GetMapping("/{id}")
    public MedicinalProperty getById(
            @Parameter(
                    description = "Identifiant de la propriété médicinale",
                    example = "1"
            )
            @PathVariable Long id){
        return propService.getById(id);
    }

    @Operation(
            summary = "Supprimer une propriété médicinale",
            description = "Supprime une propriété médicinale uniquement si elle n’est associée à aucune huile essentielle."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Propriété supprimée avec succès"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Propriété introuvable"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Propriété utilisée par au moins une huile essentielle"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(
                    description = "Identifiant de la propriété médicinale",
                    example = "3"
            )
            @PathVariable Long id){
        propService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Créer ou mettre à jour une propriété médicinale",
            description = "Crée une propriété si un identifiant non présent dans la base est fourni, ou met à jour la propriété existante si un identifiant est fourni."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Propriété créée ou mise à jour avec succès"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Données invalides"
            )
    })
    @PostMapping
    public ResponseEntity<MedicinalProperty> save(@RequestBody MedicinalProperty medicinalProperty){
        MedicinalProperty savedProp = propService.save(medicinalProperty);
        URI location = URI.create("/api/med-prop/" + savedProp.getId());
        return ResponseEntity.created(location).body(savedProp);
    }
}
