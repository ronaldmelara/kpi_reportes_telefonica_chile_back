package tech.telefonicachile.kpibackendapi.repository;

import tech.telefonicachile.kpibackendapi.dtos.response.PrtTotalIncReqCumplidosResponse;
import tech.telefonicachile.kpibackendapi.dtos.response.RptTiemposIncReqResponse;
import tech.telefonicachile.kpibackendapi.dtos.response.RptTotalesIncReqResponse;

import java.util.List;

public interface IReportsRepository {
    List<RptTotalesIncReqResponse> getTotalesIncidentesRequerimientosPorGrupo(int mes, int anio);
    List<PrtTotalIncReqCumplidosResponse> getTotalesRequerimientosCumplidosPorGrupo(int mes, int anio);

    List<PrtTotalIncReqCumplidosResponse> getTotalesIncidentesCumplidosPorGrupo(int mes, int anio);
    List<RptTiemposIncReqResponse> getTiempoRestauracionIncReq(int mes, int anio);
    List<RptTiemposIncReqResponse> getTiempoRestauracionIncReqManaged(int mes, int anio);
}
