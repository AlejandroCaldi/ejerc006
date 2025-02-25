package es.santander.ascender.ejer006.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.santander.ascender.ejer006.model.Aula;

@SpringBootTest
public class AulaRepositoryTest {

    @Autowired
    private AulaRepository repository;


    private Aula getAula(String codigo, Integer capacidadMaxima, Boolean enCondiciones, Long responsableId, Long edificioId){
        Aula aula = new Aula();
        aula.setCodigo(codigo);
        aula.setCapacidadMaxima(capacidadMaxima);
        aula.setEnCondiciones(enCondiciones);
        aula.setResponsableId(responsableId);
        aula.setEdificioId(edificioId);
        return aula;
    }


    @Test
    public void testCreate() {

        Aula aula = getAula("A001",250,true,123l,23l);
        repository.save(aula);

        assertTrue(
            repository
                .findById(aula.getId())
                .isPresent());
    }


    @Test
    public void delete() {

        Aula aula = getAula("B010",120,true,123l,4l);
        repository.save(aula);

        assertTrue(repository.existsById(aula.getId()));

        repository.deleteById(aula.getId());

        assertFalse(repository.existsById(aula.getId()));
    }


    @Test
    public void view() {

        String codigo = "C111";
        Aula aula = getAula(codigo,55,false,15l,5l);
        repository.save(aula);

        Optional<Aula> registro = repository.findById(aula.getId());

        assertTrue(registro.isPresent());
        assertTrue(registro.get().getCodigo().equals(codigo));
    }

    @Test
    public void update() {

        String codigoViejo = "D001";
        String codigoNuevo = "D100";
        Aula aula = getAula(codigoViejo,55,false,15l,5l);
        repository.save(aula);

        assertTrue(repository.existsById(aula.getId()));

        aula.setCodigo(codigoNuevo);
        repository.save(aula);

        Optional<Aula> updatedAula = repository.findById(aula.getId());

        assertTrue(updatedAula.isPresent());
        assertTrue(updatedAula.get().getCodigo().equals(codigoNuevo));


    }

}
