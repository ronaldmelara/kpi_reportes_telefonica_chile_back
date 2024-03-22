/*INCIDENTES Y REQUERIMIENTOS*/
SELECT 
DISTINCT B.id_grupo_asignacion, B.grupo_asignacion, 
(SELECT COUNT(1) FROM kpi_tech.incidencias_requerimientos A1 
WHERE A1.id_tipo_incidencia = 2 and A1.id_grupo_asignacion  = B.id_grupo_asignacion AND
A1.id_reporte = A.id_reporte) TotalIncidentes,
(SELECT COUNT(1) FROM kpi_tech.incidencias_requerimientos A1 
WHERE A1.id_tipo_incidencia = 1 and A1.id_grupo_asignacion  = B.id_grupo_asignacion AND
A1.id_reporte = A.id_reporte) TotalRequerimientos,
(SELECT COUNT(1) FROM kpi_tech.incidencias_requerimientos A1
WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia IN (1,2) AND
A1.id_reporte = A.id_reporte) TotalGeneral
FROM kpi_tech.incidencias_requerimientos A
INNER JOIN kpi_tech.catalogo_grupo_asignado B ON A.id_grupo_asignacion = B.id_grupo_asignacion
INNER JOIN kpi_tech.catalogo_tipo_incidencia C ON A.id_tipo_incidencia = C.id_tipo_incidencia
INNER JOIN kpi_tech.reportes D ON A.id_reporte = D.id_reporte
WHERE C.id_tipo_incidencia IN (1,2) AND D.id_tipo_reporte = 1 AND D.Mes = 2 AND D.anio = 2024
AND B.id_grupo_asignacion NOT IN(15,2,1,17,21,16,20,9,7)
group by B.id_grupo_asignacion, B.grupo_asignacion, A.id_reporte
ORDER BY B.grupo_asignacion ASC;

/*TD REQUERIMIENTOS POR GRUPO*/
SELECT 
DISTINCT B.id_grupo_asignacion, B.grupo_asignacion, 
SUM(1) AS totalCumplidos 
FROM kpi_tech.incidencias_requerimientos A
INNER JOIN kpi_tech.catalogo_grupo_asignado B ON A.id_grupo_asignacion = B.id_grupo_asignacion
INNER JOIN kpi_tech.catalogo_tipo_incidencia C ON A.id_tipo_incidencia = C.id_tipo_incidencia
INNER JOIN kpi_tech.reportes D ON A.id_reporte = D.id_reporte
WHERE A.id_estado_ticket = 1 AND A.req_cumple_kpi = 1  AND D.id_tipo_reporte = 1 AND D.Mes = 2 AND D.anio = 2024
AND B.id_grupo_asignacion NOT IN(15,2,1,17,21,16,20,9,7)
group by B.id_grupo_asignacion, B.grupo_asignacion
ORDER BY B.grupo_asignacion ASC;

/*TD INCIDENCIAS POR GRUPO*/
SELECT 
DISTINCT B.id_grupo_asignacion, B.grupo_asignacion, 
SUM(1) AS totalCumplidos 
FROM kpi_tech.incidencias_requerimientos A
INNER JOIN kpi_tech.catalogo_grupo_asignado B ON A.id_grupo_asignacion = B.id_grupo_asignacion
INNER JOIN kpi_tech.catalogo_tipo_incidencia C ON A.id_tipo_incidencia = C.id_tipo_incidencia
INNER JOIN kpi_tech.reportes D ON A.id_reporte = D.id_reporte
WHERE A.id_estado_ticket = 1 AND C.id_tipo_incidencia = 2 AND A.inc_cumple_kpi = 1  AND D.id_tipo_reporte = 1 AND D.Mes = 2 AND D.anio = 2024
AND B.id_grupo_asignacion NOT IN(15,2,1,17,21,16,20,9,7)
group by B.id_grupo_asignacion, B.grupo_asignacion
ORDER BY B.grupo_asignacion ASC;

