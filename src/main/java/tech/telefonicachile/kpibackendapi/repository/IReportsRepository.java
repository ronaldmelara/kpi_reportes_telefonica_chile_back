package tech.telefonicachile.kpibackendapi.repository;

import tech.telefonicachile.kpibackendapi.dtos.response.PrtTotalReqCumplidosResponse;
import tech.telefonicachile.kpibackendapi.dtos.response.RptTotalesIncReqResponse;

import java.util.List;

public interface IReportsRepository {
    List<RptTotalesIncReqResponse> getTotalesIncidentesRequerimientosPorGrupo(int mes, int anio);
    List<PrtTotalReqCumplidosResponse> getTotalesRequerimientosCumplidosPorGrupo(int mes, int anio);
}
