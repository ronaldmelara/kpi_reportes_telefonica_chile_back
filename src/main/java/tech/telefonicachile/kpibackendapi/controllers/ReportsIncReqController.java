package tech.telefonicachile.kpibackendapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.telefonicachile.kpibackendapi.dtos.response.PrtTotalReqCumplidosResponse;
import tech.telefonicachile.kpibackendapi.dtos.response.RptTotalesIncReqResponse;
import tech.telefonicachile.kpibackendapi.services.ReportsIncReqServices;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports/inc")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Reportes (Tablas Dinámicas)", description = "Reportes (Tablas Dinámicas)")
public class ReportsIncReqController {

    @Autowired
    private ReportsIncReqServices reportsIncReqServices;

    @Operation(summary = "Reporte Total de Incidentes y Requerimientos por Grupo Asignado", description = "Total de Incidentes y Requerimientos por Grupo Asignado")
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

    @Operation(summary = "Reporte Total de Requerimientos Cumplidos por Grupo Asignado", description = "Reporte Total de Requerimientos Cumplidos por Grupo Asignado")
    @GetMapping("/rpt2/{anio}/{mes}")
    @ResponseBody
    public ResponseEntity<List<PrtTotalReqCumplidosResponse>> getTotalesRequerimientosCumplidosPorGrupo(@PathVariable int anio, @PathVariable int mes){
        List<PrtTotalReqCumplidosResponse> importRespons = reportsIncReqServices.getTotalesRequerimientosCumplidosPorGrupo(mes, anio);

        if(importRespons.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(importRespons);
        }
    }
}
