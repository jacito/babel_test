# Proyecto :: babel_test
Prueba Técnica Babel
<br><br>

## Introducción
La prueba consiste en un servicio REST que contenga las siguientes operaciones: <br>
• API que recupere el listado de todos los empleados registrados. <br>
• API que borre un empleado, requiere como parámetro el id de registro. <br>
• API que actualice cualquier dato del empleado. <br>
• API que inserte uno o varios empleados en una sola petición. <br>
<br><br>

## Stack Tecnológico 
- **Lenguaje de programación**: Java 17
- **Framework**: Spring Boot 3.3.8
- **JPA**
- **Test**: junit 3.3.8 y mockito-core 3.12.4
<br><br>

## Base de Datos
El scrip para la creación de tablas se puede encontrar en 
[![Documento](https://img.shields.io/badge/Documento-%F0%9F%93%9D-blue)](https://github.com/jacito/babel_test_images/blob/main/db/babel_test.sql)

![babel_test](https://github.com/jacito/babel_test_images/blob/main/db/ER.jpg)
<br><br>

## Proyecto
Este proyecto proporciona una solución para la gestión de empleados. Aunque es sencillo, está diseñado para ser escalable mediante una arquitectura en capas, donde cada capa tiene una responsabilidad clara y las capas interactúan entre sí. 
<br><br>

### Estructura
A continuación, se describen las principales capas del proyecto:

1. db
Contiene los modelos de datos y los repositorios para interactuar con la base de datos.
     model/: Definición de las entidades del proyecto, como Employee, EventLog y ErrorLog.
repository/: Implementación de los repositorios, que proporcionan acceso a la base de datos  EmployeeRepository, EventLogRepository y ErrorLogRepository.

2. service
Contiene los servicios del sistema, que implementan la lógica de negocio y las reglas de procesamiento de los datos EmployeeService, EventLogService y ErrorLogService.

3. controller
Gestiona las solicitudes HTTP. Aquí se encuentra el controlador principal (único) que maneja las operaciones solicitadas para el servicio REST: EmployeeController.

4. exception
Contiene las clases de manejo de excepciones globales.

5. vo
Aquí se encuentran el Value Objects utilizado en el sistema para representar entidades: EmployeeVO.

![estructura](https://github.com/jacito/babel_test_images/blob/main/proyecto/Estructura.jpg)
<br><br>

### Logs
Actualmente se manejan dos logs, que se estan creando dentro de la estructura del proyecto al correr el aplicativo con el IDE de IntelliJ
<br>
![LOGS](https://github.com/jacito/babel_test_images/blob/main/proyecto/log/logs.jpg)

1.- babelEmployee.log: que imprime todo <br>
![babelEmployee.log](https://github.com/jacito/babel_test_images/blob/main/proyecto/log/log1.jpg)

2.- sqlQueries.log: que contiene información sobre la ejecución de operaciones en la base de datos. <br>
![sqlQueries.log](https://github.com/jacito/babel_test_images/blob/main/proyecto/log/log2.jpg)

Actualmente el log se encuentra en un nivel DEBUG para babelEmployee.log y algunos TRACE para sqlQueries.log

- **Ejemplo HEADERS: babelEmployee.log**
  ![HEADERS](https://github.com/jacito/babel_test_images/blob/main/proyecto/log/headers.jpg)
- **Ejemplo QUERIES: sqlQueries.log**
   ![QUERIES](https://github.com/jacito/babel_test_images/blob/main/proyecto/log/queries.jpg)

<br><br>
### Bitacora
Se manejan dos tipos de bitacoras
1.- eventLog: que se trata de todos los eventos exitosos
2.- errorLog: que se trata de todos los eventos fallidos controlados

![TablasBitacoras](https://github.com/jacito/babel_test_images/blob/main/proyecto/bitacoras.jpg)


### Contrato
El servicio REST actual cuenta con las siguientes operaciones
| Tipo Método| Operacion| Descripción|
|-----------|-------------|----------|
| GET | getActiveEmployees | Obtiene todos los empleados en estado ACTIVO. El borrado es solo lógico.|
| GET | getAllEmployees | Obtienen todos los empleados sin importar su estado.|
| POST | addEmployees| Añade uno o vatios empleados.|
| GET | getEmployeeById| Obtiene un empleado por su ID.|
| DEL |deleteEmployee | Elimina un empleado por su ID. Hace el cambio lógico a INACTIVO.|
| PUT |updateEmployee | Actualiza un empleado por su ID y la data proporcionada.|

Para mayor información sobre los métodos se puede consultar el siguiente link
[![GitHub Logo](https://github.com/jacito/babel_test_images/blob/main/contrato/logoPostman.png)](https://documenter.getpostman.com/view/22553284/2sAYdZsYZB)

También puede descargar la colección de operaciones para Postmant

[![Descargar](https://github.com/jacito/babel_test_images/blob/main/contrato/operaciones.jpg)](https://raw.githubusercontent.com/jacito/babel_test_images/main/contrato/BabelEmployeeCollection.rar)


### Pruebas
Para finalizar se cuenta con las siguientes pruebas del servicio REST

---
---
|GET | getActiveEmployees | 
|-----------|-------------|
| 200 | OK  | 
- **Postman**:<br>
![POSTMANT_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/getActiveEmployees/getActiveEmployeesPostman.jpg)
- **LOGS**:<br>
![LOG1_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/getActiveEmployees/getActiveEmployeesLog1.jpg)
![LOG2_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/getActiveEmployees/getActiveEmployeesLog2.jpg)
![LOG3_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/getActiveEmployees/getActiveEmployeesLog3.jpg)
- **DB**:<br>
![DB1_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/getActiveEmployees/getActiveEmployeesDB1.jpg)
![DB2_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/getActiveEmployees/getActiveEmployeesDB2.jpg)

---
---
|GET | getAllEmployees | 
|-----------|-------------|
| 200 | OK  | 
- **Postman**:<br>
![POSTMANT1_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/getAllEmployees/getAllEmployeesPosmant1.jpg)
![POSTMANT2_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/getAllEmployees/getAllEmployeesPosmant2.jpg)
- **LOGS**:<br>
![LOG1_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/getAllEmployees/getAllEmployeesLog1.jpg)
![LOG2_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/getAllEmployees/getAllEmployeesLog2.jpg)
- **DB**:<br>
![DB1_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/getActiveEmployees/getActiveEmployeesDB1.jpg)

---
---
|POST | addEmployees | 
|-----------|-------------|
| 200 | OK  | 
- **Postman**:<br>
![POSTMANT_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/addEmployees/addEmployeesOKPostmant.jpg)
- **LOGS**:<br>
![LOG1_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/addEmployees/addEmployeesOKLog1.jpg)
![LOG2_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/addEmployees/addEmployeesOKLog2.jpg)
- **DB**:<br>
![DB1_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/addEmployees/addEmployeesOKLog1.jpg)
![DB2_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/addEmployees/addEmployeesOKLog2.jpg)

---
|POST | addEmployees | 
|-----------|-------------|
| 400 | Bad Request| 
- **Postman**:<br>
![POSTMANT_400](https://github.com/jacito/babel_test_images/blob/main/contrato/addEmployees/addEmployeesBadRequestPostmant.jpg)
- **LOGS**:<br>
![LOG1_400](https://github.com/jacito/babel_test_images/blob/main/contrato/addEmployees/addEmployeesBadRequestLog1.jpg)
![LOG2_400](https://github.com/jacito/babel_test_images/blob/main/contrato/addEmployees/addEmployeesBadRequestLog2.jpg)
- **DB**:<br>
![DB1_400](https://github.com/jacito/babel_test_images/blob/main/contrato/addEmployees/addEmployeesBadRequestDB.jpg)

---
---
| GET  | getEmployeeById | 
|-----------|-------------|
| 200 | OK  | 
- **Postman**:<br>
![POSTMANT_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/getEmployeeById/getEmployeeByIdOKPostman.jpg)
- **LOGS**:<br>
![LOG1_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/getEmployeeById/getEmployeeByIdOKLog1.jpg)
![LOG2_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/getEmployeeById/getEmployeeByIdOKLog2.jpg)
![LOG3_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/getEmployeeById/getEmployeeByIdOKLog3.jpg)
- **DB**:<br>
![DB1_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/getEmployeeById/getEmployeeByIdOKDB.jpg)

---
| GET  | getEmployeeById | 
|-----------|-------------|
| 404 | Not Found  | 
- **Postman**:<br>
![POSTMANT_404](https://github.com/jacito/babel_test_images/blob/main/contrato/getEmployeeById/getEmployeeById404Postman.jpg)
- **LOGS**:<br>
![LOG1_404](https://github.com/jacito/babel_test_images/blob/main/contrato/getEmployeeById/getEmployeeById404Log1.jpg)
![LOG2_404](https://github.com/jacito/babel_test_images/blob/main/contrato/getEmployeeById/getEmployeeById404Log2.jpg)
- **DB**:<br>
![DB1_404](https://github.com/jacito/babel_test_images/blob/main/contrato/getEmployeeById/getEmployeeById404DB.jpg)

---
---
| DEL  | deleteEmployee | 
|-----------|-------------|
| 200 | OK  |  
- **DB**:<br>
![DB_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/deleteEmployee/deleteEmployeeOkDB1.jpg)
- **Postman**:<br>
![POSTMANT_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/deleteEmployee/deleteEmployeeOkPostman.jpg)
- **LOGS**:<br>
![LOG1_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/deleteEmployee/deleteEmployeeOkLog1.jpg)
![LOG2_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/deleteEmployee/deleteEmployeeOkLog2.jpg)
- **DB**:<br>
![DB1_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/deleteEmployee/deleteEmployeeOkDB2.jpg)
![DB2_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/deleteEmployee/deleteEmployeeOkDB3.jpg)

---
| DEL  | deleteEmployee | 
|-----------|-------------|
| 404 | Not Found  | 
- **Postman**:<br>
![POSTMANT_404](https://github.com/jacito/babel_test_images/blob/main/contrato/deleteEmployee/deleteEmployee404Postman.jpg)
- **LOGS**:<br>
![LOG1_404](https://github.com/jacito/babel_test_images/blob/main/contrato/deleteEmployee/deleteEmployee404Log1.jpg)
![LOG2_404](https://github.com/jacito/babel_test_images/blob/main/contrato/deleteEmployee/deleteEmployee404Log2.jpg)
- **DB**:<br>
![DB1_404](https://github.com/jacito/babel_test_images/blob/main/contrato/deleteEmployee/deleteEmployee404DB1.jpg)

---
---
| PUT  | updateEmployee | 
|-----------|-------------|
| 200 | OK  | 
- **DB**:<br>
![DB_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/updateEmployee/updateEmployeeDB.jpg)
- **Postman**:<br>
![POSTMANT_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/updateEmployee/updateEmployeeOKPostman.jpg)
- **LOGS**:<br>
![LOG1_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/updateEmployee/updateEmployeeOKDlog1.jpg)
![LOG2_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/updateEmployee/updateEmployeeOKDlog2.jpg)
![LOG3_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/updateEmployee/updateEmployeeOKDlog3.jpg)
- **DB**:<br>
![DB1_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/updateEmployee/updateEmployeeOKDB1.jpg)
![DB2_OK](https://github.com/jacito/babel_test_images/blob/main/contrato/updateEmployee/updateEmployeeOKDB2.jpg)

---
| PUT  | updateEmployee | 
|-----------|-------------|
| 404 | Not Found  | 
- **Postman**:<br>
![POSTMANT_404](https://github.com/jacito/babel_test_images/blob/main/contrato/updateEmployee/updateEmployee400Postman.jpg)
- **LOGS**:<br>
![LOG1_404](https://github.com/jacito/babel_test_images/blob/main/contrato/updateEmployee/updateEmployee400Dlog1.jpg)
![LOG2_404](https://github.com/jacito/babel_test_images/blob/main/contrato/updateEmployee/updateEmployee400Dlog2.jpg)
- **DB**:<br>
![DB1_404](https://github.com/jacito/babel_test_images/blob/main/contrato/updateEmployee/updateEmployee400DB1.jpg)

---
| PUT  | updateEmployee | 
|-----------|-------------|
| 400 | Bad Request| 
- **Postman**:<br>
![POSTMANT_400](https://github.com/jacito/babel_test_images/blob/main/contrato/updateEmployee/updateEmployee400Postman.jpg)
- **LOGS**:<br>
![LOG1_400](https://github.com/jacito/babel_test_images/blob/main/contrato/updateEmployee/updateEmployee400Dlog1.jpg)
![LOG2_400](https://github.com/jacito/babel_test_images/blob/main/contrato/updateEmployee/updateEmployee400Dlog2.jpg)
- **DB**:<br>
![DB1_400](https://github.com/jacito/babel_test_images/blob/main/contrato/updateEmployee/updateEmployee400DB1.jpg)

---
---

