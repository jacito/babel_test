# Proyecto :: babel_test
Prueba Técnica Babel

## Introducción
La prueba consiste en un servicio REST que contenga las siguientes operaciones: 
• API que recupere el listado de todos los empleados registrados. 
• API que borre un empleado, requiere como parámetro el id de registro. 
• API que actualice cualquier dato del empleado. 
• API que inserte uno o varios empleados en una sola petición. 

## Stack Tecnológico 
- **Lenguaje de programación**: Java 17
- **Framework**: Spring Boot 3.3.8
- **JPA**
- **Test**: junit 3.3.8 y mockito-core 3.12.4

## Base de Datos
El scrip para la creación de tablas se puede encontrar en 
[![Documento](https://img.shields.io/badge/Documento-%F0%9F%93%9D-blue)](https://github.com/jacito/babel_test_images/blob/main/db/babel_test.sql)

![babel_test](https://github.com/jacito/babel_test_images/blob/main/db/ER.jpg)

## Proyecto
Este proyecto proporciona una solución para la gestión de empleados. Aunque es sencillo, está diseñado para ser escalable mediante una arquitectura en capas, donde cada capa tiene una responsabilidad clara y las capas interactúan entre sí. 

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

### Logs
Actualmente se manejan dos logs, que se estan creando dentro de la estructura del proyecto al correr el aplicativo con el IDE de IntelliJ
![LOGS](https://github.com/jacito/babel_test_images/blob/main/proyecto/log/logs.jpg)

1.- babelEmployee.log: que imprime todo
![babelEmployee.log](https://github.com/jacito/babel_test_images/blob/main/proyecto/log/log1.jpg)

2.- sqlQueries.log: que contiene información sobre la ejecución de operaciones en la base de datos.
![sqlQueries.log](https://github.com/jacito/babel_test_images/blob/main/proyecto/log/log2.jpg)

Actualmente el log se encuentra en un nivel DEBUG para babelEmployee.log y algunos TRACE para sqlQueries.log

- **Ejemplo HEADERS: babelEmployee.log**
  ![HEADERS](https://github.com/jacito/babel_test_images/blob/main/proyecto/log/headers.jpg)
- **Ejemplo QUERIES: sqlQueries.log**
   ![QUERIES](https://github.com/jacito/babel_test_images/blob/main/proyecto/log/queries.jpg)

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
[![GitHub Logo](https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png)](https://documenter.getpostman.com/view/22553284/2sAYdZsYZB)

También puede descargar la colección de operaciones para Postmant
[![Descargar](https://github.com/jacito/babel_test_images/blob/main/contrato/operaciones.jpg)](https://raw.githubusercontent.com/jacito/babel_test_images/main/contrato/BabelEmployeeCollection.rar)


### Pruebas
