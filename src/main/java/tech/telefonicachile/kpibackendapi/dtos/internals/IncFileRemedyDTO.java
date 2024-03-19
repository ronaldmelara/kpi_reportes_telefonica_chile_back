package tech.telefonicachile.kpibackendapi.dtos.internals;


import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelSheet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ExcelSheet("Report")
public class IncFileRemedyDTO implements IncFileBase {
    @ExcelCell(0)
    String ticket;
    @ExcelCell(1)
    String impacto;
    @ExcelCell(2)
    String urgencia;
    @ExcelCell(3)
    String prioridad;
    @ExcelCell(4)
    String estado;
    @ExcelCell(5)
    String cliente;
    @ExcelCell(6)
    String fechaNotificacion;
    @ExcelCell(7)
    String fechaEnvio;
    @ExcelCell(8)
    String fechaUltimaResolucion;
    @ExcelCell(9)
    String fechaCierre;
//    @ExcelCell(10)
//    String fechaAsignada;
    //Columna K en el excel viene vacía y por regla de negocio no se usa
    @ExcelCell(11)
    String grupoAsignado;
    @ExcelCell(12)
    String responsable; //tambien nombrado "Usuario asignado"
    @ExcelCell(13)
    String resumen;
    @ExcelCell(14)
    String servicio; //tambine nombrado como "Servicio ISO 20000"
    @ExcelCell(15)
    String remitente;
    @ExcelCell(16)
    String tipoIncidencia; //tambien nombrado "Tipo"
    @ExcelCell(17)
    String EstadoSlm;

}
