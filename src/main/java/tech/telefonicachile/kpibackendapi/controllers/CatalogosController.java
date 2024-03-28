package tech.telefonicachile.kpibackendapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tech.telefonicachile.kpibackendapi.dtos.internals.ObjectValueDto;
import tech.telefonicachile.kpibackendapi.dtos.response.DatasourcesResponse;
import tech.telefonicachile.kpibackendapi.dtos.response.ImportResponse;
import tech.telefonicachile.kpibackendapi.services.CatalogoServices;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Catalogo/List de elementos", description = "Catalogo/List de elementos")
public class CatalogosController {


    @Autowired
    private CatalogoServices catalogoServices;

    @Operation(summary = "Obtener lista de datasources", description = "Obtener lista de datasources")
    @GetMapping("datasource")
    @ResponseBody
    public ResponseEntity<List<DatasourcesResponse>> getListDatasource(){
        List<DatasourcesResponse> result = catalogoServices.getListDatasource();

        if(result.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result);
        }
    }

    @Operation(summary = "Obtener lista de tipos de reportes", description = "Obtener lista de tipos de reportes")
    @GetMapping("reports")
    @ResponseBody
    public ResponseEntity<List<ObjectValueDto>> getListReportTypes(){
        List<ObjectValueDto> result = catalogoServices.getListReportTypes();

        if(result.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result);
        }
    }
}
