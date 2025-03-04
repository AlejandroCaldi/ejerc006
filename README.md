# ejerc006

### Trabajo de descanso - Edificio

Terminado. 

Es necesario crear la base de datos 'edificios' en postgres. 

´´´sh
CREATE DATABASE edificios;
´´´

El Repositorio debe ser privado.


4 entidades
Edificio
    edificioId
Aula
    aulaID
    edificioID
Mesa
    mesaID
    edificioID
Silla
    sillaID
    mesaID


    Agregar otros campos para cada entidad, mínimo 4. Iventando validaciones.

    Chequeo y funcionamiento de endpoints

    Agregado de todas las claves foraneas y adaptados los test para respetar las restricciones. 

    Exponer dentro un método que permita mover una mesa a una nueva aula.

    Mover una silla de mesa. Que no sea el método actualizar. Ei Id de aula y el ID de mesa y que lo asigne. 

    Testear endpoints para esos movimientos. 

    Tests provocando fallos por restricciones de claves foraneas. 

En Proceso de armado.

