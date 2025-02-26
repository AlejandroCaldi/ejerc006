package es.santander.ascender.ejer006.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String codigo; 

    @Column(nullable = false)
    private Integer capacidadMaxima;

    @Column()
    private Boolean enCondiciones; 

    @Column(nullable = false)
    private Long responsableId;

    @ManyToOne
    @JoinColumn(name = "edificio_id", nullable = false) 
    private Edificio edificio;

    public Aula() {
    }

    public Aula(Long id, String codigo, Integer capacidadMaxima, Boolean enCondiciones, Long responsableId,
            Edificio edificio) {
        this.id = id;
        this.codigo = codigo;
        this.capacidadMaxima = capacidadMaxima;
        this.enCondiciones = enCondiciones;
        this.responsableId = responsableId;
        this.edificio = edificio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(Integer capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public Boolean getEnCondiciones() {
        return enCondiciones;
    }

    public void setEnCondiciones(Boolean enCondiciones) {
        this.enCondiciones = enCondiciones;
    }

    public Long getResponsableId() {
        return responsableId;
    }

    public void setResponsableId(Long responsableId) {
        this.responsableId = responsableId;
    }

    public Edificio getEdificioId() {
        return edificio;
    }

    public void setEdificioId(Edificio edificioId) {
        this.edificio = edificioId;
    }

    


}
