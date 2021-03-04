/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.sistemanominas;

import java.util.Objects;

/**
 *
 * @author daw1
 */
public abstract class Empleado implements Comparable<Empleado>{
    
    private String dni;

    private String nombre;

    /**
     *
     * @param dni
     * @param nombre
     */
    public Empleado(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    
    /**
     * Get the value of nombre
     *
     * @return the value of nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the value of nombre
     *
     * @param nombre new value of nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Get the value of dni
     *
     * @return the value of dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * Set the value of dni
     *
     * @param dni new value of dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return dni + ", " + nombre;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.dni);
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        
        final Empleado other = (Empleado) obj;
        if (!this.dni.equals(other.dni)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Empleado o) {
        return this.dni.compareTo(o.dni);
    }

    /**
     *
     * @return
     */
    public abstract float ingresos();
}
