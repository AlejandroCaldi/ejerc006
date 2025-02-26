package es.santander.ascender.ejer006.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.santander.ascender.ejer006.model.Silla;
import es.santander.ascender.ejer006.service.SillaService;

@RestController
@RequestMapping("/api/silla")
public class SillaController {

    @Autowired
    private SillaService sillaService;

    @PostMapping
    public Silla create(@RequestBody Silla silla) {
        return sillaService.create(silla);
    }

    @GetMapping("/{id}")
    public Silla read(@PathVariable("id") Long id) {
        return sillaService.read(id);
    }
    
    @GetMapping
    public List<Silla> list() {
        return sillaService.read();
    }

    @PutMapping
    public Silla update(@RequestBody Silla silla) {
        return sillaService.update(silla);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        sillaService.delete(id);
    }

    @PutMapping("/{sillaId}/mover/{mesaId}")
    public ResponseEntity<Silla> moverSilla(
            @PathVariable Long sillaId,
            @PathVariable Long mesaId) {
        Silla sillaActualizada = sillaService.moverSillaDeMesa(sillaId, mesaId);
        return ResponseEntity.ok(sillaActualizada);
    }


}
