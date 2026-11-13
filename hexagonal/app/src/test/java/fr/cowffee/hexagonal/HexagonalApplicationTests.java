package fr.cowffee.hexagonal;

import fr.cowffee.core_domain.exception.*;
import fr.cowffee.core_domain.model.EssentialOil;
import fr.cowffee.core_domain.model.MedicinalProperty;
import fr.cowffee.core_domain.service.EssentialOilService;
import fr.cowffee.core_domain.service.MedicinalPropertyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class HexagonalApplicationTests {

	@Autowired
	private MedicinalPropertyService propService;
	@Autowired
	private EssentialOilService oilService;

	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("doit retourner la liste des propriétés médicinales")
	public void test_getAllMedProp(){
		assertThat(propService.getAll().size()).isEqualTo(8);
	}

	@Test
	@DisplayName("Doit retourner une propriété médicinale si l'id existe")
	public void test_getIdProp(){
		assertThat(propService.getById(7L).getTitle()).isEqualTo("Cicatrisant");
	}

	@Test
	@DisplayName("doit retourner une erreur si l'id n'existe pas")
	public void test_getIdPropError(){
		assertThrows(MedicinalPropertyNotFoundException.class, () -> propService.getById(999L));
	}

	@Test
	@DisplayName("doit supprimer une propriété médicinale si l'id existe et qu'aucune huile n'utilise cette propriété")
	public void test_deleteMedById(){
		int size = propService.getAll().size();
		propService.deleteById(2L);
		int newSize = propService.getAll().size();
		assertThat(size).isEqualTo(newSize + 1);
	}

	@Test
	@DisplayName("ne doit pas supprimer une propriété médicinale si l'id existe et qu'au moins une huile utilise cette propriété")
	public void test_deleteMedInUseByIdError(){
		assertThrows(MedicinalPropertyInUseException.class, () -> propService.deleteById(3L));
	}

	@Test
	@DisplayName("doit retourner une erreur si l'id n'existe pas")
	public void test_deleteMedByIdError(){
		assertThrows(MedicinalPropertyNotDeletedException.class, () -> propService.deleteById(888L));
	}

	@Test
	@DisplayName("doit créer une nouvelle propriété médicinale")
	public void test_saveMed(){
		MedicinalProperty newProp = propService.save(new MedicinalProperty(null, "Vasodilatateur"));
		assertThat(propService.getById(newProp.getId()).getTitle()).isEqualTo("Vasodilatateur");
	}

	@Test
	@DisplayName("doit modifier une propriété médicinale")
	public void test_updateMed(){
		MedicinalProperty newProp = propService.save(new MedicinalProperty(4L, "Hémostatique"));
		assertThat(propService.getById(4L).getTitle()).isEqualTo("Hémostatique");
	}

	@Test
	@DisplayName("doit retourner la liste des huiles essentielles")
	public void test_getAllOils(){
		assertThat(oilService.getAll().size()).isEqualTo(8);
	}

	@Test
	@DisplayName("Doit retourner une huile essentielle si l'id existe")
	public void test_getIdOil(){
		assertThat(oilService.getById(2L).getName()).isEqualTo("Cannelle");
	}

	@Test
	@DisplayName("doit retourner une erreur si l'id n'existe pas")
	public void test_getIdOilError(){
		assertThrows(EssentialOilNotFoundException.class, () -> oilService.getById(999L));
	}

	@Test
	@DisplayName("doit supprimer une huile essentielle si l'id existe")
	public void test_deleteOilById(){
		int size = oilService.getAll().size();
		oilService.deleteById(3L);
		int newSize = oilService.getAll().size();
		assertThat(size).isEqualTo(newSize + 1);
	}

	@Test
	@DisplayName("doit retourner une erreur si l'id n'existe pas")
	public void test_deleteOilByIdError(){
		assertThrows(EssentialOilNotDeletedException.class, () -> oilService.deleteById(888L));
	}

	@Test
	@DisplayName("doit créer une nouvelle huile essentielle")
	public void test_saveOil(){
		EssentialOil newOil = oilService.save(new EssentialOil(null, "Hélichryse", new HashSet<>()));
		assertThat(oilService.getById(newOil.getId()).getName()).isEqualTo("Hélichryse");
		assertThat(oilService.getById(newOil.getId()).getPropertySet()).isEmpty();
	}

	@Test
	@DisplayName("doit modifier une huile essentielle")
	public void test_updateOil(){
		EssentialOil existingOil = oilService.getById(4L);
		Set<MedicinalProperty> medSet = new HashSet<>();
		medSet.add(propService.getById(1L));
		EssentialOil newOil = oilService.save(new EssentialOil(existingOil.getId(), "Eucalyptus", medSet));
		EssentialOil foundOil = oilService.getById(4L);
		assertThat(foundOil.getName()).isEqualTo("Eucalyptus");
		assertThat(foundOil.getPropertySet()).isEqualTo(medSet);
	}

	@Test
	@DisplayName("doit ajouter une propriété médicinale à une huile essentielle")
	public void test_addMedPropToOil(){
		EssentialOil oilToUpdate = oilService.getById(6L);
		int size = oilToUpdate.getPropertySet().size();
		oilToUpdate = oilService.addAMedicinalProperty(oilToUpdate.getId(), 6L);
		EssentialOil updatedOil = oilService.getById(6L);
		assertThat(oilToUpdate.getPropertySet()).hasSize(size + 1);
		assertThat(updatedOil.getPropertySet()).extracting(MedicinalProperty::getId).contains(3L);

	}
}
