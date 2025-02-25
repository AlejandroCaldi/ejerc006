package es.santander.ascender.ejer006.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.santander.ascender.ejer006.model.Aula;
import es.santander.ascender.ejer006.service.AulaService;

@RestController
@RequestMapping("/api/aula")
public class AulaController {

    @Autowired
    private AulaService aulaService;

    @PostMapping
    public Aula create(@RequestBody Aula aula) {
        return aulaService.create(aula);
    }

    @GetMapping("/{id}")
    public Aula read(@PathVariable("id") Long id) {
        return aulaService.read(id);
    }
    
    @GetMapping
    public List<Aula> list() {
        return aulaService.read();
    }

    @PutMapping
    public Aula update(@RequestBody Aula aula) {
        return aulaService.update(aula);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        aulaService.delete(id);
    }

}
