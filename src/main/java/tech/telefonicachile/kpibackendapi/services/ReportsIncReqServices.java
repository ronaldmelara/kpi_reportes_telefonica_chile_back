package tech.telefonicachile.kpibackendapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.telefonicachile.kpibackendapi.dtos.response.PrtTotalReqCumplidosResponse;
import tech.telefonicachile.kpibackendapi.dtos.response.RptTotalesIncReqResponse;
import tech.telefonicachile.kpibackendapi.repository.IReportsRepository;

import java.util.List;

@Service
public class ReportsIncReqServices {
    @Autowired
    private IReportsRepository reportsRepository;


    public List<RptTotalesIncReqResponse> getTotalesIncidentesRequerimientosPorGrupo(int mes, int anio){
        return reportsRepository.getTotalesIncidentesRequerimientosPorGrupo(mes, anio);
    }

    public List<PrtTotalReqCumplidosResponse> getTotalesRequerimientosCumplidosPorGrupo(int mes, int anio){
        return reportsRepository.getTotalesRequerimientosCumplidosPorGrupo(mes, anio);
    }
}
