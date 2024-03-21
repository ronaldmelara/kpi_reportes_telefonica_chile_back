package tech.telefonicachile.kpibackendapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.telefonicachile.kpibackendapi.dtos.request.ImportRequest;
import tech.telefonicachile.kpibackendapi.entities.ImportLog;
import tech.telefonicachile.kpibackendapi.dtos.response.ImportResponse;
import tech.telefonicachile.kpibackendapi.services.ImportServices;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/import")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Importación de Archivos", description = "Importación de Archivos")
public class ImportController {

    @Autowired
    private ImportServices importServices;

    @Operation(summary = "Importar un archivo como insumo para reportes", description = "Importar un archivo como insumo para reportes")
    @PostMapping("")
    public ResponseEntity<ImportLog> register(@RequestParam(value = "file") MultipartFile file,
                                                     @RequestParam("idDatasource") int idDatasoruce,
                                                     @RequestParam("nombreArchivo") String nombreArchivo,
                                                     @RequestParam("usuarioCarga") String usuarioCarga,
                                              @RequestParam("mes") int mes, @RequestParam("anio") int anio,
                                              @RequestParam("idTipoReporte") int idTipoReporte) {


        byte[] bytesDelArchivo;
        try {
            bytesDelArchivo = file.getBytes();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        ImportRequest importRequest = new ImportRequest();
        importRequest.setArchivo(bytesDelArchivo);
        importRequest.setIdDatasource(idDatasoruce);
        importRequest.setUsuarioCarga(usuarioCarga);
        importRequest.setNombreArchivo(nombreArchivo);
        importRequest.setMes(mes);
        importRequest.setAnio(anio);
        importRequest.setIdTipoReporte(idTipoReporte);
        return ResponseEntity.ok(importServices.save(importRequest));

    }

    @Operation(summary = "Obtener lista de archivos importados", description = "Obtener lista de archivos importados")
    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<ImportResponse>> get(){
        List<ImportResponse> importRespons = importServices.getListImports();

        if(importRespons.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(importRespons);
        }
    }

//    @GetMapping(value = "/loaded/{idCarga}")
//    public ResponseEntity<List<TemporalIncidencias>> getLoadedDataByIdCarga(@PathVariable int idCarga){
//        List<TemporalIncidencias> importedFileDTOS = importServices.getLoadedDataByIdCarga(idCarga);
//
//        if(importedFileDTOS.isEmpty()){
//            return ResponseEntity.noContent().build();
//        }else{
//            return ResponseEntity.ok(importedFileDTOS);
//        }
//    }
}
