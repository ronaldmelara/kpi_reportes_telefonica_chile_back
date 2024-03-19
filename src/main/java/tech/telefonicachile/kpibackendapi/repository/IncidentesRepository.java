package tech.telefonicachile.kpibackendapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tech.telefonicachile.kpibackendapi.entities.Incidente;
import tech.telefonicachile.kpibackendapi.services.DataProcessingService;

import java.util.Optional;

@Repository
public interface IncidentesRepository extends JpaRepository<Incidente,Long> {
    Optional<Incidente> findByTicket(String ticket);

    @Transactional
    @Modifying
    @Query(value="UPDATE kpi_tech.incidencias_requerimientos SET id_estado_ticket=:#{#inc.idEstadoTicket}, fecha_ultima_resolucion="
            +":#{#inc.fechaUltimaResolucion}, fecha_cierre=:#{#inc.fechaCierre}, id_grupo_asignacion=:#{#inc.idGrupoAsignado}, "
            +"responsable=:#{#inc.responsable}, id_servicio=:#{#inc.idServicio}, id_tipo_incidencia=:#{#inc.idTipoIncidencia} "
            +"WHERE ticket=:#{#inc.ticket} and id_reporte=:#{#inc.idReporte}", nativeQuery = true)
    int updateTicketFromRemedy(@Param("inc") Incidente inc);

    @Transactional
    @Modifying
    @Query(value="UPDATE kpi_tech.incidencias_requerimientos SET id_estado_ticket=:#{#inc.idEstadoTicket}, fecha_ultima_resolucion="
            +":#{#inc.fechaUltimaResolucion}, fecha_cierre=:#{#inc.fechaCierre}, id_grupo_asignacion=:#{#inc.idGrupoAsignado}, "
            +"responsable=:#{#inc.responsable}, id_servicio=:#{#inc.idServicio}, id_tipo_incidencia=:#{#inc.idTipoIncidencia}, "
            +"id_grupo_propietario=:#{#inc.idGrupoPropietario}, id_catpresol1=:#{#inc.idCatpresol1}, id_catpresol2=:#{#inc.idCatpresol2}, "
            +"notas=:#{#inc.notas}, resolucion=:#{#inc.resolucion}, pendiente=:#{#inc.pendiente}, "
            +"e2e=:#{#inc.e2e}, sla=:#{#inc.sla}, id_urgencia_ob=:#{#inc.idUrgenciaOb}, "
            +"inc_cumple_kpi=:#{#inc.incCumpleKpi}, req_cumple_kpi=:#{#inc.reqCumpleKpi}, cumple_critica=:#{#inc.cumpleCritica}, "
            +"cumple_alta=:#{#inc.cumpleAlta}, cumple_media=:#{#inc.cumpleMedia}, cumple_baja=:#{#inc.cumpleBaja}, "
            +"m1h_sla=:#{#inc.menos1hSla}, m1hm4h_sla=:#{#inc.mas1hMenos4hrSla}, m4hm8h_sla=:#{#inc.mas4hMenos8hSla}, "
            +"m8hm24_sla=:#{#inc.mas8hMenos24hSla}, m24h_sla=:#{#inc.mas24hSla}, m1h_e2e=:#{#inc.menos1he2e}, "
            +"m1hm4h_e2e=:#{#inc.mas1hMenos4hre2e}, m4hm8h_e2e=:#{#inc.mas4hMenos8he2e}, m8hm24h_e2e=:#{#inc.mas8hMenos24he2e}, "
            +"m24h_e2e=:#{#inc.mas24he2e} "
            +"WHERE ticket=:#{#inc.ticket} and id_reporte=:#{#inc.idReporte}", nativeQuery = true)
    int updateTicketFromExternal(@Param("inc") Incidente inc);


}
