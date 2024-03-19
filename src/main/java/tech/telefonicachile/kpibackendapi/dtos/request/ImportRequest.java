package tech.telefonicachile.kpibackendapi.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImportRequest {

    Integer idDatasource;
     String nombreArchivo;
     String usuarioCarga;
     byte[] archivo;
     int mes;
     int anio;
     int idTipoReporte;
}
