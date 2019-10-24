# Aplicación distribuida segura en todos sus frentes

## Prerrequisitos
Es necesario tener instalado Java 1.8 y Apache Maven 3.6.0
## Descripción
Esta propuesta consta de dos servicios
que se comunican por medio de https usando certificados
ssl que se generaron previo al desarrollo. El primer servidor
hace las veces de cliente y ofrece un servicio matematico. El 
segundo servidor es el que se encarga de procesar el calculo
matematico, este calculo es regresado al servicio cliente y
ofrecido al usuario.

## Diseño
![c3](https://user-images.githubusercontent.com/13685178/67454700-d837c300-f5f0-11e9-81fb-4e3e253ecfc5.PNG)

## Ejecutar
### Descagar dependencias y compilar
```
mvn package
```
### Documentación
```
mvn javadoc:jar
```
La documentación generada por el comando anterior se encuentra en la ruta **target\apidocs**
### Ejecutar
```
java -jar target\security-client-0.0.1-SNAPSHOT.jar
java -jar target\security-0.0.1-SNAPSHOT.jar
```

## Probar
1. Debe cargar el certificado que se encuentra [aqui](https://github.com/Santiago-Rocha/AREM-Security/blob/master/nt-ms.p12) en al browser para acceder al servicio cliente, si no sabe como este [tutorial](https://support.globalsign.com/customer/es/portal/articles/1211541-instalar-certificado-digital-del-cliente---windows-usando-chrome)
2. Acceda al servicio cliente en la siguiente url https://localhost:9001
3. Al entrar por primera vez debera confirmar que desea utilizar el certificado que cargó en el paso 1
![c1](https://user-images.githubusercontent.com/13685178/67454525-0ec10e00-f5f0-11e9-8f26-b16a8ab5c649.PNG)

4. Para acceder al servicio utilice las siguiente credenciale
``` user: admin pwd: 1234 ```
![image](https://user-images.githubusercontent.com/13685178/67455040-e6d2aa00-f5f1-11e9-8fa9-3ce4c8fb3fc8.png)

5. Una vez dentro de la aplicacíon puede introducir un numero, este será elevado al cuadrado (dicha operacion es realizada por el otro servicio)
\
![image](https://user-images.githubusercontent.com/13685178/67455085-0bc71d00-f5f2-11e9-9788-501ecde4cab4.png)


## Pruebas de seguridad
### Acceder sin estar loggeado
Al intentar entrar a la pagina https://localhost:9001/calc será redigirdio nuevamente a la pagina del login hasta que acceda
### Acceder con credenciales erroneas
Al intentar acceder con una credenciales que no son correctas obtendra una alerta indicandole que hubi un error al entrar
![image](https://user-images.githubusercontent.com/13685178/67455223-a6276080-f5f2-11e9-8900-84ef9f80429e.png)

### Acceder al servicio matematico directamente
El servicio le mostrará un mensaje de error informandole que necesita TLS
![image](https://user-images.githubusercontent.com/13685178/67455167-6c565a00-f5f2-11e9-935f-765e1bb37fa2.png)


## Documento
Para más detalle del funcionamiento del proyecto ver el [documento](https://github.com/Santiago-Rocha/AREM-Security/blob/master/Informe.pdf) adjunto
## Autor 
Santiago Rocha Durán - Escuela Colombiana de Ingenieria Julio Garavito
## Licencia
Este proyecto está licenciado bajo GNU General Public License - ver [LICENSE](https://github.com/Santiago-Rocha/AREM-Security/blob/master/LICENSE.txt) para más detalles 
