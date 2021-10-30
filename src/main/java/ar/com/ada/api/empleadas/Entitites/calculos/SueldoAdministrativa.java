package ar.com.ada.api.empleadas.entitites.calculos;

import java.math.BigDecimal;

import ar.com.ada.api.empleadas.entitites.Empleada;

public class SueldoAdministrativa implements ISueldoCalculator {
    @Override
    public BigDecimal calcularSueldo(Empleada empleada) {
        BigDecimal sueldoBase = empleada.getCategoria().getSueldoBase();
        if (empleada.getSueldo().compareTo(sueldoBase) == -1)
            return sueldoBase;
        return empleada.getSueldo();
    }
}
