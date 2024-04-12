package tech.telefonicachile.kpibackendapi.repository;

import tech.telefonicachile.kpibackendapi.dtos.response.*;

import java.util.List;

public interface IReportsRepository {
    List<RptTotalesIncReqResponse> getTotalesIncidentesRequerimientosPorGrupo(int mes, int anio);
    List<PrtTotalIncReqCumplidosResponse> getTotalesRequerimientosCumplidosPorGrupo(int mes, int anio);

    List<PrtTotalIncReqCumplidosResponse> getTotalesIncidentesCumplidosPorGrupo(int mes, int anio);
    List<RptTiemposIncReqResponse> getTiempoRestauracionIncReq(int mes, int anio);
    List<RptTiemposIncReqResponse> getTiempoRestauracionIncReqManaged(int mes, int anio);
    List<RptTiemposIncReqResponse> getTiempoRestauracionIncReqServiceDelivery(int mes, int anio);
    List<RptTotalesIncPrioridadResponse> getTotalIncidentesPorPrioridad(int mes, int anio);
    List<RptTiemposUrgenciaIncReqResponse> getUrgenciaIncReq(int mes, int anio);
}
