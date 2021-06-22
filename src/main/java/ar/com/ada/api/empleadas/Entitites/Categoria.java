package ar.com.ada.api.empleadas.entitites;
import java.math.BigDecimal;

public class Categoria {

    private Integer CategoriaId;
    private String  nombre;
    private BigDecimal sueldoBase;
    
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
