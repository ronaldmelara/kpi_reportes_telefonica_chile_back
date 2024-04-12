package tech.telefonicachile.kpibackendapi.dtos.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
public class PeriodosReporteResponse {

    int idReporte;
    int mes;
    int anio;

    public PeriodosReporteResponse(int idReporte, int mes, int anio) {
        this.idReporte = idReporte;
        this.mes = mes;
        this.anio = anio;
    }
}
