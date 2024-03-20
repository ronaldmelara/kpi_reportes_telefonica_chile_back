package tech.telefonicachile.kpibackendapi.repository;

import tech.telefonicachile.kpibackendapi.dtos.response.RptTotalesIncReqResponse;

import java.util.List;

public interface IReportsRepository {
    List<RptTotalesIncReqResponse> getTotalesIncidentesRequerimientosPorGrupo(int mes, int anio);
}
