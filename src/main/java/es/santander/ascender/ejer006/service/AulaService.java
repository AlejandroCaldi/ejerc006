package es.santander.ascender.ejer006.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.santander.ascender.ejer006.enums.CRUDOperation;
import es.santander.ascender.ejer006.exceptions.CrudSecurityException;
import es.santander.ascender.ejer006.model.Aula;
import es.santander.ascender.ejer006.repository.AulaRepository;

@Service
@Transactional
public class AulaService {

    @Autowired
    private AulaRepository repository;

    public Aula create(Aula Aula) {
        if (Aula.getId() != null) {
            throw new CrudSecurityException("Han tratado de modificar un registro de Aula utilizando la creación",
                                                 CRUDOperation.CREATE, 
                                                 Aula.getId());
        }
        return repository.save(Aula);
    }

    @Transactional(readOnly = true)
    public Aula read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Aula> read() {
        return repository.findAll();
    }

    public Aula update(Aula Aula) {
        if (Aula.getId() == null) {
            throw new CrudSecurityException("Han tratado de crear un registro Aula utilizando la modifición",
                                                 CRUDOperation.UPDATE, 
                                                 null);           
        }
        return repository.save(Aula);
    }

    public void delete(Long id) {
        repository.deleteById(id);
        return;
    }


}
