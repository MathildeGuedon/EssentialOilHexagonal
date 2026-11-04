package fr.cowffee.core_domain.model;

import java.util.Objects;
import java.util.Set;

public class EssentialOil {
    private Long id;
    private String name;
    private Set<MedicinalProperty> propertySet;

    public EssentialOil() {
    }

    public EssentialOil(String name, Set<MedicinalProperty> propertySet) {
        this.name = name;
        this.propertySet = propertySet;
    }

    public EssentialOil(Long id, String name, Set<MedicinalProperty> propertySet) {
        this.id = id;
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

    public Set<MedicinalProperty> getPropertySet() {
        return propertySet;
    }

    public void setPropertySet(Set<MedicinalProperty> propertySet) {
        this.propertySet = propertySet;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EssentialOil{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", propertySet=").append(propertySet);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EssentialOil that = (EssentialOil) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(propertySet, that.propertySet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, propertySet);
    }
}
