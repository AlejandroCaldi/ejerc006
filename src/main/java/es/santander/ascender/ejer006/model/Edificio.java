package es.santander.ascender.ejer006.model;

import es.santander.ascender.ejer006.enums.Usos;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Edificio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nombre; 

    @Column(nullable = false)
    private String direccion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Usos uso = Usos.SIN_USO_ASIGNADO;

    @Column()
    private Long responsable;

    public Edificio() {
    }

    public Edificio(Long id, String nombre, String direccion, Usos uso, Long responsable) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.uso = uso;
        this.responsable = responsable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Usos getUso() {
        return uso;
    }

    public void setUso(Usos uso) {
        this.uso = uso;
    }

    public Long getResponsable() {
        return responsable;
    }

    public void setResponsable(Long responsable) {
        this.responsable = responsable;
    }

    


}
