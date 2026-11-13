package fr.cowffee.adapter_jpa.essential_oil;

import fr.cowffee.adapter_jpa.medicinal_property.MedicinalPropertyJPA;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "essential_oil")
public class EssentialOilJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "oil_property",
            joinColumns = @JoinColumn(name="oil_id"),
    inverseJoinColumns = @JoinColumn(name="property_id"))
    private Set<MedicinalPropertyJPA> propertySet = new HashSet<>();

    public EssentialOilJPA() {
    }

    public EssentialOilJPA(Long id, String name, Set<MedicinalPropertyJPA> propertySet) {
        this.id = id;
        this.name = name;
        this.propertySet = propertySet;
    }

    public EssentialOilJPA(String name, Set<MedicinalPropertyJPA> propertySet) {
        this.name = name;
        this.propertySet = propertySet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MedicinalPropertyJPA> getPropertySet() {
        return propertySet;
    }

    public void setPropertySet(Set<MedicinalPropertyJPA> propertySet) {
        this.propertySet = propertySet;
    }
}
