package fr.cowffee.adapter_jpa.essential_oil;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EssentialOilRepositoryJPA extends JpaRepository<EssentialOilJPA, Long> {
    @Query("""
            SELECT COUNT(o) > 0
            FROM EssentialOilJPA o
            JOIN o.propertySet p
            WHERE p.id = :propertyId
            """)
    boolean existsByPropertyId(@Param("propertyId") Long propertySetId);
}
