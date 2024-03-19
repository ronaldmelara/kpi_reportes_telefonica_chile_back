package tech.telefonicachile.kpibackendapi.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="log_importacion")
public class ImportLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_carga",unique=true, nullable = false)
    Integer idCarga;

    @Column(name="id_estado_importacion")
    Integer idEstadoImportacion;

    @Column(name="nombre_archivo")
    String nombreArchivo;

    @Setter
    @Column(name="usuario_carga")
    String usuarioCarga;

    @Column(name="fecha_carga")
    java.sql.Timestamp fechaCarga;

    @Column(name="fecha_importacion")
    java.sql.Timestamp fechaImportacion;

    @Column(name="resumen_importacion")
    String resumenImportacion;

    @Column(name="id_datasource")
    Integer idDatasource;

    @Column(name="id_tipo_reporte")
    Integer idTipoReporte;
    public void setFechaCarga(Long fechaCarga) {

        this.fechaCarga = new java.sql.Timestamp(fechaCarga);
    }

}
