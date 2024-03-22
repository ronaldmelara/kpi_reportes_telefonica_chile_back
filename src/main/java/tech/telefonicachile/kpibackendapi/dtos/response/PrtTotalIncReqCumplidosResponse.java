package tech.telefonicachile.kpibackendapi.dtos.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
public class PrtTotalIncReqCumplidosResponse {
    Integer idGrupoAsignacion;
    String grupoAsignacion;
    Long totalCumplidos;

    public PrtTotalIncReqCumplidosResponse(Integer idGrupoAsignacion, String grupoAsignacion, Long totalCumplidos) {
        this.idGrupoAsignacion = idGrupoAsignacion;
        this.grupoAsignacion = grupoAsignacion;
        this.totalCumplidos = totalCumplidos;
    }

}
