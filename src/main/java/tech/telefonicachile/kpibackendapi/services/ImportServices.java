package tech.telefonicachile.kpibackendapi.services;

import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import tech.telefonicachile.kpibackendapi.dtos.internals.IncFileExternalDTO;
import tech.telefonicachile.kpibackendapi.dtos.internals.IncFileRemedyDTO;
import tech.telefonicachile.kpibackendapi.dtos.request.ImportRequest;
import tech.telefonicachile.kpibackendapi.entities.ImportLog;
import tech.telefonicachile.kpibackendapi.dtos.response.ImportResponse;
import tech.telefonicachile.kpibackendapi.entities.TemporalIncidente;
import tech.telefonicachile.kpibackendapi.helper.EnumDatasource;
import tech.telefonicachile.kpibackendapi.repository.TmpIncidentesRepository;
import tech.telefonicachile.kpibackendapi.repository.ImportRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ImportServices {
    @Autowired
    private ImportRepository importRepository;

    @Autowired
    private TmpIncidentesRepository tmpIncidentesRepository;

    @Autowired
    private DataProcessingService dataProcessingService;

    public ImportLog save(ImportRequest request) {


        List<Object[]> objRpt = importRepository.getExistReport(request.getIdTipoReporte(), request.getMes(), request.getAnio());
        boolean flag = (objRpt != null && !objRpt.isEmpty());

        if(!flag){
            int affected = importRepository.insertNewReport(request.getIdTipoReporte(), request.getMes(), request.getAnio());
            objRpt = importRepository.getExistReport(request.getIdTipoReporte(), request.getMes(), request.getAnio());
        }
        else{
            if(((int)(objRpt.getFirst())[1] ) == 1 ){
                throw new IllegalArgumentException("El periodo seleccionado ya se encuentra cerrado");
            }
        }


        ImportLog importLog = new ImportLog();
        importLog.setIdEstadoImportacion(3); // Aquí deberías establecer el estado correcto
        importLog.setFechaCarga(System.currentTimeMillis());
        importLog.setNombreArchivo(request.getNombreArchivo());
        importLog.setIdDatasource(request.getIdDatasource());
        importLog.setUsuarioCarga(request.getUsuarioCarga());
        importLog.setIdTipoReporte(request.getIdTipoReporte());
        ImportLog nuevaCarga  = importRepository.save(importLog);

        // Guardar el archivo en el sistema de archivos (path)
        saveFileInPath(request.getArchivo(),request.getNombreArchivo());



        bulkData(request.getIdDatasource(), "C:\\DEV\\Docs\\" + request.getNombreArchivo(), ((int)(objRpt.getFirst())[0] ));

        //excelDataToListCloudServices("C:\\DEV\\Docs\\" + cargaDatos.getIdCarga() + ".xlsx", cargaDatos.getIdCarga() );

        return nuevaCarga;
    }

    private void saveFileInPath(byte[] archivo, String nameFile) {
        // Lógica para guardar el archivo en un path específico
        // Puedes usar librerías como java.nio.file.Files para hacer esto
        // Por ejemplo: Files.write(Paths.get("/ruta/del/archivo/" + idCarga + ".pdf"), archivo);

        //byte[] tFile = file.getFile();
        if(archivo == null || archivo.length == 0){
            throw new IllegalArgumentException("No se envió ningún archivo.");
        }

        File file = new File("C:\\DEV\\Docs\\" + nameFile);
        if(file.exists()){
            file.delete();
        }


        int index = nameFile.lastIndexOf('.');
        if(index > 0) {
            String extension = nameFile.substring(index + 1);
           if(!extension.equals("xlsx")){
               throw new IllegalArgumentException("Extensión no valida");
           }
        }


        try (FileOutputStream stream = new FileOutputStream("C:\\DEV\\Docs\\" + nameFile)) {
            stream.write(archivo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ImportResponse> getListImports(){
        List<Object[]> resultList = importRepository.getListImports();
        List<ImportResponse> importResponseList = new ArrayList<>();

        for (Object[] row : resultList) {
            ImportResponse importResponse = new ImportResponse();
            importResponse.setIdCarga((Integer) row[0]);
            importResponse.setNombreArchivo((String) row[1]);

            importResponse.setEstado((String) row[2]);
            importResponse.setDatasource((String) row[3]);
            importResponse.setNombreUsuario((String) row[4]);
            importResponse.setFechaCarga((java.sql.Timestamp) row[5]);
            importResponse.setFechaImportacion((java.sql.Timestamp) row[6]);
            importResponse.setResumenImportacion((String) row[7]);
            importResponse.setIdEstadoImportacion((Integer) row[8]);


            importResponseList.add(importResponse);
        }

        return importResponseList;

    }

    private void bulkData(int typeFile, String fileLocation, int idReporte){

        EnumDatasource selected = null;
        if(typeFile == 1){ selected = EnumDatasource.REMEDY_INC_ING_SISTEMAS; }
        if(typeFile == 2){ selected = EnumDatasource.EXTERNO_INC_IYR_INGSYS_SEMANAL; }
        if(typeFile == 3){ selected = EnumDatasource.EXTERNO_INC_IYR_INGSYS_MENSUAL; }
        if(typeFile == 4){ selected = EnumDatasource.REMEDY_CRQ_INGENIERIA_TASK; }
        if(typeFile == 5){ selected = EnumDatasource.EXTERNO_CM_CUADRO_MANDO_INGSYS; }

        int totRows = 0;

        try{
            switch (selected)
            {
                case EnumDatasource.REMEDY_INC_ING_SISTEMAS:
                    totRows = excelDataIncToListFromRemedy(fileLocation);
                    break;
                case EnumDatasource.EXTERNO_INC_IYR_INGSYS_SEMANAL:
                case EnumDatasource.EXTERNO_INC_IYR_INGSYS_MENSUAL:
                    totRows =excelDataIncToListFromExternal(fileLocation);
                    break;
            }
            if(totRows>0){
                dataProcessingService.dataProcessIncidentesRequerimientos(idReporte,selected,this);
            }

        }catch (Exception ex){
            System.out.println("Excepción al extraer datos.");
            System.out.println(ex.getMessage());
        }

    }

    @Async
    public int excelDataIncToListFromRemedy(String fileLocation){
        List<TemporalIncidente> finalRows = new ArrayList();
        try {
            PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
                    .headerStart(1)
                    .build();

            List<IncFileRemedyDTO> rows = Poiji.fromExcel(new File(fileLocation), IncFileRemedyDTO.class, options);
            for(IncFileRemedyDTO row : rows){
                if(!row.getTicket().isEmpty() && row.getTicket().startsWith("INC")){
                    TemporalIncidente newRow = TemporalIncidente.builder()
                            .ticket(StringUtils.trimToEmpty(row.getTicket()))
                            .impacto(StringUtils.trimToEmpty(row.getImpacto()))
                            .urgencia(StringUtils.trimToEmpty(row.getUrgencia()))
                            .prioridad(StringUtils.trimToEmpty(row.getPrioridad()))
                            .estado(StringUtils.trimToEmpty(row.getEstado()))
                            .cliente(StringUtils.trimToEmpty(row.getCliente()))
                            .fechaNotificacion(StringUtils.trimToEmpty(row.getFechaNotificacion()))
                            .fechaEnvio(StringUtils.trimToEmpty(row.getFechaEnvio()))

                            .fechaUltimaResolucion(StringUtils.trimToEmpty(row.getFechaUltimaResolucion()))
                            .fechaCierre(StringUtils.trimToEmpty(row.getFechaCierre()))
                            .grupoAsignado(StringUtils.trimToEmpty(row.getGrupoAsignado()))
                            .responsable(StringUtils.trimToEmpty(row.getResponsable()))
                            .resumen(StringUtils.trimToEmpty(row.getResumen()))
                            .servicio(StringUtils.trimToEmpty(row.getServicio()))
                            .remitente(StringUtils.trimToEmpty(row.getRemitente()))
                            .tipoIncidencia(StringUtils.trimToEmpty(row.getTipoIncidencia()))
                            .estadoSlm(StringUtils.trimToEmpty(row.getEstadoSlm()))

                            .build();
                    finalRows.add(newRow);
                }

            }


          return saveAllRowsIncReport(finalRows);

        }catch (Exception ex){
            tmpIncidentesRepository.deleteAll(finalRows);
            return 0;
        }
    }

    @Async
    public int excelDataIncToListFromExternal(String fileLocation){

        List<TemporalIncidente> finalRows = new ArrayList();
        try{
            PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
                    .headerStart(0)
                    .build();

            List<IncFileExternalDTO> rows = Poiji.fromExcel(new File(fileLocation), IncFileExternalDTO.class, options);

            for(IncFileExternalDTO row : rows){
                if(!row.getTicket().isEmpty() && row.getTicket().startsWith("INC")) {
                    TemporalIncidente newRow = TemporalIncidente.builder()
                            .e2e(StringUtils.trimToEmpty(row.getE2e()))
                            .estado(StringUtils.trimToEmpty(row.getEstado()))
                            .cliente(StringUtils.trimToEmpty(row.getCliente()))
                            .catpresol2(StringUtils.trimToEmpty(row.getCatpresol2()))
                            .catpresol1(StringUtils.trimToEmpty(row.getCatpresol1()))
                            .fechaCierre(StringUtils.trimToEmpty(row.getFechaCierre()))
                            .fechaEnvio(StringUtils.trimToEmpty(row.getFechaEnvio()))
                            .fechaNotificacion(StringUtils.trimToEmpty(row.getFechaNotificacion()))
                            .notas(StringUtils.trimToEmpty(row.getNotas()))
                            .grupoAsignado(StringUtils.trimToEmpty(row.getGrupoAsignado()))
                            .grupoPropietario(StringUtils.trimToEmpty(row.getGrupoPropietario()))
                            .fechaUltimaResolucion(StringUtils.trimToEmpty(row.getFechaResolucion()))
                            .impacto(StringUtils.trimToEmpty(row.getImpacto()))
                            .pendiente(StringUtils.trimToEmpty(row.getPendiente()))
                            .prioridad(StringUtils.trimToEmpty(row.getPrioridad()))
                            .remitente(StringUtils.trimToEmpty(row.getRemitente()))
                            .sla(StringUtils.trimToEmpty(row.getSla()))
                            .resolucion(StringUtils.trimToEmpty(row.getResolucion()))
                            .sla(StringUtils.trimToEmpty(row.getSla()))
                            .resumen(StringUtils.trimToEmpty(row.getResumen()))
                            .responsable(StringUtils.trimToEmpty(row.getResponsable()))
                            .servicio(StringUtils.trimToEmpty(row.getServicio()))
                            .ticket(StringUtils.trimToEmpty(row.getTicket()))
                            .tipoIncidencia(StringUtils.trimToEmpty(row.getTipoIncidencia()))
                            .urgencia(StringUtils.trimToEmpty(row.getUrgencia()))
                            .prioridad(StringUtils.trimToEmpty(row.getPrioridad()))
                            .build();
                    finalRows.add(newRow);
                }


                //fileCloudServicesRepository.saveAll(newRow);
            }
            return saveAllRowsIncReport(finalRows);

//            importRepository.updateImportStatus(5,idCarga, "Process completed. Ready for review.");

            //dataProcessingService.dataProccessCloudServices(idCarga,  this);

        }catch (Exception e){
//            importRepository.updateImportStatus(3,idCarga, "Process failed");
            tmpIncidentesRepository.deleteAll(finalRows);
            return 0;
        }

    }

    @Transactional
    private Integer saveAllRowsIncReport(List<TemporalIncidente> list){
        List<TemporalIncidente> persistedRows = tmpIncidentesRepository.saveAll(list);
        return persistedRows.size();
    }

    public List<TemporalIncidente> getDataImported(){
        return tmpIncidentesRepository.findAll();
    }
}