/*IEMPO DE RESTAURACIÓN DE INCIDENTES Y REQUERIMIENTOS*/
SELECT 
DISTINCT B.id_grupo_asignacion, B.grupo_asignacion,
(SELECT 
CAST(sec_to_time(AVG(TIME_TO_SEC(A1.sla))) AS char)
FROM kpi_tech.incidencias_requerimientos A1  
WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia = 2 AND A1.id_reporte = A.id_reporte
group by A1.id_grupo_asignacion) PromedioIncidente,
(SELECT 
CAST(sec_to_time(AVG(TIME_TO_SEC(A1.sla))) AS char)
FROM kpi_tech.incidencias_requerimientos A1  
WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia = 1 AND A1.id_reporte = A.id_reporte
group by A1.id_grupo_asignacion) PromedioRequerimiento,
(SELECT 
CAST(sec_to_time(AVG(TIME_TO_SEC(A1.sla))) as char)
FROM kpi_tech.incidencias_requerimientos A1  
WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia IN (1,2) AND A1.id_reporte = A.id_reporte
group by A1.id_grupo_asignacion) PromedioTotal,
(SELECT 
 CAST((AVG(TIME_TO_SEC(A1.sla)) / 86400) * 1440 as float)
FROM kpi_tech.incidencias_requerimientos A1  
WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia = 2 AND A1.id_reporte = A.id_reporte
group by A1.id_grupo_asignacion) MinInc,
(SELECT 
 cast((AVG(TIME_TO_SEC(A1.sla)) / 86400) * 1440 as float)
FROM kpi_tech.incidencias_requerimientos A1  
WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia = 1 AND A1.id_reporte = A.id_reporte
group by A1.id_grupo_asignacion) MinReq
FROM kpi_tech.incidencias_requerimientos A
INNER JOIN kpi_tech.catalogo_grupo_asignado B ON A.id_grupo_asignacion = B.id_grupo_asignacion
INNER JOIN kpi_tech.catalogo_tipo_incidencia C ON A.id_tipo_incidencia = C.id_tipo_incidencia
INNER JOIN kpi_tech.reportes D ON A.id_reporte = D.id_reporte
WHERE C.id_tipo_incidencia IN (1,2)
AND B.id_grupo_asignacion NOT IN(8,15,2,1,17,21,16,20,9,7, 12, 24, 4,5, 25) AND D.id_tipo_reporte = 1 AND D.Mes = 2 AND D.anio = 2024
group by B.id_grupo_asignacion, B.grupo_asignacion,C.id_tipo_incidencia, A.id_reporte
ORDER BY B.id_grupo_asignacion ASC;

/*IEMPO DE RESTAURACIÓN DE INCIDENTES Y REQUERIMIENTOS  MANAGED*/
SELECT 
DISTINCT B.id_grupo_asignacion AS idGrupoAsignacion, B.grupo_asignacion AS grupoAsignacion,
(SELECT 
CAST(sec_to_time(AVG(TIME_TO_SEC(A1.sla))) AS char)
FROM kpi_tech.incidencias_requerimientos A1  
WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia = 2 AND A1.id_reporte = A.id_reporte
group by A1.id_grupo_asignacion) AS promedioIncidentes,
(SELECT 
CAST(sec_to_time(AVG(TIME_TO_SEC(A1.sla))) AS char)
FROM kpi_tech.incidencias_requerimientos A1  
WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia = 1 AND A1.id_reporte = A.id_reporte
group by A1.id_grupo_asignacion) AS promedioRequerimientos,
(SELECT 
CAST(sec_to_time(AVG(TIME_TO_SEC(A1.sla))) as char)
FROM kpi_tech.incidencias_requerimientos A1  
WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia IN (1,2) AND A1.id_reporte = A.id_reporte
group by A1.id_grupo_asignacion) AS promedioTotal,
(SELECT 
 CAST((AVG(TIME_TO_SEC(A1.sla)) / 86400) * 1440 as float)
FROM kpi_tech.incidencias_requerimientos A1  
WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia = 2 AND A1.id_reporte = A.id_reporte
group by A1.id_grupo_asignacion) AS minInc,
(SELECT 
 cast((AVG(TIME_TO_SEC(A1.sla)) / 86400) * 1440 as float)
FROM kpi_tech.incidencias_requerimientos A1  
WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia = 1 AND A1.id_reporte = A.id_reporte
group by A1.id_grupo_asignacion) as minReq
FROM kpi_tech.incidencias_requerimientos A
INNER JOIN kpi_tech.catalogo_grupo_asignado B ON A.id_grupo_asignacion = B.id_grupo_asignacion
INNER JOIN kpi_tech.catalogo_tipo_incidencia C ON A.id_tipo_incidencia = C.id_tipo_incidencia
INNER JOIN kpi_tech.reportes D ON A.id_reporte = D.id_reporte
WHERE C.id_tipo_incidencia IN (1,2)
AND B.id_grupo_asignacion IN(1,2, 15,16,17,21,7,9,20) AND D.id_tipo_reporte = 1 AND D.Mes = 2 AND D.anio = 2024
group by B.id_grupo_asignacion, B.grupo_asignacion,C.id_tipo_incidencia, A.id_reporte
ORDER BY B.id_grupo_asignacion ASC;

