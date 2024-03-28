package tech.telefonicachile.kpibackendapi.dtos.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
public class RptTiemposUrgenciaIncReqResponse {
    Integer idUrgencia;
    String urgencia;
    String promedioIncidentes;
    String promedioRequerimientos;
    String promedioTotal;
    Long minInc;
    Long minReq;

    public RptTiemposUrgenciaIncReqResponse(Integer idUrgencia, String urgencia, String promedioIncidentes, String promedioRequerimientos, String promedioTotal, Long minInc, Long minReq) {
        this.idUrgencia = idUrgencia;
        this.urgencia = urgencia;
        this.promedioIncidentes = promedioIncidentes;
        this.promedioRequerimientos = promedioRequerimientos;
        this.promedioTotal = promedioTotal;
        this.minInc = minInc;
        this.minReq = minReq;
    }
}
