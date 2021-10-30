package ar.com.ada.api.empleadas.entitites;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.ada.api.empleadas.entitites.calculos.*;

import java.util.*;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id")
    private Integer CategoriaId;

    private String  nombre;

    @Column(name = "sueldo_base")
    private BigDecimal sueldoBase;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Empleada> empleadas = new ArrayList<>();

    @JsonIgnore //para no devolverlo por el front
    @Transient //para que no impacte en el hibernate-> luego en la DB
    private ISueldoCalculator sueldoCalculator;
    
    public List<Empleada> getEmpleadas() {
        return empleadas;
    }
    public void setEmpleadas(List<Empleada> empleadas) {
        this.empleadas = empleadas;
    }
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
        switch (this.nombre) {
            case "Administrativa":
                this.sueldoCalculator = new SueldoAdministrativa();
                break;
            case "Ventas":
                this.sueldoCalculator = new SueldoVentas();
                break;
            case "Auxiliar":
                this.sueldoCalculator = new SueldoAuxiliar();
                break;
            default:
                this.sueldoCalculator = new SueldoAdministrativa();
                break;
        }
    }
    public BigDecimal getSueldoBase() {
        return sueldoBase;
    }
    public void setSueldoBase(BigDecimal sueldoBase) {
        this.sueldoBase = sueldoBase;
    }
    
    public void agregarEmpleada (Empleada empleada){
        this.empleadas.add(empleada);
    }

    public BigDecimal calcularProximoSueldo(Empleada empleada) {
        return sueldoCalculator.calcularSueldo(empleada);
    }

}
