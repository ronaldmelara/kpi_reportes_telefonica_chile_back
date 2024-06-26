package tech.telefonicachile.kpibackendapi.repository;

import tech.telefonicachile.kpibackendapi.dtos.internals.ObjectValueDto;
import tech.telefonicachile.kpibackendapi.dtos.response.DatasourcesResponse;
import tech.telefonicachile.kpibackendapi.dtos.response.PeriodosReporteResponse;

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
    void createCliente(String value);
    void createGrupoAsignacion(String value);
    ObjectValueDto getGrupoPropietario(String value);
    void createCatpresol1(String value);
    void createCatpresol2(String value);
    void createGrupoPropietario(String value);
    List<ObjectValueDto> getTipoReporte();
    List<DatasourcesResponse> getDatasource();
    List<PeriodosReporteResponse> getPeriodosPorReporte(String value);
}
