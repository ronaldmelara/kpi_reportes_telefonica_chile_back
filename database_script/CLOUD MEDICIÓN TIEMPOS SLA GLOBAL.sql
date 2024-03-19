/*Cuenta de Tiempo  < 1 hora: */
SELECT 
DISTINCT B.id_grupo_asignacion, B.grupo_asignacion,
(SELECT 
COUNT(1) 
FROM smart10.carga_servicios_cloud A1  
WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia = 2
AND A1.id_estado_ticket = 1 AND A1.m1h_sla = 1
group by A1.id_grupo_asignacion) TotalInc,
(SELECT 
COUNT(1) 
FROM smart10.carga_servicios_cloud A1  
WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia = 1
AND A1.id_estado_ticket = 1 AND A1.m1h_sla = 1
group by A1.id_grupo_asignacion) TotalReq
FROM smart10.carga_servicios_cloud A
INNER JOIN smart10.catalogo_grupo_asignado B ON A.id_grupo_asignacion = B.id_grupo_asignacion
INNER JOIN smart10.catalogo_tipo_incidencia C ON A.id_tipo_incidencia = C.id_tipo_incidencia
WHERE C.id_tipo_incidencia IN (1,2) AND A.m1h_sla = 1
group by B.id_grupo_asignacion, B.grupo_asignacion,C.id_tipo_incidencia
ORDER BY B.id_grupo_asignacion ASC;

/*Cuenta de Tiempo  > 1 hora y < 4 horas:  */
SELECT 
DISTINCT B.id_grupo_asignacion, B.grupo_asignacion,
(SELECT 
COUNT(1) 
FROM smart10.carga_servicios_cloud A1  
WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia = 2
AND A1.id_estado_ticket = 1 AND A1.m1hm4h_sla = 1
group by A1.id_grupo_asignacion) TotalInc,
(SELECT 
COUNT(1) 
FROM smart10.carga_servicios_cloud A1  
WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia = 1
AND A1.id_estado_ticket = 1 AND A1.m1hm4h_sla = 1
group by A1.id_grupo_asignacion) TotalReq
FROM smart10.carga_servicios_cloud A
INNER JOIN smart10.catalogo_grupo_asignado B ON A.id_grupo_asignacion = B.id_grupo_asignacion
INNER JOIN smart10.catalogo_tipo_incidencia C ON A.id_tipo_incidencia = C.id_tipo_incidencia
WHERE C.id_tipo_incidencia IN (1,2) AND A.m1hm4h_sla = 1
group by B.id_grupo_asignacion, B.grupo_asignacion,C.id_tipo_incidencia
ORDER BY B.id_grupo_asignacion ASC;

/*Cuenta de Tiempo  > 4 horas y < 8 horas*/
SELECT 
DISTINCT B.id_grupo_asignacion, B.grupo_asignacion,
(SELECT 
COUNT(1) 
FROM smart10.carga_servicios_cloud A1  
WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia = 2
AND A1.id_estado_ticket = 1 AND A1.m4hm8h_sla = 1
group by A1.id_grupo_asignacion) TotalInc,
(SELECT 
COUNT(1) 
FROM smart10.carga_servicios_cloud A1  
WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia = 1
AND A1.id_estado_ticket = 1 AND A1.m4hm8h_sla = 1
group by A1.id_grupo_asignacion) TotalReq
FROM smart10.carga_servicios_cloud A
INNER JOIN smart10.catalogo_grupo_asignado B ON A.id_grupo_asignacion = B.id_grupo_asignacion
INNER JOIN smart10.catalogo_tipo_incidencia C ON A.id_tipo_incidencia = C.id_tipo_incidencia
WHERE C.id_tipo_incidencia IN (1,2) AND A.m4hm8h_sla = 1
group by B.id_grupo_asignacion, B.grupo_asignacion,C.id_tipo_incidencia
ORDER BY B.id_grupo_asignacion ASC;

/*Cuenta de Tiempo > 8 horas y < 24 horas: */
SELECT 
DISTINCT B.id_grupo_asignacion, B.grupo_asignacion,
(SELECT 
COUNT(1) 
FROM smart10.carga_servicios_cloud A1  
WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia = 2
AND A1.id_estado_ticket = 1 AND A1.m8hm24_sla = 1
group by A1.id_grupo_asignacion) TotalInc,
(SELECT 
COUNT(1) 
FROM smart10.carga_servicios_cloud A1  
WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia = 1
AND A1.id_estado_ticket = 1 AND A1.m8hm24_sla = 1
group by A1.id_grupo_asignacion) TotalReq
FROM smart10.carga_servicios_cloud A
INNER JOIN smart10.catalogo_grupo_asignado B ON A.id_grupo_asignacion = B.id_grupo_asignacion
INNER JOIN smart10.catalogo_tipo_incidencia C ON A.id_tipo_incidencia = C.id_tipo_incidencia
WHERE C.id_tipo_incidencia IN (1,2) AND A.m8hm24_sla = 1
group by B.id_grupo_asignacion, B.grupo_asignacion,C.id_tipo_incidencia
ORDER BY B.id_grupo_asignacion ASC;

