## Plataforma Banco de Iniciativas de Proyectos
### Periodo Académico: 2018-2
### Curso: Procesos de Desarrollo de Software (PDSW)
#### Plataforma Banco de Iniciativas de Proyectos para la Escuela Colombiana de Ingeniería Julio Garavito
---
### Integrantes
- Scrum Master
	- Carlos Andrés Medina Rivas
- Scrum team
	- Amalia Inés Alfonso Campuzano
	- Carlos Andrés Medina Rivas
	- Willson Sneitder Melo Merchan
	- Julián Steven Bojacá Ovalle
- Product owner
	- Francisco Jose Chaves Alonso
- Client
	- Jorge Eduardo Acero Baracaldo
---
### Profesor: Francisco Jose Chaves Alonso
### Descripción del producto
La Plataforma banco de iniciativas de proyectos, es una herramienta donde la comunidad universitaria de la Escuela Colombiana de Ingeniería Julio Garavito pueden registrar sus 

iniciativas e ideas de proyectos para ser desarrollados o gestionados por la PMO de la Escuela. El sistema, más allá de facilitar el registro de las iniciativas e ideas de 

proyectos, es una valiosa base de conocimiento donde los diferentes actores pueden revisar si hay iniciativas, ideas o intereses similares y aunar esfuerzos para la 

materialización. Adicional a lo anterior, el personal académico puede integrar a los estudiantes de diferentes asignaturas o proyectos de grado para su realización, 

materializando las iniciativas para el beneficio de toda la comunidad universitaria. La PMO puede priorizar las iniciativas viables, asignar responsables, gestionar la 

asignación de recursos, llevar métricas e identificar grupos de interés -a través del tiempo- que se van presentando para cada iniciativa. Con esto se espera lograr no solo la 

realización de los proyectos sino una aplicación práctica de los conocimientos adquiridos por los estudiantes dentro de las asignaturas de la Escuela generando un ambiente de 

aprendizaje colaborativo.

## Funcionalidades
Ahora se hará descripción de las funcionalidades de la aplicación detalladamente:
**Página de inicio:**

![Pantalla De Inicio](/images/PantallaInicio.png)
  
Esta es la página de inicio. En esta ventana el usuario cuenta con dos opciones, registrarse y iniciar sesión. En iniciar sesión los campos deben ser obligatorios y coincidir con un usuario existente, de lo contrario no podrá ingresar a la página:

![Imagen 1](/images/PantallaInicioLoginIncorrecto.png) 

![Imagen 2](/images/PantallaInicioCamposRequeridos.png)

En la imagen 1, se intentó acceder con un usuario y contraseña incorrectos. en la imagen 2 se intentó acceder sin llenar los campos.

**Página de registro:**
En la ventana de registro se pide la información necesaria para poder crear un usuario, esta información consiste en nombre, apellido, correo, carné y área a la que pertenece el usuario.

![Pantalla De Registro](/images/PantallaDeRegistro.png)

Todos los campos de registro son requeridos, de lo contrario el usuario no se podrá registrar. Una vez se registre, el usuario podrá acceder a la página ingresando el correo en el campo de usuario y como contraseña será su carné.

**Menú principal de la página:**

A este menú se accede cuando el usuario inicia sesión. también si hace click sobre la opción de "home" o sobre el logo de la casa. En el menú principal se puede ver la información del usuario que a accedido a la página en la parte superior derecha de la pantalla, a la vez que se ve un botón de salida, que hará que el usuario finalice sesión.

![Usuario Proponente](/images/PantallaMenuPrincipal.png)

![Usuario Administrador](/images/MenuOpcionesAdministrador.png)

Dependiendo del tipo de rol que maneje el usuario que inicie sesión, este manejará más o menos opciones. En la primera imagen se ve el menú de un usuario proponente y en la segunda imagen se ve el menú de un administrador. El administrador tiene más opciones y permisos que el proponente.

**Opciones del perfil del usuario:**
Cualquier usuario puede consultar su información personal en las opción que dice "perfil": 

![Opciones de perfil](/images/MenuOpcionesPerfil.png) 

Como se ve en la imagen, la opción de perfil, nos permite acceder a la información de la cuenta, y la información de mis iniciativa. Si seleccionamos la opción que dice cuenta, se mostrará la información del usuario (nombre, apellido, correo, carné, rol, dependencia y estado).

![Cuenta](/images/PerfilOpcionCuenta.png)

