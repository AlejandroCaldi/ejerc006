package es.santander.ascender.ejercicio005.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.santander.ascender.ejercicio005.model.Persona;

@SpringBootTest
public class PersonaRepositoryTest {

    @Autowired
    private PersonaRepository repository;

    private Persona getPersona(String nombre, String apellido, Long provincia_Id) {
        Persona persona = new Persona();
        persona.setNombre(nombre);
        persona.setApellido(apellido);
        persona.setProvincia_Id(provincia_Id);
        return persona;
    }

    @Test
    public void testCreate() {
        Persona persona = getPersona("Alejandro","Caldi", 3l);
        repository.save(persona);

        assertTrue(
            repository
                .findById(persona.getId())
                .isPresent());
    }

    @Test
    public void delete() {

        Persona persona = new Persona("John","Balls",14l);
        repository.save(persona);

        assertTrue(repository.existsById(persona.getId()));

        repository.deleteById(persona.getId());

        assertFalse(repository.existsById(persona.getId()));
    }

    @Test
    public void view() {

        String nombre = "John";
        Persona persona = new Persona(nombre, "Balls", 11l);
        repository.save(persona);

        Optional<Persona> registro = repository.findById(persona.getId());

        assertTrue(registro.isPresent());
        assertTrue(registro.get().getNombre().equals(nombre));
    }

    @Test
    public void update() {

        String apellidoOriginal = "Pelotas";
        String apellidoNuevo = "Gomez";
        Persona persona = new Persona("Sebastián",apellidoOriginal, 6l);
        repository.save(persona);

        assertTrue(repository.existsById(persona.getId()));

        persona.setApellido(apellidoNuevo);
        repository.save(persona);

        Optional<Persona> updatedPersona = repository.findById(persona.getId());

        assertTrue(updatedPersona.isPresent());
        assertTrue(updatedPersona.get().getApellido().equals(apellidoNuevo));


    }

}
