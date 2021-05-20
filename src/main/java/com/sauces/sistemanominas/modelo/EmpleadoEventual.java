/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.sistemanominas.modelo;

/**
 *
 * @author daw1
 */
public class EmpleadoEventual extends Empleado{

    private float salarioHora;

    private int horas;

    /**
     *
     * @param dni
     * @param nombre
     * @param salarioHora
     * @param horas
     */
    public EmpleadoEventual(String dni, String nombre, float salarioHora, int horas) throws DniException {
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

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return super.toString()+","+salarioHora+","+horas;
    }

    /**
     *
     * @return
     */
    @Override
    public float ingresos() {
        return salarioHora*horas;
    }
    
}
