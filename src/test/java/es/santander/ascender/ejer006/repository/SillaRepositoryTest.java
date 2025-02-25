package es.santander.ascender.ejer006.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.santander.ascender.ejer006.enums.Condiciones;
import es.santander.ascender.ejer006.model.Mesa;
import es.santander.ascender.ejer006.model.Silla;

@SpringBootTest
public class SillaRepositoryTest {

    @Autowired
    private SillaRepository repository;

    private Silla getSilla(String codigo, Condiciones condicion, String marca, String modelo, Long mesaId) {
        Silla silla = new Silla();
        silla.setCodigo(codigo);
        silla.setCondicion(condicion);
        silla.setMarca(marca);
        silla.setModelo(modelo);
        silla.setMesaId(mesaId);
        return silla;

    }

    @Test
    public void testCreate() {
        Silla silla = getSilla("S1000000",Condiciones.OK, "Tecno", "Reclianble DeLuxe", 258l);
        repository.save(silla);

        assertTrue(
            repository
                .findById(silla.getId())
                .isPresent());
    }


    @Test
    public void delete() {

        Silla silla = getSilla("S1000251",Condiciones.SUCIA, "Tecno", "Reclianble DeLuxe", 350l);
        repository.save(silla);

        assertTrue(repository.existsById(silla.getId()));

        repository.deleteById(silla.getId());

        assertFalse(repository.existsById(silla.getId()));
    }

    @Test
    public void view() {

        String marcaBuscar = "Silluti";
        Silla silla = getSilla("S1000251",Condiciones.SUCIA, marcaBuscar, "Reclianble DeLuxe", 351l);
        repository.save(silla);

        Optional<Silla> registro = repository.findById(silla.getId());

        assertTrue(registro.isPresent());
        assertTrue(registro.get().getMarca().equals(marcaBuscar));
    }

    @Test
    public void update() {

        String modeloOriginal = "Etrusca";
        String modeloNuevo = "Romana";
        Silla silla = getSilla("S1000251",Condiciones.SUCIA, modeloOriginal, "Reclianble DeLuxe", 351l);
        repository.save(silla);

        assertTrue(repository.existsById(silla.getId()));

        silla.setModelo(modeloNuevo);
        repository.save(silla);

        Optional<Silla> updatedSilla = repository.findById(silla.getId());

        assertTrue(updatedSilla.isPresent());
        assertTrue(updatedSilla.get().getModelo().equals(modeloNuevo));


    }

}