/*Cuenta de Tiempo  > 24 horas*/
SELECT 
DISTINCT B.id_grupo_asignacion, B.grupo_asignacion,
(SELECT 
COUNT(1) 
FROM smart10.carga_servicios_cloud A1  
WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia = 2
AND A1.id_estado_ticket = 1 AND A1.m24h_sla = 1
group by A1.id_grupo_asignacion) TotalInc,
(SELECT 
COUNT(1) 
FROM smart10.carga_servicios_cloud A1  
WHERE A1.id_grupo_asignacion  = B.id_grupo_asignacion AND A1.id_tipo_incidencia = 1
AND A1.id_estado_ticket = 1 AND A1.m24h_sla = 1
group by A1.id_grupo_asignacion) TotalReq
FROM smart10.carga_servicios_cloud A
INNER JOIN smart10.catalogo_grupo_asignado B ON A.id_grupo_asignacion = B.id_grupo_asignacion
INNER JOIN smart10.catalogo_tipo_incidencia C ON A.id_tipo_incidencia = C.id_tipo_incidencia
WHERE C.id_tipo_incidencia IN (1,2) AND A.m24h_sla = 1
group by B.id_grupo_asignacion, B.grupo_asignacion,C.id_tipo_incidencia
ORDER BY B.id_grupo_asignacion ASC;




-- Declarar una variable para almacenar el resultado de la consulta
SET @mi_variable := NULL;

-- Asignar el resultado de la consulta a la variable
SELECT COUNT(1) INTO @mi_variable FROM smart10.carga_servicios_cloud A1 WHERE A1.id_estado_ticket = 1 
AND A1.id_grupo_asignacion NOT IN(1,2,7,9,12,15,16,17,20,21,25);

CREATE TEMPORARY TABLE tabla_temporal (
    tipo varchar(50) PRIMARY KEY,
	`Tiempo < 1 hora - Cant` INT,
    `Tiempo  > 1 hora y < 4 horas - Cant` INT, 
    `Tiempo  > 4 horas y < 8 horas - Cant` INT, 
    `Tiempo > 8 horas y < 24 hora - Cant` INT, 
    `Tiempo  > 24 horas - Cant` INT, 
    Total INT
    
);

INSERT INTO tabla_temporal (tipo, `Tiempo < 1 hora - Cant`,`Tiempo  > 1 hora y < 4 horas - Cant`,  `Tiempo  > 4 horas y < 8 horas - Cant`, `Tiempo > 8 horas y < 24 hora - Cant`, `Tiempo  > 24 horas - Cant`, Total)
SELECT A.tipo,
(SELECT COUNT(1) FROM smart10.carga_servicios_cloud A1 WHERE A1.id_tipo_incidencia = A.id_tipo_incidencia AND A1.id_estado_ticket = 1 AND A1.m1h_sla = 1
AND A1.id_grupo_asignacion NOT IN(1,2,7,9,12,15,16,17,20,21,25)) AS "Tiempo  < 1 hora",
(SELECT COUNT(1) FROM smart10.carga_servicios_cloud A1 WHERE A1.id_tipo_incidencia = A.id_tipo_incidencia AND A1.id_estado_ticket = 1 AND A1.m1hm4h_sla = 1
AND A1.id_grupo_asignacion NOT IN(1,2,7,9,12,15,16,17,20,21,25)) AS "Tiempo  > 1 hora y < 4 horas",
(SELECT COUNT(1) FROM smart10.carga_servicios_cloud A1 WHERE A1.id_tipo_incidencia = A.id_tipo_incidencia AND A1.id_estado_ticket = 1 AND A1.m4hm8h_sla = 1
AND A1.id_grupo_asignacion NOT IN(1,2,7,9,12,15,16,17,20,21,25)) AS "Tiempo  > 4 horas y < 8 horas",
(SELECT COUNT(1) FROM smart10.carga_servicios_cloud A1 WHERE A1.id_tipo_incidencia = A.id_tipo_incidencia AND A1.id_estado_ticket = 1 AND A1.m8hm24_sla = 1
AND A1.id_grupo_asignacion NOT IN(1,2,7,9,12,15,16,17,20,21,25)) AS "Tiempo > 8 horas y < 24 hora",
(SELECT COUNT(1) FROM smart10.carga_servicios_cloud A1 WHERE A1.id_tipo_incidencia = A.id_tipo_incidencia AND A1.id_estado_ticket = 1 AND A1.m24h_sla = 1
AND A1.id_grupo_asignacion NOT IN(1,2,7,9,12,15,16,17,20,21,25)) AS "Tiempo  > 24 horas",
(SELECT COUNT(1) FROM smart10.carga_servicios_cloud A1 WHERE A1.id_tipo_incidencia = A.id_tipo_incidencia AND A1.id_estado_ticket = 1 
AND A1.id_grupo_asignacion NOT IN(1,2,7,9,12,15,16,17,20,21,25)) AS "Total"
FROM smart10.catalogo_tipo_incidencia A;

Select * from tabla_temporal;
