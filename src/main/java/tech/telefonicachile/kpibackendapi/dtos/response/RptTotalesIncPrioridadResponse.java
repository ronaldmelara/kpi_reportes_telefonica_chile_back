package tech.telefonicachile.kpibackendapi.dtos.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
public class RptTotalesIncPrioridadResponse {

    Integer idGrupoAsignacion;
    String grupoAsignacion;

    Integer idPrioridad;
    String prioridad;
    Long totalIncidentes;

    public RptTotalesIncPrioridadResponse(Integer idGrupoAsignacion, String grupoAsignacion, Integer idPrioridad, String prioridad, Long totalIncidentes) {
        this.idGrupoAsignacion = idGrupoAsignacion;
        this.grupoAsignacion = grupoAsignacion;
        this.idPrioridad = idPrioridad;
        this.prioridad = prioridad;
        this.totalIncidentes = totalIncidentes;
    }
}
