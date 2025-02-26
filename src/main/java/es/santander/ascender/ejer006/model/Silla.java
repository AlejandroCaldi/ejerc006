package es.santander.ascender.ejer006.model;


import es.santander.ascender.ejer006.enums.Condiciones;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

@Entity
public class Silla {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo; 

    @Enumerated(EnumType.STRING)
    private Condiciones condicion; 

    @Size(max = 30)
    @Column
    private String marca;

    @Size(max = 200)
    @Column
    private String modelo;

    @ManyToOne
    @JoinColumn(name = "mesa_id", nullable = false) 
    private Mesa mesaId;

    public Silla(){

    }

    public Silla(Long id, String codigo, Condiciones condicion, @Size(max = 30) String marca,
            @Size(max = 200) String modelo, Mesa mesaId) {
        this.id = id;
        this.codigo = codigo;
        this.condicion = condicion;
        this.marca = marca;
        this.modelo = modelo;
        this.mesaId = mesaId;
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

    public Condiciones getCondicion() {
        return condicion;
    }

    public void setCondicion(Condiciones condicion) {
        this.condicion = condicion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Mesa getMesaId() {
        return mesaId;
    }

    public void setMesaId(Mesa mesaId) {
        this.mesaId = mesaId;
    }

}
