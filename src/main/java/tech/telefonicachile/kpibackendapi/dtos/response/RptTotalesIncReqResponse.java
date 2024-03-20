package tech.telefonicachile.kpibackendapi.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
public class RptTotalesIncReqResponse {

    Integer idGrupoAsignacion;
    String grupoAsignacion;
    Long totalIncidentes;
    Long totalRequerimientos;
    Long totalGeneralPorGrupo;

    public RptTotalesIncReqResponse(Integer idGrupoAsignacion, String grupoAsignacion, Long totalIncidentes, Long totalRequerimientos, Long totalGeneralPorGrupo) {
        this.idGrupoAsignacion = idGrupoAsignacion;
        this.grupoAsignacion = grupoAsignacion;
        this.totalIncidentes = totalIncidentes;
        this.totalRequerimientos = totalRequerimientos;
        this.totalGeneralPorGrupo = totalGeneralPorGrupo;
    }
}
