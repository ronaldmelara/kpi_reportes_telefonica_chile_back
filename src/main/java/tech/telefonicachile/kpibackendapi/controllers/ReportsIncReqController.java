package tech.telefonicachile.kpibackendapi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.telefonicachile.kpibackendapi.dtos.response.ImportResponse;
import tech.telefonicachile.kpibackendapi.dtos.response.RptTotalesIncReqResponse;
import tech.telefonicachile.kpibackendapi.services.ReportsIncReqServices;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports/inc")
@RequiredArgsConstructor
public class ReportsIncReqController {

    @Autowired
    private ReportsIncReqServices reportsIncReqServices;
    @GetMapping("/rpt1/{anio}/{mes}")
    @ResponseBody
    public ResponseEntity<List<RptTotalesIncReqResponse>> getTotalesIncidentesRequerimientosPorGrupo(@PathVariable int anio, @PathVariable int mes){
        List<RptTotalesIncReqResponse> importRespons = reportsIncReqServices.getTotalesIncidentesRequerimientosPorGrupo(mes, anio);

        if(importRespons.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(importRespons);
        }
    }
}
