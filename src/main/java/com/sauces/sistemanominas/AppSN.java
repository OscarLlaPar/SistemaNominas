/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.sistemanominas;

import com.sauces.sistemanominas.controlador.Controlador;
import com.sauces.sistemanominas.modelo.SistemaNominas;
import com.sauces.sistemanominas.vista.Ventana;

/**
 *
 * @author daw1
 */
public class AppSN {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SistemaNominas modelo=new SistemaNominas();
        Ventana vista=new Ventana();
        Controlador controlador= new Controlador(modelo, vista);
        vista.setControlador(controlador);
        controlador.iniciar();
    }
    
}
