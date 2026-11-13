package fr.cowffee.hexagonal.controller;

import fr.cowffee.core_domain.model.EssentialOil;
import fr.cowffee.core_domain.service.EssentialOilService;
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
        name = "Huiles essentielles",
        description = "Gestion des huiles essentielles et de leurs propriétés médicinales"
)
@RestController
@RequestMapping("/api/oils")
public class EssentialOilRestController {
    private final EssentialOilService oilService;

    public EssentialOilRestController(EssentialOilService oilService) {
        this.oilService = oilService;
    }

    @Operation(
            summary = "Lister toutes les huiles essentielles",
            description = "Retourne la liste complète des huiles essentielles, avec leurs propriétés médicinales associées."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Liste des huiles essentielles récupérée avec succès"
            )
    })
    @GetMapping
    public List<EssentialOil> getAll(){
        return oilService.getAll();
    }

    @Operation(
            summary = "Consulter une huile essentielle",
            description = "Retourne le détail d’une huile essentielle à partir de son identifiant."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Huile essentielle trouvée"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Huile essentielle introuvable"
            )
    })
    @GetMapping("/{id}")
    public EssentialOil getById(
            @Parameter(
                    description = "Identifiant de l’huile essentielle",
                    example = "1"
            )
            @PathVariable Long id){
        return oilService.getById(id);
    }

    @Operation(
            summary = "Supprimer une huile essentielle",
            description = "Supprime l’huile essentielle correspondant à l’identifiant fourni."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Huile essentielle supprimée avec succès"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Huile essentielle introuvable"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(
                    description = "Identifiant de l’huile essentielle",
                    example = "1"
            )
            @PathVariable Long id){
        oilService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Créer ou mettre à jour une huile essentielle",
            description = "Crée une huile essentielle un identifiant inconnu est fourni, ou met à jour l’huile existante si un identifiant est fourni."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Huile essentielle créée ou mise à jour avec succès"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Données invalides"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Propriété médicinale introuvable"
            )
    })
    @PostMapping
    public ResponseEntity<EssentialOil> save(@RequestBody EssentialOil oil){
        EssentialOil savedOil = oilService.save(oil);
        URI location = URI.create("/api/oils/" + savedOil.getId());
        return ResponseEntity.created(location).body(savedOil);
    }

    @Operation(
            summary = "Associer une propriété médicinale à une huile",
            description = "Ajoute une propriété médicinale existante à l’huile essentielle identifiée dans l’URL."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Propriété ajoutée avec succès"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Huile essentielle ou propriété médicinale introuvable"
            )
    })
    @PostMapping("/{id}/properties")
    public ResponseEntity<EssentialOil> addMedPropToOil(
            @Parameter(
                    description = "Identifiant de l’huile essentielle",
                    example = "1"
            )
            @PathVariable Long id,
            @RequestBody AddPropertyRequest request){
        EssentialOil updatedOil = oilService.addAMedicinalProperty(id, request.propertyId);
        return ResponseEntity.ok(updatedOil);
    }

    public record AddPropertyRequest (Long propertyId){}
}
