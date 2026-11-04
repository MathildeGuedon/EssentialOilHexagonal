package fr.cowffee.hexagonal;

import fr.cowffee.core_domain.exception.MedicinalPropertyNotDeletedException;
import fr.cowffee.core_domain.exception.MedicinalPropertyNotFoundException;
import fr.cowffee.core_domain.model.MedicinalProperty;
import fr.cowffee.core_domain.service.MedicinalPropertyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class HexagonalApplicationTests {

	@Autowired
	private MedicinalPropertyService propService;

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
		assertThat(propService.getById(2L).getTitle()).isEqualTo("Galactogène");
	}

	@Test
	@DisplayName("doit retourner une erreur si l'id n'existe pas")
	public void test_getIdPropError(){
		assertThrows(MedicinalPropertyNotFoundException.class, () -> propService.getById(999L));
	}

	@Test
	@DisplayName("doit supprimer une propriété médicinale si l'id existe")
	public void test_deleteMedById(){
		int size = propService.getAll().size();
		propService.deleteById(3L);
		int newSize = propService.getAll().size();
		assertThat(size).isEqualTo(newSize + 1);
	}

	@Test
	@DisplayName("doit retourner une erreur si l'id n'existe pas")
	public void test_deleteMedByIdError(){
		assertThrows(MedicinalPropertyNotDeletedException.class, () -> propService.deleteById(888L));
	}

	@Test
	@DisplayName("doit créer une nouvelle propriété médicinale")
	public void test_saveMed(){
		MedicinalProperty newProp = propService.save(new MedicinalProperty(15L, "Vasodilatateur"));
		assertThat(propService.getById(9L).getTitle()).isEqualTo("Vasodilatateur");
	}

	@Test
	@DisplayName("doit modifier une propriété médicinale")
	public void test_updateMed(){
		MedicinalProperty newProp = propService.save(new MedicinalProperty(4L, "Hémostatique"));
		assertThat(propService.getById(4L).getTitle()).isEqualTo("Hémostatique");
	}
}