Si seleccionamos la opción de "Mis Iniciativas", se mostrarán las iniciativas que haya propuesto el usuario como la información de cada una (id, descripción, detalle, fecha de creación, fecha de modificación de estado, estado actual y palabras clave):

![Mis iniciativas](/images/MisIniciativas.png)

También en la parte inferior está la opción de "editar", para modificar una iniciativa. Esta opción sólo se podrá realizar si la iniciativa está en espera de revisión:

![Editando Iniciativa](/images/OpcionEditarIniciativa.png)

**Opciones de las iniciativas:**
El usuario puede crear una iniciativa, consultar las demás iniciativas y ver estadísticas acerca de las iniciativas:

![Menú de iniciativas](/images/MenuOpcionesIniciativa.png)

Si seleccionamos la opción de crear se abre la siguiente ventana:

![Registro de iniciativa](/images/RegistroDeIniciativa.png)

Se llenan los campos requeridos. Se hace una breve descripción de la iniciativa y luego se explica a detalle en que consiste la propuesta. Finalmente se asocian palabras clave para facilitar la consulta de la iniciativa. Cundo se tenga todo lo requerido se selecciona la opción de registrar iniciativa.

**Consulta de Iniciativas:**
Al seleccionar la opción de consultar se nos muestra la siguiente página:

![Consulta De Iniciativas](/images/OpcionConsultarIniciativas.png)

En esta opción se puede buscar la iniciativa que el usuario desee. La búsqueda se puede filtrar por proponente, por fecha, por dependencia, por estado de la iniciativa y por las palabras claves asociadas. Al consultar, se muestran diferentes datos de la iniciativa (id, descripción, detalle, fecha de creación, fecha de modificación, proponente, estado actual de la iniciativa, palabras claves asociadas, comentarios y votos). Para consultar quien es el proponente de la iniciativa se debe dar click sobre la casilla proponente de la inciativa:

![Proponente de la iniciativa](/images/ProponenteIniciativa.png)

También podemos agregar comentarios a una iniciativa. En la casilla de comentarios de la iniciativa seleccionamos la opción con un "+". Esto nos abrirá la siguiente ventana:

![Adición del comentario](/images/AgregarComentario.png)

Si presionamos la opción al lado izquierdo del botón "+", se podrán consultar lo comentarios:

![Adición del comentario](/images/Comentario.png)

**Estadísticas:**
Al seleccionar la opción de estadísticas, podremos ver las estadísticas de iniciativas por dependencia, fechas y estados de la iniciativas.
Las estadísticas por dependencia:

![Estadisticas por dependencias](/images/EstadisticasPorArea.png)

Las estadísticas por la fecha:

![Estadisticas por fecha](/images/EstadisticasPorFecha.png)

Las estadísticas por estados de iniciativas:

![Estadisticas por estado](/images/EstadisticasEstado.png)

Para cada una de las estadísticas se puede importar un archivo excel.

## Arquitectura y diseño detallado:
>  -  **Modelo E-R:**
>  - **Diagrama de clases:**
>  -  **Descripción de la arquitectura (capas) y del Stack de tecnologías utilizado:**
	>  **_PrimeFaces:_**
	> **_Postgres SQL:_**
>  -   **Heroku:** [Banco de Iniciativas](https://bancodeiniciativaspdsw.herokuapp.com/)
## Descripción del proceso:   
> -   **Metodología:**
> - **Trello:**  [2018-2-PROYPDSW-ECI-JACS](https://trello.com/b/E69qw4fG/2018-2-proypdsw-eci-jacs)
> - **Release-burndown chart:**
> - **Sprints:**
	> *- Sprint 1:*
	> *- Sprint 2:*
	> *- Sprint 3:*
> - **Sprint-backlog:**
> - **Sprint-burndown chart:**
> - **Reporte de pruebas:**

CircleCI: [![CircleCI](https://circleci.com/gh/ECI-JACS/BancoDeIniciativasPDSW.svg?style=svg)](https://circleci.com/gh/ECI-JACS/BancoDeIniciativasPDSW)

Codacy: [![Codacy Badge](https://api.codacy.com/project/badge/Grade/ec7f18d2708945ebbf7ec467f078f8f7)](https://www.codacy.com/app/CarlosCL98/BancoDeIniciativasPDSW?

utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ECI-JACS/BancoDeIniciativasPDSW&amp;utm_campaign=Badge_Grade)