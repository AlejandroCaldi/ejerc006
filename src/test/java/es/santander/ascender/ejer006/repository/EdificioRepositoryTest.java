package es.santander.ascender.ejer006.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.santander.ascender.ejer006.enums.Usos;
import es.santander.ascender.ejer006.model.Edificio;

@SpringBootTest
public class EdificioRepositoryTest {

    @Autowired
    private EdificioRepository repository;


    private Edificio getEdificio(String nombre, String direccion, Usos uso, Long responsable) {
        Edificio edificio = new Edificio();
        edificio.setNombre(nombre);
        edificio.setDireccion(direccion);
        edificio.setUso(uso);
        edificio.setResponsable(responsable);
        return edificio;
    }
    

    @Test
    public void testCreate() {
        Edificio edificio =  getEdificio("Gral.Sun Yat Sen", "Ernest Lluch 29", Usos.ADMINISTRACION, 123l);
        repository.save(edificio);

        assertTrue(
            repository
                .findById(edificio.getId())
                .isPresent());
    }


    @Test
    public void delete() {

        Edificio edificio =  getEdificio("Naciones Unidas", "Calvo Sotelo 15", Usos.ALMACEN, 23l);
        repository.save(edificio);

        assertTrue(repository.existsById(edificio.getId()));

        repository.deleteById(edificio.getId());

        assertFalse(repository.existsById(edificio.getId()));
    }

    @Test
    public void view() {

        String nombreBuscar = "Naciones Unidas 2. La venganza";
        Edificio edificio =  getEdificio("Naciones Unidas 2. La venganza", "Calvo Sotelo 17", Usos.CAPACITACION, 23l);
        repository.save(edificio);

        Optional<Edificio> registro = repository.findById(edificio.getId());

        assertTrue(registro.isPresent());
        assertTrue(registro.get().getNombre().equals(nombreBuscar));
    }

    @Test
    public void update() {

        String edificioNuevoNombre = "La secretaría de la Culpa";
        Edificio edificio = getEdificio("El Ministerio de la Culpa", "San Fernando 13", Usos.SIN_USO_ASIGNADO, 1l);
        repository.save(edificio);

        assertTrue(repository.existsById(edificio.getId()));

        edificio.setNombre(edificioNuevoNombre);
        repository.save(edificio);

        Optional<Edificio> updatedEdificio = repository.findById(edificio.getId());

        assertTrue(updatedEdificio.isPresent());
        assertTrue(updatedEdificio.get().getNombre().equals(edificioNuevoNombre));


    }

}
