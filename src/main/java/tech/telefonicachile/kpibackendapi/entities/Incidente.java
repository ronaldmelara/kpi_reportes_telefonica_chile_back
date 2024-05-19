package tech.telefonicachile.kpibackendapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="incidencias_requerimientos")
public class Incidente {

    @Column(name="id_reporte")
    Integer idReporte;

    @Id
    @Column(name="ticket")
    String ticket;
    @Column(name="id_impacto")
    Integer idImpacto;
    @Column(name="id_urgencia")
    Integer idUrgencia;

    @Column(name="id_prioridad")
    Integer idPrioridad;
    @Column(name="id_estado_ticket")
    Integer idEstadoTicket;
    @Column(name="id_cliente")
    Integer idCliente;
    @Column(name="fecha_notificacion")
    String fechaNotificacion;

    @Column(name="fecha_envio")
    String fechaEnvio;
    @Column(name="fecha_ultima_resolucion")
    String fechaUltimaResolucion;
    @Column(name="fecha_cierre")
    String fechaCierre;
    @Column(name="id_grupo_asignacion")
    Integer idGrupoAsignado;
    @Column(name="responsable")
    String responsable;
    @Column(name="resumen")
    String resumen;


    @Column(name="id_servicio")
    Integer idServicio;
    @Column(name="remitente")
    String remitente;
    @Column(name="id_tipo_incidencia")
    Integer idTipoIncidencia;
    @Column(name="estado_slm")
    String estadoSlm;
    @Column(name="tiempo_resolucion")
    String tiempoResolucion;

    @Column(name="pendiente")
    String pendiente;
    @Column(name="e2e")
    String e2e;
    @Column(name="sla")
    String sla;



    @Column(name = "inc_cumple_kpi")
    Integer incCumpleKpi;

    @Column(name = "req_cumple_kpi")
    Integer reqCumpleKpi;
    @Column(name="observaciones")
    String observaciones;



    @Column(name = "cumple_critica")
    Integer cumpleCritica;

    @Column(name = "cumple_alta")
    Integer cumpleAlta;

    @Column(name = "cumple_media")
    Integer cumpleMedia;

    @Column(name = "cumple_baja")
    Integer cumpleBaja;

    @Column(name = "m1h_sla")
    Integer menos1hSla;

    @Column(name = "m1hm4h_sla")
    Integer mas1hMenos4hrSla;

    @Column(name = "m4hm8h_sla")
    Integer mas4hMenos8hSla;

    @Column(name = "m8hm24_sla")
    Integer mas8hMenos24hSla;

    @Column(name = "m24h_sla")
    Integer mas24hSla;



    @Column(name = "m1h_e2e")
    Integer menos1he2e;

    @Column(name = "m1hm4h_e2e")
    Integer mas1hMenos4hre2e;

    @Column(name = "m4hm8h_e2e")
    Integer mas4hMenos8he2e;

    @Column(name = "m8hm24h_e2e")
    Integer mas8hMenos24he2e;

    @Column(name = "m24h_e2e")
    Integer mas24he2e;

    @Column(name="id_catpresol1")
    Integer idCatpresol1;
    @Column(name="id_catpresol2")
    Integer idCatpresol2;
    @Column(name="notas")
    String notas;
    @Column(name="resolucion")
    String resolucion;
    @Column(name="id_grupo_propietario")
    Integer idGrupoPropietario;
    @Column(name = "id_urgencia_ob")
    Integer idUrgenciaOb;
    @Column(name = "bloqueado")
    Integer bloqueado;
}
