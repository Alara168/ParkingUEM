# ParkingUEM
Aplicación realizada para el funcionamiento del parking a través de sensores y usando un archivo JSON. Su finalidad es permitir a los usuarios coger una plaza en concreto en un parking para luego poder buscar donde está su coche.

## Índice

1. [Instalación](#instalación)
2. [Uso](#uso)
3. [Estructura de archivos](#estructura-de-archivos)
4. [Dependencias](#dependencias)
5. [Licencia](#licencia)

## Instalación

1. Clonar el repositorio: En la máquina de destino, clona el repositorio del proyecto desde un servicio de control de versiones como GitHub o GitLab. Puedes utilizar el comando git clone seguido de la URL del repositorio para hacerlo.
    - En el caso de hacerlo desde un archivo .zip seguir los siguientes pasos y tras eso pasar al punto 2:
     1. Descargar el archivo .zip: En la máquina de destino, descarga el archivo .zip que contiene el proyecto desde el repositorio o cualquier otra fuente confiable.
     2. Extraer el archivo .zip: Extrae el contenido del archivo .zip en una ubicación de tu elección en la máquina de destino. Asegúrate de conservar la estructura de directorios del proyecto dentro del archivo .zip. 

2. Configurar Eclipse: Si aún no tienes Eclipse instalado en la máquina de destino, descárgalo e instálalo desde el sitio oficial. Luego, ábrelo y configúralo según tus preferencias.

3. Importar el proyecto: 
    1. En Eclipse, selecciona la opción "Importar" desde el menú "Archivo". 
    2. Busca la opción "Proyectos existentes en el espacio de trabajo" y haz clic en "Siguiente". 
    3. A continuación, selecciona la ubicación del proyecto clonado en tu máquina y haz clic en "Finalizar" para importarlo.

4. Configurar las dependencias: Si has utilizado JSON-Simple en tu proyecto, deberás agregar la biblioteca al proyecto en Eclipse. Puedes hacerlo de la siguiente manera:
    1. Descarga el archivo JAR de JSON-Simple desde el repositorio oficial o cualquier otra fuente confiable.
    2. En Eclipse, haz clic derecho en el proyecto importado y selecciona "Propiedades" en el menú contextual.
    3. En la ventana de propiedades, selecciona "Java Build Path" en el panel izquierdo.
    4. Haz clic en la pestaña "Librerías" y luego en "Agregar JAR externo".
    5. Busca y selecciona el archivo JAR de JSON-Simple que descargaste previamente.
    6. Haz clic en "Aplicar" o "Aceptar" para guardar los cambios.

5. Compilar y ejecutar el proyecto: Una vez que hayas configurado las dependencias, puedes compilar y ejecutar el proyecto en Eclipse. Asegúrate de tener las configuraciones adecuadas, como las rutas de archivo y las credenciales de acceso, si es necesario.

## Uso
1. Tipos de usuario
    1. **Usuario medio**:
        - Ver plazas libres
        - Coger una Plaza
        - Desocupar una Plaza
    2. **Seguridad**:
        - Cambiar un sensor de estado
    3. **Administrador**:
        - Visualización de estadísticas

2. Inicio de sesión
    1. Al iniciar el programa le preguntará si desea iniciar sesión, registrarse o salir.
        - Al registrarse siempre se creará la cuenta como usuario medio con username, contraseña y matrícula del coche.
    2. Si desea iniciar sesión deberá introducir el nombre de usuario y la contraseña.
        - En caso de no existir o contraseña incorrecta le volverá a preguntar lo que desea hacer.
    ~~~
   Bienvenido al sistema de inicio de sesión

    Seleccione una opción:
    1. Iniciar sesión
    2. Registrar usuario
    3. Salir
    ~~~
3. Funcionalidad
    1. **Usuario medio**
        ~~~
        ------------------- MENU -------------------
        Para ver las plazas libres, pulse 1
        Para coger una plaza, pulse 2
        Para encontrar un coche, pulse 3
        Para desocupar una plaza, pulse 4
        Para salir, pulse 5
        ~~~
       - Tras elegir la opción de ver plazas se mostrará:
            ~~~~
            Plaza: 4
            Plaza: 25
            Plaza: 89
            Plaza: 112
            Plaza: 175
            Plaza: 264
            Plaza: 350
            ~~~~
       - Tras elegir la opción de coger una plaza se mostrará:
            ~~~~
            Introduce el id de la plaza la cual quieras ocupar
            ~~~~
            - Tras introducir el numero de la plaza se mostrará:
                ~~~~
                Se ha actualizado el parking: 2
                ~~~~
                
       - Tras elegir la opción de buscar tu coche:
            ~~~~
            Tu plaza es: 72
            ~~~~
       - Tras elegir la opción de desocupar tu plaza se vaciará la plaza y se procederá a cerra la aplicación
            
        - Tras elegir la opción de salir se cerrará la aplicación

    2.**Seguridad**
      ~~~~
      Para cambiar el estado de una plaza, pulse 1
      Para salir, pulse 2
      ~~~~
        
      - Opción de cambiar un sensor se le preguntará la plaza que desea cambiar
          ~~~~
          Introduce el id que quieras cambiar
          ~~~~
      - Tras introducir el valor se cerrará la aplicación
             
    4.**Administrador**
      ~~~~
      Para ver las estadísticas, pulse 1
      Para salir, pulse 2
      ~~~~
      Tras elegir la opción de mostrar estadísticas se mostrará:
      ~~~~
        Se han realizado: 6 cambios
        Las 5 plazas más cogidas son: 
        Plaza: 3 ha sido ocupada: 18
        Plaza: 23 ha sido ocupada: 15
        Plaza: 53 ha sido ocupada: 13
        Plaza: 89 ha sido ocupada: 9
        Plaza: 197 ha sido ocupada: 5
      ~~~~

## Estructura de archivos
- `main`:
  - `Sistema.java`: Inicio de sesión.
  - `Funcionar.java`: En función del tipo de usuario realiza las funciones correspondientes.
  - `Organizar.java`: Organiza las plazas de mayor a menor en función de las veces que se ha cogido la plaza.
  - `bbdd.json`: Contiene los datos de las plazas y los usuarios.

## Dependencias
    - JSON-Simple: 1.1.1

## Licencia
El software está bajo la licencia:
  - GNU General Public License v2.0

