package es.santander.ascender.ejer006.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.santander.ascender.ejer006.enums.CRUDOperation;
import es.santander.ascender.ejer006.exceptions.CrudSecurityException;
import es.santander.ascender.ejer006.model.Mesa;
import es.santander.ascender.ejer006.repository.MesaRepository;

@Service
@Transactional
public class MesaService {

    @Autowired
    private MesaRepository repository;

    public Mesa create(Mesa mesa) {
        if (mesa.getId() != null) {
            throw new CrudSecurityException("Han tratado de modificar un registro de Mesa utilizando la creación",
                                                 CRUDOperation.CREATE, 
                                                 mesa.getId());
        }
        return repository.save(mesa);
    }

    @Transactional(readOnly = true)
    public Mesa read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Mesa> read() {
        return repository.findAll();
    }

    public Mesa update(Mesa mesa) {
        if (mesa.getId() == null) {
            throw new CrudSecurityException("Han tratado de crear un registro Mesa utilizando la modifición",
                                                 CRUDOperation.UPDATE, 
                                                 null);           
        }
        return repository.save(mesa);
    }

    public void delete(Long id) {
        repository.deleteById(id);
        return;
    }


}
