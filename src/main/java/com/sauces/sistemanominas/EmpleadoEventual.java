/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.sistemanominas;

/**
 *
 * @author daw1
 */
public class EmpleadoEventual extends Empleado{

    private float salarioHora;

    private int horas;

    public EmpleadoEventual(String dni, String nombre, float salarioHora, int horas) {
        super(dni, nombre);
        this.salarioHora = salarioHora;
        this.horas = horas;
    }

    /**
     * Get the value of horas
     *
     * @return the value of horas
     */
    public int getHoras() {
        return horas;
    }

    /**
     * Set the value of horas
     *
     * @param horas new value of horas
     */
    public void setHoras(int horas) {
        this.horas = horas;
    }

    /**
     * Get the value of salarioHora
     *
     * @return the value of salarioHora
     */
    public float getSalarioHora() {
        return salarioHora;
    }

    /**
     * Set the value of salarioHora
     *
     * @param salarioHora new value of salarioHora
     */
    public void setSalarioHora(float salarioHora) {
        this.salarioHora = salarioHora;
    }

    @Override
    public String toString() {
        return super.toString()+", "+salarioHora+", "+horas;
    }

    
    
    @Override
    public float ingresos() {
        return salarioHora*horas;
    }
    
}
