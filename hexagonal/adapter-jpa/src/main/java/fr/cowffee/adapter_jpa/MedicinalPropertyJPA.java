package fr.cowffee.adapter_jpa;

import jakarta.persistence.*;

@Entity
@Table(name="medicinal_property")
public class MedicinalPropertyJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    public MedicinalPropertyJPA() {
    }

    public MedicinalPropertyJPA(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
