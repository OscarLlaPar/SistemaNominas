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

    public EmpleadoFijo(String dni, String nombre, float salario) {
        super(dni, nombre);
        this.salario = salario;
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

    @Override
    public String toString() {
        return super.toString()+", "+salario;
    }

   
    
    
    @Override
    public float ingresos() {
        return salario;
    }
    
}
