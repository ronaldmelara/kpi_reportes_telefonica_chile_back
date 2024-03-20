package tech.telefonicachile.kpibackendapi.repository;


import jakarta.persistence.*;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import tech.telefonicachile.kpibackendapi.dtos.internals.ObjectValueDto;


import java.util.List;

@Repository
public class CatalogosRepositoryImp implements ICatalogosRepository{
    @PersistenceContext
    private EntityManager entityManager;


    private ObjectValueDto executeQuery(String qry, String prm){
        try{
            return (ObjectValueDto) entityManager.createNativeQuery(qry)
                    .setParameter("value", prm)
                    .unwrap(org.hibernate.query.Query.class)
                    .setResultTransformer(Transformers.aliasToBean(ObjectValueDto.class))
                    .getSingleResult();
        }catch (NoResultException | NonUniqueResultException e){
            return null;
        }
    }
    @Override
    public ObjectValueDto getCatpresol1(String value) {
        String qry = "SELECT id_catpresol1 AS id, catpresol1 AS value FROM kpi_tech.catalogo_catpresol1 WHERE catpresol1=:value";
        return executeQuery(qry, value);

    }

    @Override
    public ObjectValueDto getCatpresol2(String value) {
        String qry = "SELECT id_catpresol2 AS id, catpresol2 AS value FROM kpi_tech.catalogo_catpresol2 WHERE catpresol2=:value";
        return executeQuery(qry, value);
    }

    @Override
    public ObjectValueDto getCliente(String value) {
        String qry = "SELECT id_cliente AS id, cliente AS value FROM kpi_tech.catalogo_clientes WHERE cliente=:value";
        return executeQuery(qry, value);
    }
    @Override
    public ObjectValueDto getServicioIso(String value) {
        String qry = "SELECT id_servicio_iso AS id, servicio_iso AS value FROM kpi_tech.catalogo_servicios_iso WHERE servicio_iso=:value";
        return executeQuery(qry, value);
    }

    @Override
    public ObjectValueDto getImpacto(String value) {
        String qry = "SELECT id_impacto AS id, impacto AS value FROM kpi_tech.catalogo_impactos WHERE impacto=:value";
        return executeQuery(qry, value);
    }

    @Override
    public ObjectValueDto getUrgencia(String value) {
        String qry = "SELECT id_urgencia AS id, urgencia AS value FROM kpi_tech.catalogo_urgencias_tech WHERE urgencia=:value";
        return executeQuery(qry, value);
    }

    @Override
    public ObjectValueDto getPrioridad(String value) {
        String qry = "SELECT id_prioridad AS id, prioridad AS value FROM kpi_tech.catalogo_prioridad WHERE prioridad=:value";
        return executeQuery(qry, value);
    }

    @Override
    public ObjectValueDto getEstadoTicket(String value) {
        String qry = "SELECT id_estado_ticket AS id, estado AS value FROM kpi_tech.catalogo_estado_ticket WHERE estado=:value";
        return executeQuery(qry, value);
    }

    @Override
    public ObjectValueDto getGrupoAsignado(String value) {
        String qry = "SELECT id_grupo_asignacion AS id, grupo_asignacion AS value FROM kpi_tech.catalogo_grupo_asignado WHERE grupo_asignacion=:value";
        return executeQuery(qry, value);
    }

    @Override
    public ObjectValueDto getTipoIncidencia(String value) {
        String qry = "SELECT id_tipo_incidencia AS id, tipo AS value FROM kpi_tech.catalogo_tipo_incidencia WHERE tipo=:value";
        return executeQuery(qry, value);
    }
}
