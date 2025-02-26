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
import es.santander.ascender.ejer006.model.Silla;

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
    
    private Edificio edificio;
    private Aula aula;

    @BeforeAll
    public void setUp() {
        edificio = new Edificio(null, "Gral.Patton", "Ernest Lluch 31", Usos.ADMINISTRACION, 123L);
        repositoryEdificio.save(edificio); 

        aula = new Aula(null, "A999", 875, true, 123l, edificio);
        repositoryAula.save(aula);
    }



    private Silla getSilla(String codigoMesa, Condiciones condicionMesa, String marcaMesa, String modeloMesa, Aula aula,
        String codigoSilla, Condiciones condicionSilla, String marcaSilla, String modeloSilla) {
        
        Mesa mesa = new Mesa(null, codigoMesa, condicionMesa, marcaMesa, modeloMesa, aula);
        repositoryMesa.save(mesa);
    
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
        Silla silla = getSilla("MMM98765",Condiciones.OK, "MarcablaBlah", "ModeloChiChat", aula,
                            "XX5588",Condiciones.OK, "Tecno", "Reclianble DeLuxe");
        repository.save(silla);

        assertTrue(
            repository
                .findById(silla.getId())
                .isPresent());
    }


    @Test
    public void delete() {

        String codigoSilla = "XX5589";
        Silla silla = getSilla("MMM98766",Condiciones.OK, "MarcablaBlah", "ModeloChiChat", aula,
                               codigoSilla,Condiciones.OK, "Tecno", "Reclianble Standard");
        repository.save(silla);

        assertTrue(repository.existsById(silla.getId()));

        repository.deleteById(silla.getId());

        assertFalse(repository.existsById(silla.getId()));
    }

    @Test
    public void view() {

        String codigoBuscar = "S1000600";
        Silla silla = getSilla("MMM98767",Condiciones.OK, "MarcablaBlah", "ModeloChiChat", aula,
                         codigoBuscar,Condiciones.OK, "Tecno", "Reclianble Standard");
        repository.save(silla);

        Optional<Silla> registro = repository.findById(silla.getId());

        assertTrue(registro.isPresent());
        assertTrue(registro.get().getCodigo().equals(codigoBuscar));
    }

    @Test
    public void update() {

        String modeloOriginal = "Etrusca";
        String modeloNuevo = "Romana";
        Silla silla = getSilla("MMM98768",Condiciones.OK, "MarcablaBlah", "ModeloChiChat", aula,
                         "S1000699",Condiciones.OK, "Tecno", modeloOriginal);
        repository.save(silla);

        assertTrue(repository.existsById(silla.getId()));

        silla.setModelo(modeloNuevo);
        repository.save(silla);

        Optional<Silla> updatedSilla = repository.findById(silla.getId());

        assertTrue(updatedSilla.isPresent());
        assertTrue(updatedSilla.get().getModelo().equals(modeloNuevo));


    }

}
