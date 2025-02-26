package es.santander.ascender.ejer006.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import es.santander.ascender.ejer006.model.Silla;
import es.santander.ascender.ejer006.service.SillaService;
import jakarta.transaction.Transactional;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
public class SillaRepositoryTest {

    @Autowired
    private SillaRepository repository;

    @Autowired
    private EdificioRepository repositoryEdificio;

    @Autowired
    private AulaRepository repositoryAula;

    @Autowired
    private MesaRepository repositoryMesa;

    @Autowired
    private SillaService serviceSilla;
    
    private Edificio edificio;
    private Aula aula;
    private Mesa mesa;

    @BeforeAll
    public void setUp() {
        edificio = new Edificio(null, "Gral.Patton", "Ernest Lluch 31", Usos.ADMINISTRACION, 123L);
        repositoryEdificio.save(edificio); 

        aula = new Aula(null, "A999", 875, true, 123l, edificio);
        repositoryAula.save(aula);

        mesa = new Mesa(null, "MM77776", Condiciones.OK, "Marca Tipo Mesa", "No sé, es una mesa", aula);
        repositoryMesa.save(mesa);
    }



    private Silla getSilla(String codigoSilla, Condiciones condicionSilla, String marcaSilla, String modeloSilla, Mesa mesa) {
        
        Silla silla = new Silla();
        silla.setCodigo(codigoSilla);
        silla.setCondicion(condicionSilla);
        silla.setMarca(marcaSilla);
        silla.setModelo(modeloSilla);
        silla.setMesaId(mesa);
        return silla;

    }

    @Test
    public void testCreate() {
        Silla silla = getSilla("XX5588",Condiciones.OK, "Tecno", "Reclianble DeLuxe", mesa);
        repository.save(silla);

        assertTrue(
            repository
                .findById(silla.getId())
                .isPresent());
    }


    @Test
    public void delete() {

        String codigoSilla = "XX5589";
        Silla silla = getSilla(codigoSilla,Condiciones.OK, "Tecno", "Reclianble Standard", mesa);
        repository.save(silla);

        assertTrue(repository.existsById(silla.getId()));

        repository.deleteById(silla.getId());

        assertFalse(repository.existsById(silla.getId()));
    }

    @Test
    public void view() {

        String codigoBuscar = "S1000600";
        Silla silla = getSilla(codigoBuscar,Condiciones.OK, "Tecno", "Reclianble Standard", mesa);
        repository.save(silla);

        Optional<Silla> registro = repository.findById(silla.getId());

        assertTrue(registro.isPresent());
        assertTrue(registro.get().getCodigo().equals(codigoBuscar));
    }

    @Test
    public void update() {

        String modeloOriginal = "Etrusca";
        String modeloNuevo = "Romana";
        Silla silla = getSilla("S1000699",Condiciones.OK, "Tecno", modeloOriginal, mesa);
        repository.save(silla);

        assertTrue(repository.existsById(silla.getId()));

        silla.setModelo(modeloNuevo);
        repository.save(silla);

        Optional<Silla> updatedSilla = repository.findById(silla.getId());

        assertTrue(updatedSilla.isPresent());
        assertTrue(updatedSilla.get().getModelo().equals(modeloNuevo));

    }

    @Transactional
    @Test
    public void moverSillaMesa() {
        Mesa mesaNueva = new Mesa(null, "MM7777", Condiciones.SUCIA, "Marca Genérica", "modelo genérico (es una silla)", aula);
        repositoryMesa.save(mesaNueva);

        Silla silla = new Silla(null, "MU0444",Condiciones.OK,"MesaStar","Cedro barnizado", mesa);
        repository.save(silla);

        serviceSilla.moverSillaDeMesa(silla.getId(), mesaNueva.getId());

        Silla sillaActualizada = repository.findById(silla.getId()).orElseThrow();

        assertEquals(mesaNueva.getId(), sillaActualizada.getMesaId().getId(), "MesaId de silla actualizada debería ser igual a id de Mesa");

    }

}
