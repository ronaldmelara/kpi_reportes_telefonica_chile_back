package tech.telefonicachile.kpibackendapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="temporal_incidencias")
public class TemporalIncidente {

    @Id
    @Column(name="ticket")
    String ticket;

    @Column(name="servicio")
    String servicio;
    @Column(name="grupo_asignado")
    String grupoAsignado;
    @Column(name="grupo_propietarios")
    String grupoPropietario;
    @Column(name="responsable")
    String responsable;
    @Column(name="remitente")
    String remitente;
    @Column(name="impacto")
    String impacto;
    @Column(name="urgencia")
    String urgencia;
    @Column(name="prioridad")
    String prioridad;
    @Column(name="tipo_incidencia")
    String tipoIncidencia;
    @Column(name="cliente")
    String cliente;
    @Column(name="resumen")
    String resumen;
    @Column(name="fecha_notificacion")
    String fechaNotificacion;
    @Column(name="fecha_envio")
    String fechaEnvio;
    @Column(name="estado")
    String estado;
    @Column(name="catpresol1")
    String catpresol1;
    @Column(name="catpresol2")
    String catpresol2;
    @Column(name="fecha_ultima_resolucion")
    String fechaUltimaResolucion;
    @Column(name="fecha_cierre")
    String fechaCierre;
    @Column(name="notas")
    String notas;
    @Column(name="resolucion")
    String resolucion;
    @Column(name="pendiente")
    String pendiente;
    @Column(name="e2e")
    String e2e;
    @Column(name="sla")
    String sla;
    @Column(name="estado_slm")
    String estadoSlm;
}
