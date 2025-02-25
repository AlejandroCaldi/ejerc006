package es.santander.ascender.ejer006.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.santander.ascender.ejer006.enums.CRUDOperation;
import es.santander.ascender.ejer006.exceptions.CrudSecurityException;
import es.santander.ascender.ejer006.model.Edificio;
import es.santander.ascender.ejer006.repository.EdificioRepository;

@Service
@Transactional
public class EdificioService {

    @Autowired
    private EdificioRepository repository;

    public Edificio create(Edificio Edificio) {
        if (Edificio.getId() != null) {
            throw new CrudSecurityException("Han tratado de modificar un registro de Edificio utilizando la creación",
                                                 CRUDOperation.CREATE, 
                                                 Edificio.getId());
        }
        return repository.save(Edificio);
    }

    @Transactional(readOnly = true)
    public Edificio read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Edificio> read() {
        return repository.findAll();
    }

    public Edificio update(Edificio Edificio) {
        if (Edificio.getId() == null) {
            throw new CrudSecurityException("Han tratado de crear un registro Edificio utilizando la modifición",
                                                 CRUDOperation.UPDATE, 
                                                 null);           
        }
        return repository.save(Edificio);
    }

    public void delete(Long id) {
        repository.deleteById(id);
        return;
    }


}
