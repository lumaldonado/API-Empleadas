package ar.com.ada.api.empleadas.entitites.calculos;

import java.math.BigDecimal;

import ar.com.ada.api.empleadas.entitites.Empleada;

public class SueldoAuxiliar implements ISueldoCalculator {
    @Override
    public BigDecimal calcularSueldo(Empleada empleada) {
        return empleada.getCategoria().getSueldoBase();
    }
}
