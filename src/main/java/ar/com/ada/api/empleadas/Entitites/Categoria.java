package ar.com.ada.api.empleadas.entitites;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.*;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITYT)
    @Column(name = "categoria_id")
    private Integer CategoriaId;

    private String  nombre;

    @Column(name = "sueldo_base")
    private BigDecimal sueldoBase;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Empleada> empleadas = new ArrayList<>();
    
    public Integer getCategoriaId() {
        return CategoriaId;
    }
    public void setCategoriaId(Integer categoriaId) {
        CategoriaId = categoriaId;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public BigDecimal getSueldoBase() {
        return sueldoBase;
    }
    public void setSueldoBase(BigDecimal sueldoBase) {
        this.sueldoBase = sueldoBase;
    }
    
}
