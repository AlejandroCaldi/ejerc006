package es.santander.ascender.ejer006.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.santander.ascender.ejer006.enums.Condiciones;
import es.santander.ascender.ejer006.model.Mesa;

@SpringBootTest
public class MesaRepositoryTest {

    @Autowired
    private MesaRepository repository;

    private Mesa getMesa(String codigo, Condiciones condicion, String marca, String modelo, Long aulaId) {
        Mesa mesa = new Mesa();
        mesa.setCodigo(codigo);
        mesa.setCondicion(condicion);
        mesa.setMarca(marca);
        mesa.setModelo(modelo);
        mesa.setAulaId(aulaId);
        return mesa;

    }

    @Test
    public void testCreate() {

        Mesa mesa = getMesa("MM0001",Condiciones.OK,"Tecno","Araucaria oscura", 15794l);
        repository.save(mesa);

        assertTrue(
            repository
                .findById(mesa.getId())
                .isPresent());
    }


    @Test
    public void delete() {

        Mesa mesa = getMesa("MM0002",Condiciones.SUCIA,"Tecno","Cedro barnizado", 15794l);
        repository.save(mesa);

        assertTrue(repository.existsById(mesa.getId()));

        repository.deleteById(mesa.getId());

        assertFalse(repository.existsById(mesa.getId()));
    }


    @Test
    public void view() {

        Condiciones cond = Condiciones.OK;
        Mesa mesa = getMesa("MM0003",cond,"MesaStar","Cedro barnizado", 15794l);
        repository.save(mesa);

        Optional<Mesa> registro = repository.findById(mesa.getId());

        assertTrue(registro.isPresent());
        assertTrue(registro.get().getCondicion().equals(cond));
    }


    @Test
    public void update() {

        Condiciones condPrevia = Condiciones.ROTA;
        Condiciones condArreglada = Condiciones.OK;
        Mesa mesa = getMesa("MM0004",condPrevia,"MesaStar","Cedro barnizado", 15794l);
        repository.save(mesa);

        assertTrue(repository.existsById(mesa.getId()));

        mesa.setCondicion(condArreglada);
        repository.save(mesa);

        Optional<Mesa> updatedMesa = repository.findById(mesa.getId());

        assertTrue(updatedMesa.isPresent());
        assertTrue(updatedMesa.get().getCondicion().equals(condArreglada));


    }

}
