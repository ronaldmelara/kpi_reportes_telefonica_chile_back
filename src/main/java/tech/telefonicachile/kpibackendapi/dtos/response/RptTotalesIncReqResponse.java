package tech.telefonicachile.kpibackendapi.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RptTotalesIncReqResponse {

    Integer idGrupoAsignacion;
    String grupoAsignacion;
    Integer totalIncidentes;
    Integer totalRequerimientos;
    Integer totalGeneralPorGrupo;
}
