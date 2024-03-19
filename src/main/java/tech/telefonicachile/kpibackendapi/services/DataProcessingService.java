package tech.telefonicachile.kpibackendapi.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.telefonicachile.kpibackendapi.entities.Incidente;
import tech.telefonicachile.kpibackendapi.entities.TemporalIncidente;
import tech.telefonicachile.kpibackendapi.entities.catalogs.*;
import tech.telefonicachile.kpibackendapi.helper.EnumDatasource;
import tech.telefonicachile.kpibackendapi.repository.IncidentesRepository;
import tech.telefonicachile.kpibackendapi.repository.TmpIncidentesRepository;
import tech.telefonicachile.kpibackendapi.repository.catalogs.*;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataProcessingService {
    @Autowired
    private TmpIncidentesRepository tmpIncidentesRepository;
    @Autowired
    private Catpresol1Repository catpresol1Repository;
    @Autowired
    private Catpresol2Repository catpresol2Repository;
    @Autowired
    private ClasificacionTipoServRepository clasificacionTipoServRepository;
    @Autowired
    private ClientesRepository clientesRepository;
    @Autowired
    private CodigoServiciosRepository codigoServiciosRepository;
    @Autowired
    private EstadoTicketRepository estadoTicketRepository;
    @Autowired
    private GrupoAsignadoRepository grupoAsignadoRepository;
    @Autowired
    private GrupoPropietarioRepository grupoPropietarioRepository;
    @Autowired
    private ImpactoRepository impactoRepository;
    @Autowired
    private NivelSoporteRepository nivelSoporteRepository;
    @Autowired
    private PrioridadRepository prioridadRepository;
    @Autowired
    private SegmentosRepository segmentosRepository;
    @Autowired
    private ServiciosISORepository serviciosISORepository;
    @Autowired
    private SubsegmentosRepository subsegmentosRepository;
    @Autowired
    private TipoIncidenciaRepository tipoIncidenciaRepository;
    @Autowired
    private TipoServicioRemedyRepository tipoServicioRemedyRepository;
    @Autowired
    private TipoServiciosRepository tipoServiciosRepository;
    @Autowired
    private UrgenciaOBRepository urgenciaOBRepository;
    @Autowired
    private UrgenciaTechRepository urgenciaTechRepository;

    @Autowired
    private IncidentesRepository incidentesRepository;



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
        //LoadImportServices importServices = new LoadImportServices();
        List<TemporalIncidente> dataRaw = importServices.getDataImported();

        List<Incidente> dataToInsert = new ArrayList<>();
        List<Incidente> dataToUpdate = new ArrayList<>();
        for(TemporalIncidente dato: dataRaw){

            boolean ticketExists = incidentesRepository.findByTicket(dato.getTicket()).isEmpty();

            ServiciosISO ent1 = serviciosISORepository.findByServicio(StringUtils.trimToEmpty(dato.getServicio()));
            Impactos ent4 = impactoRepository.findByImpacto(StringUtils.trimToEmpty(dato.getImpacto()));
            UrgenciasTech ent5 = urgenciaTechRepository.findByUrgencia(StringUtils.trimToEmpty(dato.getUrgencia()));
            Prioridad ent6 = prioridadRepository.findByPrioridad(StringUtils.trimToEmpty(dato.getPrioridad()));
            EstadoTicket ent9 = estadoTicketRepository.findByEstado(StringUtils.trimToEmpty(dato.getEstado()));
            Clientes ent8 = clientesRepository.findByCliente(StringUtils.trimToEmpty(dato.getCliente()));
            GrupoAsignado ent2 = grupoAsignadoRepository.findByGrupo(StringUtils.trimToEmpty(dato.getGrupoAsignado()));

            //Clasificación TIpo de Incidencia
            //Agregar regla de negocio:
            /*
                         Restauración de servicio a usuario     = Incidencia de cliente     ----->  Incidencia de cliente
                         Petición de serv. por el usuario       = Requerimiento de cliente  ----->  Requerimiento de cliente
                         Evento de infraestructura              = Requerimiento de red      ----->  Requerimiento de cliente
                         Restauración de infraestructura        = Incidencia de Red         ----->  Incidencia de cliente
            */
            String catIncidencia = "";
            switch (dato.getTipoIncidencia())
            {
                case "Restauración de servicio a usuario":
                case "Restauración de infraestructura":
                case "Incidente de Red":
                    catIncidencia = "Incidencia de cliente";
                    break;
                case "Petición de serv. por el usuario":
                case "Evento de infraestructura":
                case "Requerimiento de Red":
                    catIncidencia = "Requerimiento de cliente";
                    break;
                default:
                    catIncidencia = dato.getTipoIncidencia();
            }
            TipoIncidencias ent7 = tipoIncidenciaRepository.findByTipo(StringUtils.trimToEmpty(catIncidencia).toLowerCase());


            switch (source){
                case EnumDatasource.REMEDY_INC_ING_SISTEMAS:

                    if (ticketExists)
                    {

                        incidentesRepository.updateTicketFromRemedy(Incidente.builder()
                                .idReporte(idReporte)
                                .ticket(dato.getTicket())
                                .idEstadoTicket(ent9 != null ? ent9.getIdEstado() : null)
                                .fechaUltimaResolucion(dato.getFechaUltimaResolucion())
                                .fechaCierre(dato.getFechaCierre())
                                .idGrupoAsignado(ent2 != null ? ent2.getIdGrupo() : null)
                                .responsable(dato.getResponsable())
                                .idServicio(ent1 != null ? ent1.getIdServicio() : null)
                                .idTipoIncidencia(ent7 != null ? ent7.getIdTipoIncidencia() : null)
                                .build());

                    }
                    else {

                        incidentesRepository.save(Incidente.builder()
                                .idReporte(idReporte)
                                .ticket(dato.getTicket())
                                .idImpacto(ent4 != null ? ent4.getIdImpacto() : null)
                                .idUrgencia(ent5 != null ? ent5.getIdUrgencia() : null)
                                .idPrioridad(ent6 != null ? ent6.getIdPrioridad() : null)
                                .idEstadoTicket(ent9 != null ? ent9.getIdEstado() : null)
                                .idCliente(ent8 != null ? ent8.getIdCliente() : null)
                                .fechaNotificacion(dato.getFechaNotificacion())
                                .fechaEnvio(dato.getFechaEnvio())
                                .fechaUltimaResolucion(dato.getFechaUltimaResolucion())
                                .fechaCierre(dato.getFechaCierre())
                                .idGrupoAsignado(ent2 != null ? ent2.getIdGrupo() : null)
                                .responsable(dato.getResponsable())
                                .idServicio(ent1 != null ? ent1.getIdServicio() : null)
                                .remitente(dato.getRemitente())
                                .idTipoIncidencia(ent7 != null ? ent7.getIdTipoIncidencia() : null)
                                .estadoSlm(dato.getEstadoSlm())
                                .build());
                    }
                    break;
                case EnumDatasource.EXTERNO_INC_IYR_INGSYS_MENSUAL:
                case EnumDatasource.EXTERNO_INC_IYR_INGSYS_SEMANAL:


                    GrupoPropietario ent3 = grupoPropietarioRepository.findByGrupo(StringUtils.trimToEmpty(dato.getGrupoPropietario()));
                    Catpresol1 ent10 = catpresol1Repository.findByCatpresol1(StringUtils.trimToEmpty(dato.getCatpresol1()));
                    Catpresol2 ent11 = catpresol2Repository.findByCatpresol2(StringUtils.trimToEmpty(dato.getCatpresol2()));

                    String sla = formatearHora(dato.getSla());
                    String e2e = formatearHora(dato.getE2e());
                    String pend = formatearHora(dato.getPendiente());

                    if (ticketExists)
                    {
                        incidentesRepository.updateTicketFromExternal(Incidente.builder()
                                .idGrupoAsignado(ent2 != null ? ent2.getIdGrupo() : null)
                                .idGrupoPropietario(ent3 != null ? ent3.getIdGrupo() : null)
                                .responsable(dato.getResponsable())
                                .remitente(dato.getRemitente())
                                .idImpacto(ent4 != null ? ent4.getIdImpacto() : null)
                                .idUrgencia(ent5 != null ? ent5.getIdUrgencia() : null)
                                .idPrioridad(ent6 != null ? ent6.getIdPrioridad() : null)
                                .idTipoIncidencia(ent7 != null ? ent7.getIdTipoIncidencia() : null)
                                .idEstadoTicket(ent9 != null ? ent9.getIdEstado() : null)
                                .idCatpresol1(ent10 != null ? ent10.getIdCatpresol1() : null)
                                .idCatpresol2(ent11 != null ? ent11.getIdCatpresol2() : null)
                                .fechaUltimaResolucion(dato.getFechaUltimaResolucion())
                                .fechaCierre(dato.getFechaCierre())
                                .notas(dato.getNotas())
                                .resolucion(dato.getResolucion())
                                .pendiente(pend)
                                .e2e(e2e)
                                .sla(sla)
                                .idUrgenciaOb(ent5 != null ? ent5.getIdUrgencia() : null)
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
                                .idServicio(ent1 != null ? ent1.getIdServicio() : null)
                                .idGrupoAsignado(ent2 != null ? ent2.getIdGrupo() : null)
                                .idGrupoPropietario(ent3 != null ? ent3.getIdGrupo() : null)
                                .responsable(dato.getResponsable())
                                .remitente(dato.getRemitente())
                                .idImpacto(ent4 != null ? ent4.getIdImpacto() : null)
                                .idUrgencia(ent5 != null ? ent5.getIdUrgencia() : null)
                                .idPrioridad(ent6 != null ? ent6.getIdPrioridad() : null)
                                .idTipoIncidencia(ent7 != null ? ent7.getIdTipoIncidencia() : null)
                                .idCliente(ent8 != null ? ent8.getIdCliente() : null)
                                .resumen(dato.getResumen())
                                .fechaNotificacion(dato.getFechaNotificacion())
                                .fechaEnvio(dato.getFechaEnvio())
                                .idEstadoTicket(ent9 != null ? ent9.getIdEstado() : null)
                                .idCatpresol1(ent10 != null ? ent10.getIdCatpresol1() : null)
                                .idCatpresol2(ent11 != null ? ent11.getIdCatpresol2() : null)
                                .fechaUltimaResolucion(dato.getFechaUltimaResolucion())
                                .fechaCierre(dato.getFechaCierre())
                                .notas(dato.getNotas())
                                .resolucion(dato.getResolucion())
                                .pendiente(pend)
                                .e2e(e2e)
                                .sla(sla)
                                .idUrgenciaOb(ent5 != null ? ent5.getIdUrgencia() : null)
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





//            dataFinal.add(
//                    Incidente.builder()
//                    .idReporte(idReporte)
//                    .ticket(dato.getTicket())
//                    .idServicio(ent1 != null ? ent1.getIdServicio() : null)
//                    .idGrupoAsignado(ent2 != null ? ent2.getIdGrupo() : null)
//                    .idGrupoPropietario(ent3 != null ? ent3.getIdGrupo() : null)
//                    .responsable(dato.getResponsable())
//                    .remitente(dato.getRemitente())
//                    .idImpacto(ent4 != null ? ent4.getIdImpacto() : null)
//                    .idUrgencia(ent5 != null ? ent5.getIdUrgencia() : null)
//                    .idPrioridad(ent6 != null ? ent6.getIdPrioridad() : null)
//                    .idTipoIncidencia(ent7 != null ? ent7.getIdTipoIncidencia() : null)
//                    .idCliente(ent8 != null ? ent8.getIdCliente() : null)
//                    .resumen(dato.getResumen())
//                    .fechaNotificacion(dato.getFechaNotificacion())
//                    .fechaEnvio(dato.getFechaEnvio())
//                    .idEstadoTicket(ent9 != null ? ent9.getIdEstado() : null)
//                    .idCatpresol1(ent10 != null ? ent10.getIdCatpresol1() : null)
//                    .idCatpresol2(ent11 != null ? ent11.getIdCatpresol2() : null)
//                    .fechaUltimaResolucion(dato.getFechaUltimaResolucion())
//                    .fechaCierre(dato.getFechaCierre())
//                    .notas(dato.getNotas())
//                    .resolucion(dato.getResolucion())
//                    .pendiente(pend)
//                    .e2e(e2e)
//                    .sla(sla)
//                    .idUrgenciaOb(ent5 != null ? ent5.getIdUrgencia() : null)
//                    //REGLA DE NEGOCIO: Se debe evauluar si el SLA es menor o igual a 4:00:00
//                    .incCumpleKpi(CheckIncidenciaCumpleKpi(ent7, sla, ID_INCIDENTE_CLIENTE, TIEMPO_4HRS))
//                    //REGLA DE NEGOCIO: Se debe evauluar si el SLA es menor o igual a 48:00:00
//                    .reqCumpleKpi(CheckIncidenciaCumpleKpi(ent7, sla, ID_REQUERIMIENTO_CLIENTE, TIEMPO_48HRS))
//                    //REGLA DE NEGOCIO: Se debe evauluar si el SLA es menor o igual a 4:00:00 cuando la incidencia es Crítica
//                    .cumpleCritica(CheckUrgenciaCumpleKpi(ent5, sla, ID_URGENCIA_CRITICA, TIEMPO_4HRS))
//                    //REGLA DE NEGOCIO: Se debe evauluar si el SLA es menor o igual a 8:00:00 cuando la incidencia es Alta
//                    .cumpleAlta(CheckUrgenciaCumpleKpi(ent5, sla, ID_URGENCIA_ALTA, TIEMPO_8HRS))
//                    //REGLA DE NEGOCIO: Se debe evauluar si el SLA es menor o igual a 12:00:00 cuando la incidencia es Media
//                    .cumpleMedia(CheckUrgenciaCumpleKpi(ent5, sla, ID_URGENCIA_MEDIA, TIEMPO_12HRS))
//                    //REGLA DE NEGOCIO: Se debe evauluar si el SLA es menor o igual a 24:00:00 cuando la incidencia es Baja
//                    .cumpleBaja(CheckUrgenciaCumpleKpi(ent5, sla, ID_URGENCIA_BAJA, TIEMPO_24HRS))
//
//                    .menos1hSla(CheckTiempoCumpleKpi(sla, TIEMPO_1HRS, EnumOperators.SMALL_THAN_OR_EQUAL))
//                    .mas1hMenos4hrSla(CheckTiempoCumpleKpi(sla, TIEMPO_1HRS, TIEMPO_4HRS ))
//                    .mas4hMenos8hSla(CheckTiempoCumpleKpi(sla, TIEMPO_4HRS, TIEMPO_8HRS))
//                    .mas8hMenos24hSla(CheckTiempoCumpleKpi(sla, TIEMPO_8HRS, TIEMPO_24HRS))
//                    .mas24hSla(CheckTiempoCumpleKpi(sla, TIEMPO_24HRS, EnumOperators.GREATER_THAN ))
//
//                    .menos1he2e(CheckTiempoCumpleKpi(e2e, TIEMPO_1HRS, EnumOperators.SMALL_THAN_OR_EQUAL))
//                    .mas1hMenos4hre2e(CheckTiempoCumpleKpi(e2e, TIEMPO_1HRS, TIEMPO_4HRS ))
//                    .mas4hMenos8he2e(CheckTiempoCumpleKpi(e2e, TIEMPO_4HRS, TIEMPO_8HRS))
//                    .mas8hMenos24he2e(CheckTiempoCumpleKpi(e2e, TIEMPO_8HRS, TIEMPO_24HRS))
//                    .mas24he2e(CheckTiempoCumpleKpi(e2e, TIEMPO_24HRS, EnumOperators.GREATER_THAN ))
//
//                    .build()
//            );


        }


        //fuera del for



//        if(!dataFinal.isEmpty())
//        {
//            incidentesRepository.saveAll(dataFinal);
//        }


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

    private Integer CheckIncidenciaCumpleKpi(TipoIncidencias tipoIncidencias, String tiempoSla, Integer idIncidencia, int tiempoRegla){
        if(tipoIncidencias == null) return null;
        if(!tipoIncidencias.getIdTipoIncidencia().equals(idIncidencia)) return null;

        Boolean resultChekTime = evaluateTimes(tiempoSla,tiempoRegla, EnumOperators.SMALL_THAN_OR_EQUAL);
        if(tipoIncidencias.getIdTipoIncidencia().equals(idIncidencia) && (resultChekTime != null && resultChekTime )){
            return 1;
        }
        return null;
    }

    private Integer CheckUrgenciaCumpleKpi(UrgenciasTech urgenciasTech, String tiempoSla, Integer idUrgencia, int tiempoRegla){
        if(urgenciasTech == null) return null;
        if(!urgenciasTech.getIdUrgencia().equals(idUrgencia)) return null;

        Boolean resultChekTime = evaluateTimes(tiempoSla,tiempoRegla, EnumOperators.SMALL_THAN_OR_EQUAL);
        if(urgenciasTech.getIdUrgencia().equals(idUrgencia) && (resultChekTime != null && resultChekTime )){
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

//    private static LocalTime parseTiempo(String tiempoStr) {
//        // Definir los posibles patrones de formato
//        String[] patrones = {"H:m:s", "HH:mm:ss"};
//
//        // Intentar analizar el tiempo utilizando cada patrón
//        for (String patron : patrones) {
//            try {
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patron);
//                LocalTime tiempo = LocalTime.parse(tiempoStr, formatter);
//                return tiempo;
//            } catch (Exception e) {
//                // Continuar con el siguiente patrón si hay una excepción
//            }
//        }
//
//        // Devolver null si no se pudo analizar el tiempo
//        return null;
//    }

    private Duration parseTiempo(String tiempoStr) {

//
//        String[] patrones = {"H:m:s", "HH:mm:ss"};
//
//        // Intentar analizar el tiempo utilizando cada patrón
//        for (String patron : patrones) {
//            try {
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patron);
//                LocalTime tiempo = LocalTime.parse(tiempoStr, formatter);
//                return Duration.between(LocalTime.MIN, tiempo); // Convertir a duración
//            } catch (Exception e) {
//                // Continuar con el siguiente patrón si hay una excepción
//            }
//        }
//
//        // Devolver null si no se pudo analizar el tiempo
//        return null;

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
//        // Dividir la hora en sus partes (horas, minutos, segundos)
//        String[] partes = horaStr.split(":");
//        // Asegurar que siempre haya tres partes
//        if (partes.length == 1) {
//            // Si hay solo una parte, considerarla como minutos
//            return HORA_FORMATTER.format(LocalTime.of(0, Integer.parseInt(partes[0]), 0));
//        } else if (partes.length == 2) {
//            // Si hay dos partes, considerar la primera como horas y la segunda como minutos
//            return HORA_FORMATTER.format(LocalTime.of(0, Integer.parseInt(partes[0]), Integer.parseInt(partes[1])));
//        } else if (partes.length == 3) {
//            // Si hay tres partes, considerarlas como horas, minutos y segundos
//            return HORA_FORMATTER.format(LocalTime.of(Integer.parseInt(partes[0]), Integer.parseInt(partes[1]), Integer.parseInt(partes[2])));
//        } else {
//            // Si no coincide con ninguno de los formatos esperados, retornar null
//            return null;
//        }
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
