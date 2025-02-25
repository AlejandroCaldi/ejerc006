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

import es.santander.ascender.ejer006.model.Mesa;
import es.santander.ascender.ejer006.service.MesaService;

@RestController
@RequestMapping("/api/mesa")
public class MesaController {

    @Autowired
    private MesaService mesaService;

    @PostMapping
    public Mesa create(@RequestBody Mesa mesa) {
        return mesaService.create(mesa);
    }

    @GetMapping("/{id}")
    public Mesa read(@PathVariable("id") Long id) {
        return mesaService.read(id);
    }
    
    @GetMapping
    public List<Mesa> list() {
        return mesaService.read();
    }

    @PutMapping
    public Mesa update(@RequestBody Mesa mesa) {
        return mesaService.update(mesa);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        mesaService.delete(id);
    }

}
