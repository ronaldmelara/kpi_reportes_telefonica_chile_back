package tech.telefonicachile.kpibackendapi.services;

import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.telefonicachile.kpibackendapi.dtos.internals.ObjectValueDto;
import tech.telefonicachile.kpibackendapi.entities.Incidente;
import tech.telefonicachile.kpibackendapi.entities.TemporalIncidente;
import tech.telefonicachile.kpibackendapi.helper.EnumDatasource;
import tech.telefonicachile.kpibackendapi.repository.ICatalogosRepository;
import tech.telefonicachile.kpibackendapi.repository.IncidentesRepository;


import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DataProcessingService {

    @Autowired
    private IncidentesRepository incidentesRepository;

    @Autowired
    private ICatalogosRepository catalogosRepository;

    final int TIEMPO_1HRS = 1;
    final int TIEMPO_4HRS = 4;
    final int TIEMPO_8HRS = 8;
    final int TIEMPO_12HRS = 12;
    final int TIEMPO_24HRS = 24;
    final int TIEMPO_48HRS = 48;

    final Integer ID_INCIDENTE_CLIENTE = 2;
    final Integer ID_REQUERIMIENTO_CLIENTE = 1;
    final Integer ID_URGENCIA_CRITICA = 1;
    final Integer ID_URGENCIA_ALTA = 2;
    final Integer ID_URGENCIA_MEDIA = 3;
    final Integer ID_URGENCIA_BAJA = 4;


    public void dataProcessIncidentesRequerimientos(int idReporte, EnumDatasource source, ImportServices importServices){

        List<TemporalIncidente> dataRaw = importServices.getDataImported();

        for(TemporalIncidente dato: dataRaw){

            boolean ticketExists = incidentesRepository.findByTicket(dato.getTicket()).isPresent();

            ObjectValueDto ent1 = catalogosRepository.getServicioIso(StringUtils.trimToEmpty(dato.getServicio()));
            ObjectValueDto ent4 = catalogosRepository.getImpacto(StringUtils.trimToEmpty(dato.getImpacto()));
            ObjectValueDto ent5 = catalogosRepository.getUrgencia(StringUtils.trimToEmpty(dato.getUrgencia()));
            ObjectValueDto ent6 = catalogosRepository.getPrioridad(StringUtils.trimToEmpty(dato.getPrioridad()));
            ObjectValueDto ent9 = catalogosRepository.getEstadoTicket(StringUtils.trimToEmpty(dato.getEstado()));
            ObjectValueDto ent8 = catalogosRepository.getCliente(StringUtils.trimToEmpty(dato.getCliente()));
//            if (ent8 == null){
//                catalogosRepository.createCliente(StringUtils.trimToEmpty(dato.getCliente()));
//                ent8 = catalogosRepository.getCliente(StringUtils.trimToEmpty(dato.getCliente()));
//            }
            ObjectValueDto ent2 = catalogosRepository.getGrupoAsignado(StringUtils.trimToEmpty(dato.getGrupoAsignado()));
//            if(ent2 == null){
//                catalogosRepository.createGrupoAsignacion(StringUtils.trimToEmpty(dato.getGrupoAsignado()));
//                ent2 = catalogosRepository.getGrupoAsignado(StringUtils.trimToEmpty(dato.getGrupoAsignado()));
//            }

            //Clasificación TIpo de Incidencia
            //Agregar regla de negocio:
            /*
                         Restauración de servicio a usuario     = Incidencia de cliente     ----->  Incidencia de cliente
                         Petición de serv. por el usuario       = Requerimiento de cliente  ----->  Requerimiento de cliente
                         Evento de infraestructura              = Requerimiento de red      ----->  Requerimiento de cliente
                         Restauración de infraestructura        = Incidencia de Red         ----->  Incidencia de cliente
            */
            String catIncidencia = "";

            if (dato.getTipoIncidencia().equalsIgnoreCase("Restauración de servicio a usuario") ||
                    dato.getTipoIncidencia().equalsIgnoreCase("Restauración de infraestructura") ||
                    dato.getTipoIncidencia().equalsIgnoreCase("Incidente de Red") ||
                    dato.getTipoIncidencia().equalsIgnoreCase("Incidente de Cliente")){
                catIncidencia = "Incidente de Cliente";
            }
            else if (dato.getTipoIncidencia().equalsIgnoreCase("Petición de serv. por el usuario") ||
                    dato.getTipoIncidencia().equalsIgnoreCase("Evento de infraestructura") ||
                    dato.getTipoIncidencia().equalsIgnoreCase("Requerimiento de Red") ||
                    dato.getTipoIncidencia().equalsIgnoreCase("Requerimiento de cliente")){
                catIncidencia = "Requerimiento de Cliente";
            }else{
                System.out.println(dato.getTipoIncidencia());
            }

            ObjectValueDto ent7 = catalogosRepository.getTipoIncidencia(StringUtils.trimToEmpty(catIncidencia).toLowerCase());


            switch (source){
                case EnumDatasource.REMEDY_INC_ING_SISTEMAS:

                    if (ticketExists)
                    {

                        incidentesRepository.updateTicketFromRemedy(Incidente.builder()
                                .idReporte(idReporte)
                                .ticket(dato.getTicket())
                                .idEstadoTicket(ent9 != null ? ent9.getId() : null)
                                .fechaUltimaResolucion(dato.getFechaUltimaResolucion())
                                .fechaCierre(dato.getFechaCierre())
                                .idGrupoAsignado(ent2 != null ? ent2.getId() : null)
                                .responsable(dato.getResponsable())
                                .idServicio(ent1 != null ? ent1.getId() : null)
                                .idTipoIncidencia(ent7 != null ? ent7.getId() : null)
                                .build());

                    }
                    else {

                        incidentesRepository.save(Incidente.builder()
                                .idReporte(idReporte)
                                .ticket(dato.getTicket())
                                .idImpacto(ent4 != null ? ent4.getId() : null)
                                .idUrgencia(ent5 != null ? ent5.getId() : null)
                                .idPrioridad(ent6 != null ? ent6.getId() : null)
                                .idEstadoTicket(ent9 != null ? ent9.getId() : null)
                                .idCliente(ent8 != null ? ent8.getId() : null)
                                .fechaNotificacion(dato.getFechaNotificacion())
                                .fechaEnvio(dato.getFechaEnvio())
                                .fechaUltimaResolucion(dato.getFechaUltimaResolucion())
                                .fechaCierre(dato.getFechaCierre())
                                .idGrupoAsignado(ent2 != null ? ent2.getId() : null)
                                .responsable(dato.getResponsable())
                                .idServicio(ent1 != null ? ent1.getId() : null)
                                .remitente(dato.getRemitente())
                                .idTipoIncidencia(ent7 != null ? ent7.getId() : null)
                                .estadoSlm(dato.getEstadoSlm())
                                .build());
                    }
                    break;
                case EnumDatasource.EXTERNO_INC_IYR_INGSYS_MENSUAL:
                case EnumDatasource.EXTERNO_INC_IYR_INGSYS_SEMANAL:


                    ObjectValueDto ent3 = catalogosRepository.getGrupoPropietario(StringUtils.trimToEmpty(dato.getGrupoPropietario()));
                    ObjectValueDto ent10 = catalogosRepository.getCatpresol1(StringUtils.trimToEmpty(dato.getCatpresol1()));
                    ObjectValueDto ent11 = catalogosRepository.getCatpresol2(StringUtils.trimToEmpty(dato.getCatpresol2()));

                    String sla = formatearHora(dato.getSla());
                    String e2e = formatearHora(dato.getE2e());
                    String pend = formatearHora(dato.getPendiente());

                    if (ticketExists)
                    {
                        incidentesRepository.updateTicketFromExternal(Incidente.builder()
                                .idGrupoAsignado(ent2 != null ? ent2.getId() : null)
                                .idGrupoPropietario(ent3 != null ? ent3.getId() : null)
                                .responsable(dato.getResponsable())
                                .remitente(dato.getRemitente())
                                .idImpacto(ent4 != null ? ent4.getId() : null)
                                .idUrgencia(ent5 != null ? ent5.getId() : null)
                                .idPrioridad(ent6 != null ? ent6.getId() : null)
                                .idTipoIncidencia(ent7 != null ? ent7.getId() : null)
                                .idEstadoTicket(ent9 != null ? ent9.getId() : null)
                                .idCatpresol1(ent10 != null ? ent10.getId() : null)
                                .idCatpresol2(ent11 != null ? ent11.getId() : null)
                                .fechaUltimaResolucion(dato.getFechaUltimaResolucion())
                                .fechaCierre(dato.getFechaCierre())
                                .notas(dato.getNotas())
                                .resolucion(dato.getResolucion())
                                .pendiente(pend)
                                .e2e(e2e)
                                .sla(sla)
                                .idUrgenciaOb(ent5 != null ? ent5.getId() : null)
                                //REGLA DE NEGOCIO: Se debe evauluar si el SLA es menor o igual a 4:00:00
                                .incCumpleKpi(CheckIncidenciaCumpleKpi(ent7, sla, ID_INCIDENTE_CLIENTE, TIEMPO_4HRS))
                                //REGLA DE NEGOCIO: Se debe evauluar si el SLA es menor o igual a 48:00:00
                                .reqCumpleKpi(CheckIncidenciaCumpleKpi(ent7, sla, ID_REQUERIMIENTO_CLIENTE, TIEMPO_48HRS))
                                //REGLA DE NEGOCIO: Se debe evauluar si el SLA es menor o igual a 4:00:00 cuando la incidencia es Crítica
                                .cumpleCritica(CheckUrgenciaCumpleKpi(ent5, sla, ID_URGENCIA_CRITICA, TIEMPO_4HRS))
                                //REGLA DE NEGOCIO: Se debe evauluar si el SLA es menor o igual a 8:00:00 cuando la incidencia es Alta
                                .cumpleAlta(CheckUrgenciaCumpleKpi(ent5, sla, ID_URGENCIA_ALTA, TIEMPO_8HRS))
                                //REGLA DE NEGOCIO: Se debe evauluar si el SLA es menor o igual a 12:00:00 cuando la incidencia es Media
                                .cumpleMedia(CheckUrgenciaCumpleKpi(ent5, sla, ID_URGENCIA_MEDIA, TIEMPO_12HRS))
                                //REGLA DE NEGOCIO: Se debe evauluar si el SLA es menor o igual a 24:00:00 cuando la incidencia es Baja
                                .cumpleBaja(CheckUrgenciaCumpleKpi(ent5, sla, ID_URGENCIA_BAJA, TIEMPO_24HRS))

                                .menos1hSla(CheckTiempoCumpleKpi(sla, TIEMPO_1HRS, EnumOperators.SMALL_THAN_OR_EQUAL))
                                .mas1hMenos4hrSla(CheckTiempoCumpleKpi(sla, TIEMPO_1HRS, TIEMPO_4HRS ))
                                .mas4hMenos8hSla(CheckTiempoCumpleKpi(sla, TIEMPO_4HRS, TIEMPO_8HRS))
                                .mas8hMenos24hSla(CheckTiempoCumpleKpi(sla, TIEMPO_8HRS, TIEMPO_24HRS))
                                .mas24hSla(CheckTiempoCumpleKpi(sla, TIEMPO_24HRS, EnumOperators.GREATER_THAN ))

                                .menos1he2e(CheckTiempoCumpleKpi(e2e, TIEMPO_1HRS, EnumOperators.SMALL_THAN_OR_EQUAL))
                                .mas1hMenos4hre2e(CheckTiempoCumpleKpi(e2e, TIEMPO_1HRS, TIEMPO_4HRS ))
                                .mas4hMenos8he2e(CheckTiempoCumpleKpi(e2e, TIEMPO_4HRS, TIEMPO_8HRS))
                                .mas8hMenos24he2e(CheckTiempoCumpleKpi(e2e, TIEMPO_8HRS, TIEMPO_24HRS))
                                .mas24he2e(CheckTiempoCumpleKpi(e2e, TIEMPO_24HRS, EnumOperators.GREATER_THAN ))

                                .build());
                    }
                    else {

                        incidentesRepository.save(Incidente.builder()
                                .idReporte(idReporte)
                                .ticket(dato.getTicket())
                                .idServicio(ent1 != null ? ent1.getId() : null)
                                .idGrupoAsignado(ent2 != null ? ent2.getId() : null)
                                .idGrupoPropietario(ent3 != null ? ent3.getId() : null)
                                .responsable(dato.getResponsable())
                                .remitente(dato.getRemitente())
                                .idImpacto(ent4 != null ? ent4.getId() : null)
                                .idUrgencia(ent5 != null ? ent5.getId() : null)
                                .idPrioridad(ent6 != null ? ent6.getId() : null)
                                .idTipoIncidencia(ent7 != null ? ent7.getId() : null)
                                .idCliente(ent8 != null ? ent8.getId() : null)
                                .resumen(dato.getResumen())
                                .fechaNotificacion(dato.getFechaNotificacion())
                                .fechaEnvio(dato.getFechaEnvio())
                                .idEstadoTicket(ent9 != null ? ent9.getId() : null)
                                .idCatpresol1(ent10 != null ? ent10.getId() : null)
                                .idCatpresol2(ent11 != null ? ent11.getId() : null)
                                .fechaUltimaResolucion(dato.getFechaUltimaResolucion())
                                .fechaCierre(dato.getFechaCierre())
                                .notas(dato.getNotas())
                                .resolucion(dato.getResolucion())
                                .pendiente(pend)
                                .e2e(e2e)
                                .sla(sla)
                                .idUrgenciaOb(ent5 != null ? ent5.getId() : null)
                                //REGLA DE NEGOCIO: Se debe evauluar si el SLA es menor o igual a 4:00:00
                                .incCumpleKpi(CheckIncidenciaCumpleKpi(ent7, sla, ID_INCIDENTE_CLIENTE, TIEMPO_4HRS))
                                //REGLA DE NEGOCIO: Se debe evauluar si el SLA es menor o igual a 48:00:00
                                .reqCumpleKpi(CheckIncidenciaCumpleKpi(ent7, sla, ID_REQUERIMIENTO_CLIENTE, TIEMPO_48HRS))
                                //REGLA DE NEGOCIO: Se debe evauluar si el SLA es menor o igual a 4:00:00 cuando la incidencia es Crítica
                                .cumpleCritica(CheckUrgenciaCumpleKpi(ent5, sla, ID_URGENCIA_CRITICA, TIEMPO_4HRS))
                                //REGLA DE NEGOCIO: Se debe evauluar si el SLA es menor o igual a 8:00:00 cuando la incidencia es Alta
                                .cumpleAlta(CheckUrgenciaCumpleKpi(ent5, sla, ID_URGENCIA_ALTA, TIEMPO_8HRS))
                                //REGLA DE NEGOCIO: Se debe evauluar si el SLA es menor o igual a 12:00:00 cuando la incidencia es Media
                                .cumpleMedia(CheckUrgenciaCumpleKpi(ent5, sla, ID_URGENCIA_MEDIA, TIEMPO_12HRS))
                                //REGLA DE NEGOCIO: Se debe evauluar si el SLA es menor o igual a 24:00:00 cuando la incidencia es Baja
                                .cumpleBaja(CheckUrgenciaCumpleKpi(ent5, sla, ID_URGENCIA_BAJA, TIEMPO_24HRS))

                                .menos1hSla(CheckTiempoCumpleKpi(sla, TIEMPO_1HRS, EnumOperators.SMALL_THAN_OR_EQUAL))
                                .mas1hMenos4hrSla(CheckTiempoCumpleKpi(sla, TIEMPO_1HRS, TIEMPO_4HRS ))
                                .mas4hMenos8hSla(CheckTiempoCumpleKpi(sla, TIEMPO_4HRS, TIEMPO_8HRS))
                                .mas8hMenos24hSla(CheckTiempoCumpleKpi(sla, TIEMPO_8HRS, TIEMPO_24HRS))
                                .mas24hSla(CheckTiempoCumpleKpi(sla, TIEMPO_24HRS, EnumOperators.GREATER_THAN ))

                                .menos1he2e(CheckTiempoCumpleKpi(e2e, TIEMPO_1HRS, EnumOperators.SMALL_THAN_OR_EQUAL))
                                .mas1hMenos4hre2e(CheckTiempoCumpleKpi(e2e, TIEMPO_1HRS, TIEMPO_4HRS ))
                                .mas4hMenos8he2e(CheckTiempoCumpleKpi(e2e, TIEMPO_4HRS, TIEMPO_8HRS))
                                .mas8hMenos24he2e(CheckTiempoCumpleKpi(e2e, TIEMPO_8HRS, TIEMPO_24HRS))
                                .mas24he2e(CheckTiempoCumpleKpi(e2e, TIEMPO_24HRS, EnumOperators.GREATER_THAN ))

                                .build());
                    }
                    break;
            }

        }

    }

    enum EnumOperators{
        SMALL_THAN_OR_EQUAL,
        GREATER_THAN_OR_EQUAL,
        EQUAL,
        SMALL_THAN,
        GREATER_THAN,
        NOT_EQUAL
    }
    private static Duration getDurationFromHours(int hours) {
        // Calcular el total de segundos para la cantidad de horas especificadas
        long totalSeconds = hours * 3600;
        // Devolver la duración total de segundos
        return Duration.ofSeconds(totalSeconds);
    }

    private Boolean evaluateTimes(String time1, int time2,EnumOperators operator) {
        Duration duration1 = parseTiempo(time1);
        Duration duration2 = getDurationFromHours(time2);// parseTiempo(time2);

        if(duration1 == null || duration2 == null)
            return null;

        long seconds1 = duration1.toSeconds();
        long seconds2 = duration2.toSeconds();

        switch (operator) {
            case SMALL_THAN_OR_EQUAL:
                return seconds1 <= seconds2;
            case GREATER_THAN_OR_EQUAL:
                return seconds1 >= seconds2;
            case SMALL_THAN:
                return seconds1 < seconds2;
            case GREATER_THAN:
                return seconds1 > seconds2;
            case EQUAL:
                return seconds1 == seconds2;
            default:
                return false;
        }
    }

    private Integer CheckIncidenciaCumpleKpi(ObjectValueDto tipoIncidencias, String tiempoSla, Integer idIncidencia, int tiempoRegla){
        if(tipoIncidencias == null) return null;
        if(!tipoIncidencias.getId().equals(idIncidencia)) return null;

        Boolean resultChekTime = evaluateTimes(tiempoSla,tiempoRegla, EnumOperators.SMALL_THAN_OR_EQUAL);
        if(tipoIncidencias.getId().equals(idIncidencia) && (resultChekTime != null && resultChekTime )){
            return 1;
        }
        return null;
    }

    private Integer CheckUrgenciaCumpleKpi(ObjectValueDto urgenciasTech, String tiempoSla, Integer idUrgencia, int tiempoRegla){
        if(urgenciasTech == null) return null;
        if(!urgenciasTech.getId().equals(idUrgencia)) return null;

        Boolean resultChekTime = evaluateTimes(tiempoSla,tiempoRegla, EnumOperators.SMALL_THAN_OR_EQUAL);
        if(urgenciasTech.getId().equals(idUrgencia) && (resultChekTime != null && resultChekTime )){
            return 1;
        }
        return null;
    }

    private Integer CheckTiempoCumpleKpi(String tiempo1, int limite1, int  limite2){

            Boolean result1 = evaluateTimes(tiempo1, limite1, EnumOperators.GREATER_THAN);
            Boolean result2 = evaluateTimes(tiempo1, limite2, EnumOperators.SMALL_THAN_OR_EQUAL);

            if(result1 == null || result2 == null) return null;
            return (result1 && result2 ? 1 : 0);

    }
    private Integer CheckTiempoCumpleKpi(String tiempo1, int limite1, EnumOperators operators){

            Boolean result = evaluateTimes(tiempo1, limite1, operators);
            if (result == null) return  null;
            return (result ? 1 : 0);
    }

    private Duration parseTiempo(String tiempoStr) {

        // Dividir el tiempoStr en partes usando el delimitador ":"
        String[] partes = tiempoStr.split(":");
        try {
            // Extraer las horas, minutos y segundos de las partes
            int horas = Integer.parseInt(partes[0]);
            int minutos = Integer.parseInt(partes[1]);
            int segundos = Integer.parseInt(partes[2]);

            // Calcular el total de segundos en el tiempo
            long totalSegundos = horas * 3600 + minutos * 60 + segundos;

            // Devolver la duración total de segundos
            return Duration.ofSeconds(totalSegundos);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            // Devolver null si el formato del tiempo es incorrecto
            return null;
        }
    }

    // Formato de salida para la hora
    private static final DateTimeFormatter HORA_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    // Método para validar y formatear el formato de hora
    public static String formatearHora(String horaStr) {

        // Dividir la cadena en partes usando el delimitador ":"
        String[] partes = horaStr.split(":");

        // Asegurarse de que haya al menos una parte y que cada parte sea un número
        if (partes.length >= 1 && partes[0].matches("\\d+")) {
            // Convertir las partes a números enteros
            int horas = Integer.parseInt(partes[0]);
            int minutos = (partes.length >= 2 && partes[1].matches("\\d+")) ? Integer.parseInt(partes[1]) : 0;
            int segundos = (partes.length == 3 && partes[2].matches("\\d+")) ? Integer.parseInt(partes[2]) : 0;

            // Formatear los números como cadenas y volver a concatenarlas con el delimitador ":"
            return String.format("%02d:%02d:%02d", horas, minutos, segundos);
        } else {
            // Si no tiene el formato correcto, retornar null
            return null;
        }
    }
}
