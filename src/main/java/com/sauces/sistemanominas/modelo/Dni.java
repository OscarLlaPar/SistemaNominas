/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.sistemanominas.modelo;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author daw1
 */
public class Dni implements Comparable<Dni>, Serializable {
    private String dni;
    
    public Dni(String dni) throws DniException{
        if(!Dni.esValido(dni)){
            throw new DniException("DNI incorrecto");
        }
        this.dni=dni;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return dni;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.dni);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dni other = (Dni) obj;
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        return true;
    }
    
    public static boolean esValido(String dni){
        boolean esValido=false;
        int resto;
        String letras="TRWAGMYFPDXBNJZSQVHLCKE";
        char letra;
        String er="([0-9]{8})([A-Z])";
        Pattern p=Pattern.compile(er);
        Matcher m=p.matcher(dni);
        if(m.matches()){
            resto=Integer.parseInt(m.group(1))%23;
            letra=letras.charAt(resto);
            if(letra==dni.charAt(8)){
                esValido=true;
            }
        }
        return esValido;
    }

    @Override
    public int compareTo(Dni dni) {
        return this.dni.compareTo(dni.dni);
    }
}
