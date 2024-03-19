package tech.telefonicachile.kpibackendapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tech.telefonicachile.kpibackendapi.entities.ImportLog;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImportRepository extends JpaRepository<ImportLog,Long> {


    @Query(value = "SELECT A.id_carga, A.nombre_archivo, C.estado, E.datasource, G.nombre_usuario, " +
            "A.fecha_carga, A.fecha_importacion, A.resumen_importacion, A.id_estado_importacion " +
            "FROM kpi_tech.log_importacion A  " +
            "INNER JOIN kpi_tech.catalogo_estado_importacion C ON A.id_estado_importacion = C.id_estado_importacion " +
            "INNER JOIN kpi_tech.catalogo_datasource E ON E.id_datasource = A.id_datasource " +
            "INNER JOIN kpi_tech.usuario G ON G.id = A.usuario_carga " +
            "ORDER BY A.id_carga DESC", nativeQuery = true)
    List<Object[]> getListImports();


//    @Transactional
//    @Modifying
//    @Query(value="UPDATE smart10.cargaimportacion SET id_estado_importacion = :id_estado_importacion, fecha_importacion="
//            +"CASE WHEN fecha_importacion IS NULL THEN CURRENT_TIMESTAMP ELSE fecha_importacion END, "
//            +"resumen_importacion = :resumen WHERE id_carga = :idCarga", nativeQuery = true)
//    int updateImportStatus(@Param("id_estado_importacion") Integer id_estado_importacion,
//                            @Param("idCarga") Integer idCarga,
//                            @Param("resumen") String resumen);


    @Query(value = "SELECT id_reporte, cerrado FROM kpi_tech.reportes WHERE id_tipo_reporte=:idTipoReporte AND mes=:mes AND anio=:anio ORDER BY id_reporte LIMIT 1", nativeQuery = true)
    List<Object[]> getExistReport(@Param("idTipoReporte") int idTipoReporte, @Param("mes") int mes, @Param("anio") int anio);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO kpi_tech.reportes (id_tipo_reporte, mes, anio, cerrado) VALUES (:idTipoReporte, :mes, :anio, 0)", nativeQuery = true)
    int insertNewReport(@Param("idTipoReporte") int idTipoReporte, @Param("mes") int mes, @Param("anio") int anio);


}
