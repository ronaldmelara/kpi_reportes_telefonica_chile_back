package tech.telefonicachile.kpibackendapi.repository;

import tech.telefonicachile.kpibackendapi.dtos.internals.ObjectValueDto;

import java.util.List;

public interface ICatalogosRepository {
    ObjectValueDto getCatpresol1(String value);
    ObjectValueDto getCatpresol2(String value);
    ObjectValueDto getCliente(String value);
    ObjectValueDto getServicioIso(String value);
    ObjectValueDto getImpacto(String value);
    ObjectValueDto getUrgencia(String value);
    ObjectValueDto getPrioridad(String value);
    ObjectValueDto getEstadoTicket(String value);
    ObjectValueDto getGrupoAsignado(String value);
    ObjectValueDto getTipoIncidencia(String value);
}
