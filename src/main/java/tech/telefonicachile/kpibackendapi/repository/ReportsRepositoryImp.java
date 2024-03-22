package tech.telefonicachile.kpibackendapi.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import tech.telefonicachile.kpibackendapi.dtos.response.PrtTotalIncReqCumplidosResponse;
import tech.telefonicachile.kpibackendapi.dtos.response.RptTiemposIncReqResponse;
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
                "(SELECT COUNT(1) FROM kpi_tech.incidencias_requerimientos A1 " +
                "WHERE A1.id_tipo_incidencia = 2 and A1.id_grupo_asignacion  = B.id_grupo_asignacion AND " +
                "A1.id_reporte = A.id_reporte) AS totalIncidentes, " +
                "(SELECT COUNT(1) FROM kpi_tech.incidencias_requerimientos A1 " +
                "WHERE A1.id_tipo_incidencia = 1 and A1.id_grupo_asignacion  = B.id_grupo_asignacion AND " +
                "A1.id_reporte = A.id_reporte) AS totalRequerimientos, " +
                "(SELECT COUNT(1) FROM kpi_tech.incidencias_requerimientos A1 " +
                "WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia IN (1,2) AND " +
                "A1.id_reporte = A.id_reporte) AS totalGeneralPorGrupo " +
                "FROM kpi_tech.incidencias_requerimientos A " +
                "INNER JOIN kpi_tech.catalogo_grupo_asignado B ON A.id_grupo_asignacion = B.id_grupo_asignacion " +
                "INNER JOIN kpi_tech.catalogo_tipo_incidencia C ON A.id_tipo_incidencia = C.id_tipo_incidencia " +
                "INNER JOIN kpi_tech.reportes D ON A.id_reporte = D.id_reporte " +
                "WHERE C.id_tipo_incidencia IN (1,2) AND D.id_tipo_reporte = 1 AND D.Mes = :mes AND D.anio = :anio " +
                "AND B.id_grupo_asignacion NOT IN(15,2,1,17,21,16,20,9,7) " +
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
    public List<PrtTotalIncReqCumplidosResponse> getTotalesRequerimientosCumplidosPorGrupo(int mes, int anio){


        String qry = "SELECT " +
                "DISTINCT B.id_grupo_asignacion AS idGrupoAsignacion, B.grupo_asignacion AS grupoAsignacion, " +
                "SUM(1) AS totalCumplidos " +
                "FROM kpi_tech.incidencias_requerimientos A " +
                "INNER JOIN kpi_tech.catalogo_grupo_asignado B ON A.id_grupo_asignacion = B.id_grupo_asignacion " +
                "INNER JOIN kpi_tech.catalogo_tipo_incidencia C ON A.id_tipo_incidencia = C.id_tipo_incidencia " +
                "INNER JOIN kpi_tech.reportes D ON A.id_reporte = D.id_reporte " +
                "WHERE A.id_estado_ticket = 1 AND A.req_cumple_kpi = 1  AND D.id_tipo_reporte = 1 AND D.Mes = :mes AND D.anio = :anio " +
                "AND B.id_grupo_asignacion NOT IN(15,2,1,17,21,16,20,9,7) " +
                "group by B.id_grupo_asignacion, B.grupo_asignacion " +
                "ORDER BY B.grupo_asignacion ASC";

        return (List<PrtTotalIncReqCumplidosResponse>) entityManager.createNativeQuery(qry)
                .setParameter("anio", anio)
                .setParameter("mes", mes)
                .unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(Transformers.aliasToBean(PrtTotalIncReqCumplidosResponse.class))
                .getResultList();
    }

    @Override
    public List<PrtTotalIncReqCumplidosResponse> getTotalesIncidentesCumplidosPorGrupo(int mes, int anio){


        String qry = "SELECT " +
                "DISTINCT B.id_grupo_asignacion AS idGrupoAsignacion, B.grupo_asignacion AS grupoAsignacion, " +
                "SUM(1) AS totalCumplidos " +
                "FROM kpi_tech.incidencias_requerimientos A " +
                "INNER JOIN kpi_tech.catalogo_grupo_asignado B ON A.id_grupo_asignacion = B.id_grupo_asignacion " +
                "INNER JOIN kpi_tech.catalogo_tipo_incidencia C ON A.id_tipo_incidencia = C.id_tipo_incidencia " +
                "INNER JOIN kpi_tech.reportes D ON A.id_reporte = D.id_reporte " +
                "WHERE A.id_estado_ticket = 1 AND C.id_tipo_incidencia = 2 AND A.inc_cumple_kpi = 1   AND D.id_tipo_reporte = 1 AND D.Mes = :mes AND D.anio = :anio " +
                "AND B.id_grupo_asignacion NOT IN(15,2,1,17,21,16,20,9,7) " +
                "group by B.id_grupo_asignacion, B.grupo_asignacion " +
                "ORDER BY B.grupo_asignacion ASC";

        return (List<PrtTotalIncReqCumplidosResponse>) entityManager.createNativeQuery(qry)
                .setParameter("anio", anio)
                .setParameter("mes", mes)
                .unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(Transformers.aliasToBean(PrtTotalIncReqCumplidosResponse.class))
                .getResultList();
    }

    @Override
    public List<RptTiemposIncReqResponse> getTiempoRestauracionIncReq(int mes, int anio){


        String qry = "SELECT " +
                "DISTINCT B.id_grupo_asignacion AS idGrupoAsignacion, B.grupo_asignacion AS grupoAsignacion, " +
                "(SELECT " +
                "sec_to_time(AVG(TIME_TO_SEC(A1.sla))) " +
                "FROM kpi_tech.incidencias_requerimientos A1  " +
                "WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia = 2 AND A1.id_reporte = A.id_reporte " +
                "group by A1.id_grupo_asignacion) AS promedioIncidentes, " +
                "(SELECT " +
                "sec_to_time(AVG(TIME_TO_SEC(A1.sla))) " +
                "FROM kpi_tech.incidencias_requerimientos A1  " +
                "WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia = 1 AND A1.id_reporte = A.id_reporte " +
                "group by A1.id_grupo_asignacion) AS promedioRequerimientos, " +
                "(SELECT  " +
                "sec_to_time(AVG(TIME_TO_SEC(A1.sla)))  " +
                "FROM kpi_tech.incidencias_requerimientos A1  " +
                "WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia IN (1,2) AND A1.id_reporte = A.id_reporte " +
                "group by A1.id_grupo_asignacion) AS promedioTotal, " +
                "(SELECT  " +
                " (AVG(TIME_TO_SEC(A1.sla)) / 86400) * 1440 " +
                "FROM kpi_tech.incidencias_requerimientos A1  " +
                "WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia = 2 AND A1.id_reporte = A.id_reporte " +
                "group by A1.id_grupo_asignacion) AS minInc, " +
                "(SELECT " +
                " (AVG(TIME_TO_SEC(A1.sla)) / 86400) * 1440 " +
                "FROM kpi_tech.incidencias_requerimientos A1  " +
                "WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia = 1 AND A1.id_reporte = A.id_reporte " +
                "group by A1.id_grupo_asignacion) AS minReq " +
                "FROM kpi_tech.incidencias_requerimientos A " +
                "INNER JOIN kpi_tech.catalogo_grupo_asignado B ON A.id_grupo_asignacion = B.id_grupo_asignacion " +
                "INNER JOIN kpi_tech.catalogo_tipo_incidencia C ON A.id_tipo_incidencia = C.id_tipo_incidencia " +
                "INNER JOIN kpi_tech.reportes D ON A.id_reporte = D.id_reporte " +
                "WHERE C.id_tipo_incidencia IN (1,2) " +
                "AND B.id_grupo_asignacion NOT IN(8,15,2,1,17,21,16,20,9,7, 12, 24, 4,5, 25) AND D.id_tipo_reporte = 1 AND D.Mes = :mes AND D.anio = :anio " +
                "group by B.id_grupo_asignacion, B.grupo_asignacion,C.id_tipo_incidencia, A.id_reporte " +
                "ORDER BY B.id_grupo_asignacion ASC";

        return (List<RptTiemposIncReqResponse>) entityManager.createNativeQuery(qry)
                .setParameter("anio", anio)
                .setParameter("mes", mes)
                .unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(Transformers.aliasToBean(RptTiemposIncReqResponse.class))
                .getResultList();
    }

    @Override
    public List<RptTiemposIncReqResponse> getTiempoRestauracionIncReqManaged(int mes, int anio){


        String qry = "SELECT " +
                "DISTINCT B.id_grupo_asignacion AS idGrupoAsignacion, B.grupo_asignacion AS grupoAsignacion, " +
                "(SELECT  " +
                "CAST(sec_to_time(AVG(TIME_TO_SEC(A1.sla))) AS char) " +
                "FROM kpi_tech.incidencias_requerimientos A1  " +
                "WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia = 2 AND A1.id_reporte = A.id_reporte " +
                "group by A1.id_grupo_asignacion) AS promedioIncidentes, " +
                "(SELECT " +
                "CAST(sec_to_time(AVG(TIME_TO_SEC(A1.sla))) AS char) " +
                "FROM kpi_tech.incidencias_requerimientos A1  " +
                "WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia = 1 AND A1.id_reporte = A.id_reporte " +
                "group by A1.id_grupo_asignacion) AS promedioRequerimientos, " +
                "(SELECT " +
                "CAST(sec_to_time(AVG(TIME_TO_SEC(A1.sla))) as char) " +
                "FROM kpi_tech.incidencias_requerimientos A1  " +
                "WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia IN (1,2) AND A1.id_reporte = A.id_reporte " +
                "group by A1.id_grupo_asignacion) AS promedioTotal, " +
                "(SELECT  " +
                " CAST((AVG(TIME_TO_SEC(A1.sla)) / 86400) * 1440 as float) " +
                "FROM kpi_tech.incidencias_requerimientos A1  " +
                "WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia = 2 AND A1.id_reporte = A.id_reporte " +
                "group by A1.id_grupo_asignacion) AS minInc, " +
                "(SELECT  " +
                " cast((AVG(TIME_TO_SEC(A1.sla)) / 86400) * 1440 as float) " +
                "FROM kpi_tech.incidencias_requerimientos A1  " +
                "WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia = 1 AND A1.id_reporte = A.id_reporte " +
                "group by A1.id_grupo_asignacion) as minReq " +
                "FROM kpi_tech.incidencias_requerimientos A " +
                "INNER JOIN kpi_tech.catalogo_grupo_asignado B ON A.id_grupo_asignacion = B.id_grupo_asignacion " +
                "INNER JOIN kpi_tech.catalogo_tipo_incidencia C ON A.id_tipo_incidencia = C.id_tipo_incidencia " +
                "INNER JOIN kpi_tech.reportes D ON A.id_reporte = D.id_reporte " +
                "WHERE C.id_tipo_incidencia IN (1,2) " +
                "AND B.id_grupo_asignacion IN(1,2, 15,16,17,21,7,9,20) AND D.id_tipo_reporte = 1 AND D.Mes = :mes AND D.anio = :anio " +
                "group by B.id_grupo_asignacion, B.grupo_asignacion,C.id_tipo_incidencia, A.id_reporte " +
                "ORDER BY B.id_grupo_asignacion ASC";

        return (List<RptTiemposIncReqResponse>) entityManager.createNativeQuery(qry)
                .setParameter("anio", anio)
                .setParameter("mes", mes)
                .unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(Transformers.aliasToBean(RptTiemposIncReqResponse.class))
                .getResultList();
    }

}
