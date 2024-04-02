package tech.telefonicachile.kpibackendapi.dtos.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
@Builder
public class RptTiemposUrgenciaIncReqResponse {
    Integer idUrgencia;
    String urgencia;
    java.sql.Time promedioIncidentes;
    java.sql.Time promedioRequerimientos;
    java.sql.Time promedioTotal;
    BigDecimal minInc;
    BigDecimal minReq;

    public RptTiemposUrgenciaIncReqResponse(Integer idUrgencia, String urgencia, java.sql.Time promedioIncidentes, java.sql.Time promedioRequerimientos, java.sql.Time promedioTotal, BigDecimal minInc, BigDecimal minReq) {
        this.idUrgencia = idUrgencia;
        this.urgencia = urgencia;
        this.promedioIncidentes = promedioIncidentes;
        this.promedioRequerimientos = promedioRequerimientos;
        this.promedioTotal = promedioTotal;
        this.minInc = minInc;
        this.minReq = minReq;
    }
}
