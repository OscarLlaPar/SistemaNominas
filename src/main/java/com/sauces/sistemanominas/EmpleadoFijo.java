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
public class EmpleadoFijo extends Empleado{

        private float salario;

    /**
     *
     * @param dni
     * @param nombre
     * @param salario
     */
    public EmpleadoFijo(String dni, String nombre, float salario) throws DniException {
        super(dni, nombre);
        this.salario = salario;
        if(salario<0){
            throw new IllegalArgumentException("Error en el salario. Debe ser positivo");
        }
    }

        
    /**
     * Get the value of salario
     *
     * @return the value of salario
     */
    public float getSalario() {
        return salario;
    }

    /**
     * Set the value of salario
     *
     * @param salario new value of salario
     */
    public void setSalario(float salario) {
        this.salario = salario;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return super.toString()+", "+salario;
    }

    /**
     *
     * @return
     */
    @Override
    public float ingresos() {
        return salario;
    }
    
}
