package tech.telefonicachile.kpibackendapi.dtos.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
@Builder
public class PrtTotalIncReqCumplidosResponse {
    Integer idGrupoAsignacion;
    String grupoAsignacion;
    BigDecimal totalCumplidos;

    public PrtTotalIncReqCumplidosResponse(Integer idGrupoAsignacion, String grupoAsignacion, BigDecimal totalCumplidos) {
        this.idGrupoAsignacion = idGrupoAsignacion;
        this.grupoAsignacion = grupoAsignacion;
        this.totalCumplidos = totalCumplidos;
    }

}
