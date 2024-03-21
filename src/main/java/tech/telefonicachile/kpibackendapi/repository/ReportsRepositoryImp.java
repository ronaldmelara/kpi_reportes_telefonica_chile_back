package tech.telefonicachile.kpibackendapi.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import tech.telefonicachile.kpibackendapi.dtos.response.PrtTotalReqCumplidosResponse;
import tech.telefonicachile.kpibackendapi.dtos.response.RptTotalesIncReqResponse;

import java.util.List;

@Repository
public class ReportsRepositoryImp implements IReportsRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<RptTotalesIncReqResponse> getTotalesIncidentesRequerimientosPorGrupo(int mes, int anio){


        String qry = "SELECT " +
                "DISTINCT B.id_grupo_asignacion AS idGrupoAsignacion, B.grupo_asignacion AS grupoAsignacion, " +
                "(SELECT COUNT(1) FROM kpi_tech.incidencias_requerimientos A1 INNER JOIN kpi_tech.catalogo_grupo_asignado B1 ON A1.id_grupo_asignacion = B1.id_grupo_asignacion " +
                "INNER JOIN kpi_tech.catalogo_tipo_incidencia C1 ON A1.id_tipo_incidencia = C1.id_tipo_incidencia WHERE C1.id_tipo_incidencia = 2 and A1.id_grupo_asignacion  = B.id_grupo_asignacion) AS totalIncidentes, " +
                "(SELECT COUNT(1) FROM kpi_tech.incidencias_requerimientos A1 INNER JOIN kpi_tech.catalogo_grupo_asignado B1 ON A1.id_grupo_asignacion = B1.id_grupo_asignacion " +
                "INNER JOIN kpi_tech.catalogo_tipo_incidencia C1 ON A1.id_tipo_incidencia = C1.id_tipo_incidencia WHERE C1.id_tipo_incidencia = 1 and A1.id_grupo_asignacion  = B.id_grupo_asignacion) AS totalRequerimientos, " +
                "(SELECT COUNT(1) FROM kpi_tech.incidencias_requerimientos A1 INNER JOIN kpi_tech.catalogo_grupo_asignado B1 ON A1.id_grupo_asignacion = B1.id_grupo_asignacion " +
                "WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia IN (1,2)) AS totalGeneralPorGrupo " +
                "FROM kpi_tech.incidencias_requerimientos A " +
                "INNER JOIN kpi_tech.catalogo_grupo_asignado B ON A.id_grupo_asignacion = B.id_grupo_asignacion " +
                "INNER JOIN kpi_tech.catalogo_tipo_incidencia C ON A.id_tipo_incidencia = C.id_tipo_incidencia " +
                "INNER JOIN kpi_tech.reportes D ON A.id_reporte = D.id_reporte " +
                "WHERE C.id_tipo_incidencia IN (1,2) AND D.id_tipo_reporte = 1 AND D.Mes = :mes AND D.anio = :anio " +
                "GROUP BY B.id_grupo_asignacion, B.grupo_asignacion " +
                "ORDER BY B.grupo_asignacion ASC";

        return (List<RptTotalesIncReqResponse>) entityManager.createNativeQuery(qry)
                .setParameter("anio", anio)
                .setParameter("mes", mes)
                .unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(Transformers.aliasToBean(RptTotalesIncReqResponse.class))
                .getResultList();
    }

    @Override
    public List<PrtTotalReqCumplidosResponse> getTotalesRequerimientosCumplidosPorGrupo(int mes, int anio){


        String qry = "SELECT \n" +
                "DISTINCT B.id_grupo_asignacion AS idGrupoAsignacion, B.grupo_asignacion AS grupoAsignacion, " +
                "(SELECT COUNT(1) FROM kpi_tech.incidencias_requerimientos A1 INNER JOIN kpi_tech.catalogo_grupo_asignado B1 ON A1.id_grupo_asignacion = B1.id_grupo_asignacion " +
                "INNER JOIN kpi_tech.catalogo_estado_ticket C1 ON A1.id_estado_ticket = C1.id_estado_ticket " +
                "INNER JOIN kpi_tech.catalogo_tipo_incidencia D ON A1.id_tipo_incidencia = D.id_tipo_incidencia WHERE C1.id_estado_ticket = 1 and A1.id_grupo_asignacion  = B.id_grupo_asignacion AND D.id_tipo_incidencia IN (1,2) AND A1.req_cumple_kpi = 1) AS totalCumplidos " +
                "FROM kpi_tech.incidencias_requerimientos A\n" +
                "INNER JOIN kpi_tech.catalogo_grupo_asignado B ON A.id_grupo_asignacion = B.id_grupo_asignacion " +
                "INNER JOIN kpi_tech.catalogo_tipo_incidencia C ON A.id_tipo_incidencia = C.id_tipo_incidencia " +
                "INNER JOIN kpi_tech.reportes D ON A.id_reporte = D.id_reporte " +
                "WHERE C.id_tipo_incidencia IN (1,2) AND A.req_cumple_kpi = 1  AND D.id_tipo_reporte = 1 AND D.Mes = :mes AND D.anio = :anio " +
                "group by B.id_grupo_asignacion, B.grupo_asignacion " +
                "ORDER BY B.grupo_asignacion ASC";

        return (List<PrtTotalReqCumplidosResponse>) entityManager.createNativeQuery(qry)
                .setParameter("anio", anio)
                .setParameter("mes", mes)
                .unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(Transformers.aliasToBean(PrtTotalReqCumplidosResponse.class))
                .getResultList();
    }


}
