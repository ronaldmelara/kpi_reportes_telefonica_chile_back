package tech.telefonicachile.kpibackendapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.telefonicachile.kpibackendapi.dtos.response.*;
import tech.telefonicachile.kpibackendapi.repository.IReportsRepository;

import java.util.List;

@Service
public class ReportsIncReqServices {
    @Autowired
    private IReportsRepository reportsRepository;


    public List<RptTotalesIncReqResponse> getTotalesIncidentesRequerimientosPorGrupo(int mes, int anio){
        return reportsRepository.getTotalesIncidentesRequerimientosPorGrupo(mes, anio);
    }

    public List<PrtTotalIncReqCumplidosResponse> getTotalesRequerimientosCumplidosPorGrupo(int mes, int anio){
        return reportsRepository.getTotalesRequerimientosCumplidosPorGrupo(mes, anio);
    }

    public List<PrtTotalIncReqCumplidosResponse> getTotalesIncidentesCumplidosPorGrupo(int mes, int anio){
        return reportsRepository.getTotalesIncidentesCumplidosPorGrupo(mes, anio);
    }

    public List<RptTiemposIncReqResponse> getTiempoRestauracionIncReq(int mes, int anio){
        return reportsRepository.getTiempoRestauracionIncReq(mes, anio);
    }

    public List<RptTiemposIncReqResponse> getTiempoRestauracionIncReqManaged(int mes, int anio){
        return reportsRepository.getTiempoRestauracionIncReqManaged(mes, anio);
    }

    public List<RptTiemposIncReqResponse> getTiempoRestauracionIncReqServiceDelivery(int mes, int anio){
        return reportsRepository.getTiempoRestauracionIncReqServiceDelivery(mes, anio);
    }

    public List<RptTotalesIncPrioridadResponse> getTotalIncidentesPorPrioridad(int mes, int anio){
        return reportsRepository.getTotalIncidentesPorPrioridad(mes, anio);
    }

    public List<RptTiemposUrgenciaIncReqResponse> getUrgenciaIncReq(int mes, int anio){
        return reportsRepository.getUrgenciaIncReq(mes, anio);
    }
}
