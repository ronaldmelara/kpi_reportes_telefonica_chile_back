package tech.telefonicachile.kpibackendapi.dtos.internals;


import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelSheet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ExcelSheet("Hoja1")
public class IncFileExternalDTO implements IncFileBase {
    @ExcelCell(0)
    String ticket;
    @ExcelCell(1)
    String servicio;
    @ExcelCell(2)
    String grupoAsignado;
    @ExcelCell(3)
    String grupoPropietario;
    @ExcelCell(4)
    String responsable;
    @ExcelCell(5)
    String remitente;
    @ExcelCell(6)
    String impacto;
    @ExcelCell(7)
    String urgencia;
    @ExcelCell(8)
    String prioridad;
    @ExcelCell(9)
    String tipoIncidencia;
    @ExcelCell(10)
    String cliente;
    @ExcelCell(11)
    String resumen;
    @ExcelCell(12)
    String fechaNotificacion;
    @ExcelCell(13)
    String fechaEnvio;
    @ExcelCell(14)
    String estado;
    @ExcelCell(15)
    String catpresol1;
    @ExcelCell(16)
    String catpresol2;
    @ExcelCell(17)
    String fechaResolucion;
    @ExcelCell(18)
    String fechaCierre;
    @ExcelCell(19)
    String notas;
    @ExcelCell(20)
    String resolucion;
    @ExcelCell(21)
    String pendiente;
    @ExcelCell(22)
    String e2e;
    @ExcelCell(23)
    String sla;
}
