package tech.telefonicachile.kpibackendapi.dtos.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
public class RptTiemposIncReqResponse {
    Integer idGrupoAsignacion;
    String grupoAsignacion;
    String promedioIncidentes;
    String promedioRequerimientos;
    String promedioTotal;
    Long minInc;
    Long minReq;

    public RptTiemposIncReqResponse(Integer idGrupoAsignacion, String grupoAsignacion, String promedioIncidentes, String promedioRequerimientos, String promedioTotal, Long minInc, Long minReq) {
        this.idGrupoAsignacion = idGrupoAsignacion;
        this.grupoAsignacion = grupoAsignacion;
        this.promedioIncidentes = promedioIncidentes;
        this.promedioRequerimientos = promedioRequerimientos;
        this.promedioTotal = promedioTotal;
        this.minInc = minInc;
        this.minReq = minReq;
    }
}