/*TD TOTAL POR PRIORIDAD*/
SELECT 
DISTINCT  B.id_grupo_asignacion, B.grupo_asignacion, D.id_prioridad, D.prioridad,
(SELECT COUNT(A1.id_urgencia) FROM smart10.carga_servicios_cloud A1 
INNER JOIN smart10.catalogo_grupo_asignado B1 ON A1.id_grupo_asignacion = B1.id_grupo_asignacion 
INNER JOIN smart10.catalogo_estado_ticket C1 ON A1.id_estado_ticket = C1.id_estado_ticket 
INNER JOIN smart10.catalogo_tipo_incidencia D1 ON A1.id_tipo_incidencia = D1.id_tipo_incidencia 
WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND D1.id_tipo_incidencia = 2 AND A1.id_prioridad = D.id_prioridad
) TotalPorPrioridad
FROM smart10.carga_servicios_cloud A
INNER JOIN smart10.catalogo_grupo_asignado B ON A.id_grupo_asignacion = B.id_grupo_asignacion
INNER JOIN smart10.catalogo_tipo_incidencia C ON A.id_tipo_incidencia = C.id_tipo_incidencia
INNER JOIN smart10.catalogo_prioridad D ON A.id_prioridad = D.id_prioridad
WHERE C.id_tipo_incidencia IN (2)
group by B.id_grupo_asignacion, B.grupo_asignacion, D.id_prioridad, D.prioridad
ORDER BY B.grupo_asignacion ASC;


/*URGENCIA INCIDENTES Y REQUERIMIENTOS CLOUD*/
SELECT 
DISTINCT D.id_urgencia, D.urgencia,
(SELECT 
sec_to_time(AVG(TIME_TO_SEC(A1.sla))) 
FROM smart10.carga_servicios_cloud A1  
WHERE A1.id_urgencia  = D.id_urgencia AND A1.id_tipo_incidencia = 2) PromedioIncidente,
(SELECT 
sec_to_time(AVG(TIME_TO_SEC(A1.sla))) 
FROM smart10.carga_servicios_cloud A1  
WHERE A1.id_urgencia  = D.id_urgencia AND A1.id_tipo_incidencia = 1) PromedioRequerimiento,
(SELECT 
sec_to_time(AVG(TIME_TO_SEC(A1.sla))) 
FROM smart10.carga_servicios_cloud A1  
WHERE A1.id_urgencia  = D.id_urgencia AND A1.id_tipo_incidencia IN (1,2)) Total,
(SELECT 
 (AVG(TIME_TO_SEC(A1.sla)) / 86400) * 1440
FROM smart10.carga_servicios_cloud A1  
WHERE A1.id_urgencia  = D.id_urgencia AND A1.id_tipo_incidencia = 2) MinInc,
(SELECT 
 (AVG(TIME_TO_SEC(A1.sla)) / 86400) * 1440
FROM smart10.carga_servicios_cloud A1  
WHERE A1.id_urgencia  = D.id_urgencia AND A1.id_tipo_incidencia = 1) MinReq
FROM smart10.carga_servicios_cloud A
INNER JOIN smart10.catalogo_grupo_asignado B ON A.id_grupo_asignacion = B.id_grupo_asignacion
INNER JOIN smart10.catalogo_urgencias_tech D ON A.id_urgencia = D.id_urgencia
group by D.id_urgencia
ORDER BY D.id_urgencia ASC;

