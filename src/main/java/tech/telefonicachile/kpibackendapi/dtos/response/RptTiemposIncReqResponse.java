package tech.telefonicachile.kpibackendapi.dtos.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
@Builder
public class RptTiemposIncReqResponse {
    Integer idGrupoAsignacion;
    String grupoAsignacion;
    java.sql.Time promedioIncidentes;
    java.sql.Time promedioRequerimientos;
    java.sql.Time promedioTotal;
    BigDecimal minInc;
    BigDecimal minReq;

    public RptTiemposIncReqResponse(Integer idGrupoAsignacion, String grupoAsignacion, java.sql.Time promedioIncidentes, java.sql.Time promedioRequerimientos, java.sql.Time promedioTotal, BigDecimal minInc, BigDecimal minReq) {
        this.idGrupoAsignacion = idGrupoAsignacion;
        this.grupoAsignacion = grupoAsignacion;
        this.promedioIncidentes = promedioIncidentes;
        this.promedioRequerimientos = promedioRequerimientos;
        this.promedioTotal = promedioTotal;
        this.minInc = minInc;
        this.minReq = minReq;
    }
}
