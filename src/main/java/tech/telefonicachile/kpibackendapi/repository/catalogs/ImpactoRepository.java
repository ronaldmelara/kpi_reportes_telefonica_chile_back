package tech.telefonicachile.kpibackendapi.repository.catalogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.telefonicachile.kpibackendapi.entities.catalogs.Impactos;

@Repository
public interface ImpactoRepository extends JpaRepository<Impactos, Long> {
    Impactos findByImpacto(String param);
}
