package tech.telefonicachile.kpibackendapi.dtos.response;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ImportResponse {

    Integer idCarga;



    String nombreArchivo;

    String estado;
    String datasource;


    String nombreUsuario;

    String resumenImportacion;


    private java.sql.Timestamp fechaCarga;


    private java.sql.Timestamp fechaImportacion;


    Integer idEstadoImportacion;

}
