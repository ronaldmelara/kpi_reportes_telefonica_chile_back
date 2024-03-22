package tech.telefonicachile.kpibackendapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.telefonicachile.kpibackendapi.dtos.response.PrtTotalIncReqCumplidosResponse;
import tech.telefonicachile.kpibackendapi.dtos.response.RptTiemposIncReqResponse;
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
    @GetMapping("/rpt01/{anio}/{mes}")
    @ResponseBody
    public ResponseEntity<List<RptTotalesIncReqResponse>> getTotalesIncidentesRequerimientosPorGrupo(@PathVariable int anio, @PathVariable int mes){
        List<RptTotalesIncReqResponse> result = reportsIncReqServices.getTotalesIncidentesRequerimientosPorGrupo(mes, anio);

        if(result.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result);
        }
    }

    @Operation(summary = "Reporte Total de Requerimientos Cumplidos por Grupo Asignado", description = "Reporte Total de Requerimientos Cumplidos por Grupo Asignado")
    @GetMapping("/rpt02/{anio}/{mes}")
    @ResponseBody
    public ResponseEntity<List<PrtTotalIncReqCumplidosResponse>> getTotalesRequerimientosCumplidosPorGrupo(@PathVariable int anio, @PathVariable int mes){
        List<PrtTotalIncReqCumplidosResponse> result = reportsIncReqServices.getTotalesRequerimientosCumplidosPorGrupo(mes, anio);

        if(result.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result);
        }
    }

    @Operation(summary = "Reporte Total de Incidentes Cumplidos por Grupo Asignado", description = "Reporte Total de Incidentes Cumplidos por Grupo Asignado")
    @GetMapping("/rpt03/{anio}/{mes}")
    @ResponseBody
    public ResponseEntity<List<PrtTotalIncReqCumplidosResponse>> getTotalesIncidentesCumplidosPorGrupo(@PathVariable int anio, @PathVariable int mes){
        List<PrtTotalIncReqCumplidosResponse> result = reportsIncReqServices.getTotalesIncidentesCumplidosPorGrupo(mes, anio);

        if(result.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result);
        }
    }

    @Operation(summary = "Reporte Tiempo de Restauracion de Incidentes y Requerimientos por Grupo Asignado", description = "Reporte Tiempo de Restauracion de Incidentes y Requerimientos por Grupo Asignado")
    @GetMapping("/rpt04/{anio}/{mes}")
    @ResponseBody
    public ResponseEntity<List<RptTiemposIncReqResponse>> getTiempoRestauracionIncReq(@PathVariable int anio, @PathVariable int mes){
        List<RptTiemposIncReqResponse> result = reportsIncReqServices.getTiempoRestauracionIncReq(mes, anio);

        if(result.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result);
        }
    }

    @Operation(summary = "Reporte Tiempo de Restauracion de Incidentes y Requerimientos Managed por Grupo Asignado", description = "Reporte Tiempo de Restauracion de Incidentes y Requerimientos Managed por Grupo Asignado")
    @GetMapping("/rpt04/{anio}/{mes}")
    @ResponseBody
    public ResponseEntity<List<RptTiemposIncReqResponse>> getTiempoRestauracionIncReqManaged(@PathVariable int anio, @PathVariable int mes){
        List<RptTiemposIncReqResponse> result = reportsIncReqServices.getTiempoRestauracionIncReqManaged(mes, anio);

        if(result.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result);
        }
    }
}
