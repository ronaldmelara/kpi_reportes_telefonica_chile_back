package tech.telefonicachile.kpibackendapi.repository.catalogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.telefonicachile.kpibackendapi.entities.catalogs.EstadoTicket;

@Repository
public interface EstadoTicketRepository extends JpaRepository<EstadoTicket, Long> {
    EstadoTicket findByEstado(String param);
}
