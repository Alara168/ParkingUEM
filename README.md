# ParkingUEM
Aplicación realizada para el funcionamiento del parking a través de sensores y usando un archivo JSON. Su finalidad es permitir a los usuarios coger una plaza en concreto en un parking para leugo poder buscar donde está su coche.

## Índice

1. [Instalación](#instalación)
2. [Uso](#uso)
3. [Estructura de archivos](#estructura-de-archivos)
4. [Dependencias](#dependencias)
5. [Licencia](#licencia)

## Instalación

1. Clonar el repositorio: En la máquina de destino, clona el repositorio del proyecto desde un servicio de control de versiones como GitHub o GitLab. Puedes utilizar el comando git clone seguido de la URL del repositorio para hacerlo.

2. Configurar Eclipse: Si aún no tienes Eclipse instalado en la máquina de destino, descárgalo e instálalo desde el sitio oficial. Luego, ábrelo y configúralo según tus preferencias.

3. Importar el proyecto: En Eclipse, selecciona la opción "Importar" desde el menú "Archivo". Busca la opción "Proyectos existentes en el espacio de trabajo" y haz clic en "Siguiente". A continuación, selecciona la ubicación del proyecto clonado en tu máquina y haz clic en "Finalizar" para importarlo.

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
    1. Usuario medio:
    2. Seguridad:
    3. Administrador:
2. Inicio de sesión
    1. Al iniciar el programa le preguntará si desea iniciar sesión, registrarse o salir.
        -Al registrarse siempre se creará la cuenta como usuario medio con username, contraseña y matrícula del coche.
    2. Si desea iniciar sesión deberá introducir el nombre de usuario y la contraseña.
        -En caso de no existir o contraseña incorrecta le volverá a preguntar lo que desea hacer.
    
3. Funcionalidad

## Estructura de archivos


## Dependencias
    - JSON-Simple: 1.1.1

## Licencia
El software está bajo la licencia:
  - GNU General Public License v2.0

