package tech.telefonicachile.kpibackendapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.telefonicachile.kpibackendapi.entities.TemporalIncidente;

import java.util.Optional;

@Repository
public interface TmpIncidentesRepository extends JpaRepository<TemporalIncidente, Long> {

    Optional<TemporalIncidente> findByTicket(String ticket);
}