/*URGENCIA INCIDENTES Y REQUERIMIENTOS SERVICE DELIVERY*/
SELECT 
DISTINCT  B.id_grupo_asignacion, B.grupo_asignacion, D.id_urgencia, D.urgencia,
(SELECT sec_to_time(AVG(TIME_TO_SEC(A1.sla)))  FROM smart10.carga_servicios_cloud A1 
INNER JOIN smart10.catalogo_grupo_asignado B1 ON A1.id_grupo_asignacion = B1.id_grupo_asignacion 
INNER JOIN smart10.catalogo_estado_ticket C1 ON A1.id_estado_ticket = C1.id_estado_ticket 
INNER JOIN smart10.catalogo_tipo_incidencia D ON A1.id_tipo_incidencia = D.id_tipo_incidencia 
WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND D.id_tipo_incidencia = 2 AND A1.id_urgencia = D.id_urgencia
) PromInci,
(SELECT sec_to_time(AVG(TIME_TO_SEC(A1.sla)))  FROM smart10.carga_servicios_cloud A1 
INNER JOIN smart10.catalogo_grupo_asignado B1 ON A1.id_grupo_asignacion = B1.id_grupo_asignacion 
INNER JOIN smart10.catalogo_estado_ticket C1 ON A1.id_estado_ticket = C1.id_estado_ticket 
INNER JOIN smart10.catalogo_tipo_incidencia D ON A1.id_tipo_incidencia = D.id_tipo_incidencia 
WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND D.id_tipo_incidencia = 1 AND A1.id_urgencia = D.id_urgencia
) PromReq,
(SELECT (AVG(TIME_TO_SEC(A1.sla))/ 86400) * 1440  FROM smart10.carga_servicios_cloud A1 
INNER JOIN smart10.catalogo_grupo_asignado B1 ON A1.id_grupo_asignacion = B1.id_grupo_asignacion 
INNER JOIN smart10.catalogo_estado_ticket C1 ON A1.id_estado_ticket = C1.id_estado_ticket 
INNER JOIN smart10.catalogo_tipo_incidencia D ON A1.id_tipo_incidencia = D.id_tipo_incidencia 
WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND D.id_tipo_incidencia = 2 AND A1.id_urgencia = D.id_urgencia
) MinInci,
(SELECT (AVG(TIME_TO_SEC(A1.sla))/ 86400) * 1440  FROM smart10.carga_servicios_cloud A1 
INNER JOIN smart10.catalogo_grupo_asignado B1 ON A1.id_grupo_asignacion = B1.id_grupo_asignacion 
INNER JOIN smart10.catalogo_estado_ticket C1 ON A1.id_estado_ticket = C1.id_estado_ticket 
INNER JOIN smart10.catalogo_tipo_incidencia D ON A1.id_tipo_incidencia = D.id_tipo_incidencia 
WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND D.id_tipo_incidencia = 1 AND A1.id_urgencia = D.id_urgencia
) MinReq
FROM smart10.carga_servicios_cloud A
INNER JOIN smart10.catalogo_grupo_asignado B ON A.id_grupo_asignacion = B.id_grupo_asignacion
INNER JOIN smart10.catalogo_tipo_incidencia C ON A.id_tipo_incidencia = C.id_tipo_incidencia
INNER JOIN smart10.catalogo_urgencias_tech D ON A.id_urgencia = D.id_urgencia
WHERE C.id_tipo_incidencia IN (1,2)
group by B.id_grupo_asignacion, B.grupo_asignacion, D.id_urgencia, D.urgencia
ORDER BY D.id_urgencia ASC;


/*Cuenta Urgencia*/
SELECT 
DISTINCT  B.id_grupo_asignacion, B.grupo_asignacion, D.id_urgencia, D.urgencia,
(SELECT COUNT(A1.id_urgencia) FROM smart10.carga_servicios_cloud A1 
INNER JOIN smart10.catalogo_grupo_asignado B1 ON A1.id_grupo_asignacion = B1.id_grupo_asignacion 
INNER JOIN smart10.catalogo_estado_ticket C1 ON A1.id_estado_ticket = C1.id_estado_ticket 
INNER JOIN smart10.catalogo_tipo_incidencia D ON A1.id_tipo_incidencia = D.id_tipo_incidencia 
WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND D.id_tipo_incidencia = 2 AND A1.id_urgencia = D.id_urgencia
) TotalInc
FROM smart10.carga_servicios_cloud A
INNER JOIN smart10.catalogo_grupo_asignado B ON A.id_grupo_asignacion = B.id_grupo_asignacion
INNER JOIN smart10.catalogo_tipo_incidencia C ON A.id_tipo_incidencia = C.id_tipo_incidencia
INNER JOIN smart10.catalogo_urgencias_tech D ON A.id_urgencia = D.id_urgencia
WHERE C.id_tipo_incidencia IN (1,2)
group by B.id_grupo_asignacion, B.grupo_asignacion, D.id_urgencia, D.urgencia
ORDER BY B.grupo_asignacion ASC;