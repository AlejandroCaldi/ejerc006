package es.santander.ascender.ejer006.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.santander.ascender.ejer006.enums.Usos;
import es.santander.ascender.ejer006.model.Aula;
import es.santander.ascender.ejer006.model.Edificio;
import jakarta.transaction.Transactional;


@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
public class AulaRepositoryTest {

    @Autowired
    private AulaRepository repository;

    @Autowired
    private EdificioRepository repositoryEdificio;
    
    private Edificio edificio;

    @BeforeAll
    public void setUp() {
        edificio = new Edificio(null, "Gral.Patton", "Ernest Lluch 31", Usos.ADMINISTRACION, 123L);
        repositoryEdificio.save(edificio); 
    }

    @Transactional
    public Aula crearAulaEnEdificio(String codigo, Integer capacidadMaxima, Boolean enCondiciones, Long responsableId) {
 
        Aula aula = new Aula();
        aula.setCapacidadMaxima(capacidadMaxima);
        aula.setCodigo(codigo);
        aula.setEdificioId(edificio);
        aula.setEnCondiciones(enCondiciones);
        aula.setResponsableId(responsableId);
        
        repository.save(aula);
        
        return aula;
    }

   

    @Test
    public void testCreate() {

        Aula aula = crearAulaEnEdificio("A001",250,true,123l);
        repository.save(aula);

        assertTrue(
            repository
                .findById(aula.getId())
                .isPresent());

    }


    @Test
    public void delete() {

        Aula aula = crearAulaEnEdificio("B010",120,true,123l);
        repository.save(aula);

        assertTrue(repository.existsById(aula.getId()));

        repository.deleteById(aula.getId());

        assertFalse(repository.existsById(aula.getId()));
    }


    @Test
    public void view() {

        String codigo = "C111";
        Aula aula = crearAulaEnEdificio(codigo,55,false,15l);
        repository.save(aula);

        Optional<Aula> registro = repository.findById(aula.getId());

        assertTrue(registro.isPresent());
        assertTrue(registro.get().getCodigo().equals(codigo));
    }

    @Test
    public void update() {

        String codigoViejo = "D001";
        String codigoNuevo = "D100";
        Aula aula = crearAulaEnEdificio(codigoViejo,55,false,15l);
        repository.save(aula);

        assertTrue(repository.existsById(aula.getId()));

        aula.setCodigo(codigoNuevo);
        repository.save(aula);

        Optional<Aula> updatedAula = repository.findById(aula.getId());

        assertTrue(updatedAula.isPresent());
        assertTrue(updatedAula.get().getCodigo().equals(codigoNuevo));


    }

}
