package tech.telefonicachile.kpibackendapi.dtos.internals;

import com.poiji.annotation.ExcelCell;

public interface IncFileBase {
    String ticket = "";

    public String impacto = "";

    String urgencia= "";

    String prioridad= "";

    String estado= "";

    String cliente= "";

    String fechaNotificacion= "";

    String fechaEnvio= "";

    String fechaUltimaResolucion= "";

    String fechaCierre= "";

    String grupoAsignado= "";

    String responsable= ""; //tambien nombrado "Usuario asignado"

    String resumen= "";

    String servicio= ""; //tambine nombrado como "Servicio ISO 20000"

    String remitente= "";

    String tipoIncidencia= ""; //tambien nombrado "Tipo"

    String EstadoSlm= "";

}
