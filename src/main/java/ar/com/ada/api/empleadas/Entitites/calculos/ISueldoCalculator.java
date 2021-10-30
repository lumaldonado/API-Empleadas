package ar.com.ada.api.empleadas.entitites.calculos;

import java.math.BigDecimal;

import ar.com.ada.api.empleadas.entitites.Empleada;

public interface ISueldoCalculator {

    BigDecimal calcularSueldo(Empleada empleada);
}
