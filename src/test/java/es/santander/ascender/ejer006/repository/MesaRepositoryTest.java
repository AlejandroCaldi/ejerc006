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

import es.santander.ascender.ejer006.enums.Condiciones;
import es.santander.ascender.ejer006.enums.Usos;
import es.santander.ascender.ejer006.model.Aula;
import es.santander.ascender.ejer006.model.Edificio;
import es.santander.ascender.ejer006.model.Mesa;
import jakarta.transaction.Transactional;


@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
public class MesaRepositoryTest {

    @Autowired
    private MesaRepository repository;

    @Autowired
    private EdificioRepository repositoryEdificio;

    @Autowired
    private AulaRepository repositoryAula;
    
    private Edificio edificio;
    private Aula aula;

    @BeforeAll
    public void setUp() {
        edificio = new Edificio(null, "Gral.Patton", "Ernest Lluch 31", Usos.ADMINISTRACION, 123L);
        repositoryEdificio.save(edificio); 

        aula = new Aula(null, "A999", 875, true, 123l, edificio);
        repositoryAula.save(aula);
    
    }

    @Transactional
    public Mesa crearMesaEnAula(String codigo, Condiciones condicion, String marca, String modelo, Aula aula) {
 
        Mesa mesa = new Mesa(null, codigo, condicion, marca, modelo, aula);       
        repository.save(mesa);
        
        return mesa;
    }
    
    @Test
    public void testCreate() {

        Mesa mesa = crearMesaEnAula("MM9999",Condiciones.OK,"Tecno","Araucaria oscura", aula);
        repository.save(mesa);

        assertTrue(
            repository
                .findById(mesa.getId())
                .isPresent());
    }


    @Test
    public void delete() {

        Mesa mesa = crearMesaEnAula("MM0002",Condiciones.SUCIA,"Tecno","Cedro barnizado",aula);
        repository.save(mesa);

        assertTrue(repository.existsById(mesa.getId()));

        repository.deleteById(mesa.getId());

        assertFalse(repository.existsById(mesa.getId()));
    }


    @Test
    public void view() {

        Condiciones cond = Condiciones.OK;
        Mesa mesa = crearMesaEnAula("MM20003",cond,"MesaStar","Cedro barnizado", aula);
        repository.save(mesa);

        Optional<Mesa> registro = repository.findById(mesa.getId());

        assertTrue(registro.isPresent());
        assertTrue(registro.get().getCondicion().equals(cond));
    }


    @Test
    public void update() {

        Condiciones condPrevia = Condiciones.ROTA;
        Condiciones condArreglada = Condiciones.OK;
        Mesa mesa = crearMesaEnAula("MU0004",condPrevia,"MesaStar","Cedro barnizado", aula);
        repository.save(mesa);

        assertTrue(repository.existsById(mesa.getId()));

        mesa.setCondicion(condArreglada);
        repository.save(mesa);

        Optional<Mesa> updatedMesa = repository.findById(mesa.getId());

        assertTrue(updatedMesa.isPresent());
        assertTrue(updatedMesa.get().getCondicion().equals(condArreglada));


    }

}
