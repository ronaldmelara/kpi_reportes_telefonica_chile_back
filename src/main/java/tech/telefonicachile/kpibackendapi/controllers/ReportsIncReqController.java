package tech.telefonicachile.kpibackendapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.telefonicachile.kpibackendapi.dtos.response.*;
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
    @GetMapping("/rpt05/{anio}/{mes}")
    @ResponseBody
    public ResponseEntity<List<RptTiemposIncReqResponse>> getTiempoRestauracionIncReqManaged(@PathVariable int anio, @PathVariable int mes){
        List<RptTiemposIncReqResponse> result = reportsIncReqServices.getTiempoRestauracionIncReqManaged(mes, anio);

        if(result.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result);
        }
    }

    @Operation(summary = "Reporte Tiempo de Restauracion de Incidentes y Requerimientos Service Delivery", description = "Reporte Tiempo de Restauracion de Incidentes y Requerimientos Service Delivery")
    @GetMapping("/rpt06/{anio}/{mes}")
    @ResponseBody
    public ResponseEntity<List<RptTiemposIncReqResponse>> getTiempoRestauracionIncReqServiceDelivery(@PathVariable int anio, @PathVariable int mes){
        List<RptTiemposIncReqResponse> result = reportsIncReqServices.getTiempoRestauracionIncReqServiceDelivery(mes, anio);

        if(result.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result);
        }
    }

    @Operation(summary = "Reporte Total de Incidentes por Prioridad", description = "Reporte Total de Incidentes por Prioridad")
    @GetMapping("/rpt07/{anio}/{mes}")
    @ResponseBody
    public ResponseEntity<List<RptTotalesIncPrioridadResponse>> getTotalIncidentesPorPrioridad(@PathVariable int anio, @PathVariable int mes){
        List<RptTotalesIncPrioridadResponse> result = reportsIncReqServices.getTotalIncidentesPorPrioridad(mes, anio);

        if(result.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result);
        }
    }

    @Operation(summary = "Reporte Tiempo de Incidentes/Requerimientos por Urgencia", description = "Reporte Tiempo de Incidentes/Requerimientos por Urgencia")
    @GetMapping("/rpt08/{anio}/{mes}")
    @ResponseBody
    public ResponseEntity<List<RptTiemposUrgenciaIncReqResponse>> getUrgenciaIncReq(@PathVariable int anio, @PathVariable int mes){
        List<RptTiemposUrgenciaIncReqResponse> result = reportsIncReqServices.getUrgenciaIncReq(mes, anio);

        if(result.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result);
        }
    }
    @Operation(summary = "Reporte URGENCIA INCIDENTES Y REQUERIMIENTOS SERVICE DELIVERY", description = "Reporte URGENCIA INCIDENTES Y REQUERIMIENTOS SERVICE DELIVERY")
    @GetMapping("/rpt09/{anio}/{mes}")
    @ResponseBody
    public ResponseEntity<List<RptTiemposUrgenciaGroupIncReqResponse>> getUrgenciaServiceDeliveryIncReq(@PathVariable int anio, @PathVariable int mes){
        List<RptTiemposUrgenciaGroupIncReqResponse> result = reportsIncReqServices.getUrgenciaServiceDeliveryIncReq(mes, anio);

        if(result.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result);
        }
    }

    @Operation(summary = "Reporte URGENCIA INCIDENTES Y REQUERIMIENTOS MANAGED", description = "Reporte URGENCIA INCIDENTES Y REQUERIMIENTOS MANAGED")
    @GetMapping("/rpt10/{anio}/{mes}")
    @ResponseBody
    public ResponseEntity<List<RptTiemposUrgenciaGroupIncReqResponse>> getUrgenciaServiceManagedIncReq(@PathVariable int anio, @PathVariable int mes){
        List<RptTiemposUrgenciaGroupIncReqResponse> result = reportsIncReqServices.getUrgenciaServiceManagedIncReq(mes, anio);

        if(result.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result);
        }
    }

    @Operation(summary = "Reporte URGENCIA INCIDENTES Y REQUERIMIENTOS CLOUD", description = "Reporte URGENCIA INCIDENTES Y REQUERIMIENTOS CLOUD")
    @GetMapping("/rpt11/{anio}/{mes}")
    @ResponseBody
    public ResponseEntity<List<RptTiemposUrgenciaGroupIncReqResponse>> getUrgenciaServiceCloudIncReq(@PathVariable int anio, @PathVariable int mes){
        List<RptTiemposUrgenciaGroupIncReqResponse> result = reportsIncReqServices.getUrgenciaServiceCloudIncReq(mes, anio);

        if(result.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result);
        }
    }
}
