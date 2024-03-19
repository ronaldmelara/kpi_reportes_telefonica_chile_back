package tech.telefonicachile.kpibackendapi.repository.catalogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.telefonicachile.kpibackendapi.entities.catalogs.Clientes;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Long> {
    Clientes findByCliente(String param);
}
