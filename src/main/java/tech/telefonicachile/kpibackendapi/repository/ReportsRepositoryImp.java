package tech.telefonicachile.kpibackendapi.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import tech.telefonicachile.kpibackendapi.dtos.response.RptTotalesIncReqResponse;

import java.util.List;

@Repository
public class ReportsRepositoryImp implements IReportsRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<RptTotalesIncReqResponse> getTotalesIncidentesRequerimientosPorGrupo(int mes, int anio){
        String qry = "SELECT " +
                "DISTINCT B.id_grupo_asignacion, B.grupo_asignacion, " +
                "(SELECT COUNT(1) FROM kpi_tech.incidencias_requerimientos A1 INNER JOIN kpi_tech.catalogo_grupo_asignado B1 ON A1.id_grupo_asignacion = B1.id_grupo_asignacion " +
                "INNER JOIN kpi_tech.catalogo_tipo_incidencia C1 ON A1.id_tipo_incidencia = C1.id_tipo_incidencia WHERE C1.id_tipo_incidencia = 2 and A1.id_grupo_asignacion  = B.id_grupo_asignacion) TotalIncidentes," +
                "(SELECT COUNT(1) FROM kpi_tech.incidencias_requerimientos A1 INNER JOIN kpi_tech.catalogo_grupo_asignado B1 ON A1.id_grupo_asignacion = B1.id_grupo_asignacion " +
                "INNER JOIN kpi_tech.catalogo_tipo_incidencia C1 ON A1.id_tipo_incidencia = C1.id_tipo_incidencia WHERE C1.id_tipo_incidencia = 1 and A1.id_grupo_asignacion  = B.id_grupo_asignacion) TotalRequerimientos," +
                "(SELECT COUNT(1) FROM kpi_tech.incidencias_requerimientos A1 INNER JOIN kpi_tech.catalogo_grupo_asignado B1 ON A1.id_grupo_asignacion = B1.id_grupo_asignacion " +
                "WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia IN (1,2)) TotalGeneral" +
                "FROM kpi_tech.incidencias_requerimientos A" +
                "INNER JOIN kpi_tech.catalogo_grupo_asignado B ON A.id_grupo_asignacion = B.id_grupo_asignacion" +
                "INNER JOIN kpi_tech.catalogo_tipo_incidencia C ON A.id_tipo_incidencia = C.id_tipo_incidencia" +
                "INNER JOIN kpi_tech.reportes D ON A.id_reporte = D.id_reporte" +
                "WHERE C.id_tipo_incidencia IN (1,2) AND D.id_tipo_reporte = 1 AND D.Mes = :mes AND D.anio = :anio" +
                "group by B.id_grupo_asignacion, B.grupo_asignacion" +
                "ORDER BY B.grupo_asignacion ASC";
        Query query = entityManager.createQuery(qry, RptTotalesIncReqResponse.class);
        query.setParameter("anio", anio);
        query.setParameter("mes", mes);
        return query.getResultList();
    }

}
