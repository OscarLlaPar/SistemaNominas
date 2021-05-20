/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.sistemanominas.controlador;

import com.sauces.sistemanominas.modelo.DaoException;
import com.sauces.sistemanominas.modelo.Dni;
import com.sauces.sistemanominas.modelo.DniException;
import com.sauces.sistemanominas.modelo.Empleado;
import com.sauces.sistemanominas.modelo.EmpleadoCsv;
import com.sauces.sistemanominas.modelo.EmpleadoDao;
import com.sauces.sistemanominas.modelo.EmpleadoEventual;
import com.sauces.sistemanominas.modelo.EmpleadoFijo;
import com.sauces.sistemanominas.modelo.EmpleadoJson;
import com.sauces.sistemanominas.modelo.EmpleadoObj;
import com.sauces.sistemanominas.modelo.EmpleadoXml;
import com.sauces.sistemanominas.modelo.SistemaNominas;
import com.sauces.sistemanominas.vista.Ventana;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author daw1
 */
public class Controlador {
    private Ventana vista;
    private SistemaNominas modelo;

    public Controlador(SistemaNominas modelo, Ventana vista) {
        this.modelo = modelo;
        this.vista = vista;
        
    }

    public Ventana getVista() {
        return vista;
    }

    public void setVista(Ventana vista) {
        this.vista = vista;
    }

    public SistemaNominas getModelo() {
        return modelo;
    }

    public void setModelo(SistemaNominas modelo) {
        this.modelo = modelo;
    }
    
    public void crearEmpleado(){
        try {
        Empleado e=null;
        String dni=vista.getDni();
        String nombre=vista.getNombre();
        String tipo=vista.getTipo();
        float salario=vista.getSalario();
        
        if(tipo.equals("EVENTUAL")){
            
                int horas=vista.getHoras();
                e=new EmpleadoEventual(dni,nombre,salario,horas);
        }    
        else{
            
                e=new EmpleadoFijo(dni,nombre,salario);
            
        }
        vista.mostrarIngresos(e.ingresos());
        if(modelo.incluirEmpleado(e)){
            vista.mostrarMensaje("Empleado incluido");
        }
        else{
            vista.mostrarMensaje("No se ha podido incluir el empleado");
        }
        } catch (DniException ex) {
                vista.mostrarMensaje(ex.getMessage());
        }
        
        
    }
    
    public void buscarEmpleado(){
        String dni=JOptionPane.showInputDialog("Introduzca DNI del empleado:");
        if (Dni.esValido(dni)){
            Empleado e=modelo.getEmpleado(dni);
            if(e!=null){
                vista.mostrarDni(e.getDni().toString());
                vista.mostrarNombre(e.getNombre());
                vista.mostrarIngresos(e.ingresos());
                if(e instanceof EmpleadoFijo){
                    vista.mostrarTipo("FIJO");
                    vista.mostrarSalario(((EmpleadoFijo) e).getSalario());
                }
                else if(e instanceof EmpleadoEventual){
                    vista.mostrarTipo("EVENTUAL");
                    vista.mostrarSalario(((EmpleadoEventual) e).getSalarioHora());
                    vista.mostrarHoras(((EmpleadoEventual) e).getHoras());
                }
            }
        }
        else{
            vista.mostrarMensaje("Dni no válido");
        }
    }
    
    public void eliminarEmpleado(){
        String dni=vista.getDni();
        Empleado empleado=modelo.getEmpleado(dni);
        if (empleado!=null){
            if(modelo.eliminarEmpleado(empleado)){
                vista.limpiarCampos();
                JOptionPane.showMessageDialog(vista, "Empleado eliminado");
            }
            else{
                JOptionPane.showMessageDialog(vista, "No se ha podido eliminar el empleado");
            }
        }
        else{
            vista.mostrarMensaje("No existe el empleado");
        }
        vista.actualizarTabla();
    }
    
    public void modificarEmpleado(){
        try{
        String dni=vista.getDni();
        Empleado empleado=modelo.getEmpleado(dni);
        String nombre=vista.getNombre();
        float salario=vista.getSalario();
        
        
            
        empleado.setNombre(nombre);
        
        if(empleado instanceof EmpleadoEventual){
            int horas=vista.getHoras();
            ((EmpleadoEventual) empleado).setSalarioHora(salario);
            ((EmpleadoEventual) empleado).setHoras(horas);

        }
        if(empleado instanceof EmpleadoFijo){
            ((EmpleadoFijo) empleado).setSalario(salario);
        }
        vista.mostrarIngresos(empleado.ingresos());
        this.listarEmpleados();
        } catch (NumberFormatException nfe){
                vista.mostrarMensaje("Error en los datos introducidos");
        }
    }
    
    public void listarEmpleados(){
        List<Empleado> listado=null;
        switch(vista.getOrden().toUpperCase()){
            case "DNI":     listado=modelo.listarEmpleados();
                break;
            case "NOMBRE":  listado=modelo.listarEmpleados();
                            Collections.sort(listado, new Comparator<Empleado>(){
                                @Override
                                public int compare(Empleado e1, Empleado e2) {
                                    return e1.getNombre().compareTo(e2.getNombre());
                                }
                                
                            });
                break;
            case "INGRESOS":listado=modelo.listarEmpleadosPorSueldo();
                break;
        }
        if(listado!=null){
        vista.mostrarEmpleados(listado);
        vista.actualizarTabla();
        }
    }
    
    public void guardarEmpleados(){
        String nombreFichero;
        int posicionPunto;
        String extension="";
        EmpleadoDao empleadoDao=null;
        int n;
        try {
            
            nombreFichero=vista.getArchivo();
            
            posicionPunto=nombreFichero.lastIndexOf('.');
            if(posicionPunto!=-1){
                extension=nombreFichero.substring(posicionPunto);
            }
            switch(extension){
                case ".csv": empleadoDao=new EmpleadoCsv(nombreFichero);
                    break;
                case ".obj": empleadoDao=new EmpleadoObj(nombreFichero);
                    break;
                case ".json": empleadoDao=new EmpleadoJson(nombreFichero);
                    break;
                case ".xml": empleadoDao=new EmpleadoXml(nombreFichero);
                    break;
                default: System.out.println("Formato incorrecto");
            }
            modelo.setEmpleadoDao(empleadoDao);
            n=modelo.guardarEmpleados();
            vista.mostrarMensaje("Se han guardado "+n+" empleados");
        } catch (DaoException ex) {
            vista.mostrarMensaje("Error en el fichero");
        }
    }
    
    public void cargarEmpleados(){
        String nombreFichero;
        int posicionPunto;
        String extension="";
        EmpleadoDao empleadoDao=null;
        int n;
        try {
            
            nombreFichero=vista.getArchivo();
            
            posicionPunto=nombreFichero.lastIndexOf('.');
            if(posicionPunto!=-1){
                extension=nombreFichero.substring(posicionPunto);
            }
            switch(extension){
                case ".csv": empleadoDao=new EmpleadoCsv(nombreFichero);
                    break;
                case ".obj": empleadoDao=new EmpleadoObj(nombreFichero);
                    break;
                case ".json": empleadoDao=new EmpleadoJson(nombreFichero);
                    break;
                case ".xml": empleadoDao=new EmpleadoXml(nombreFichero);
                    break;
            }
            
            modelo.setEmpleadoDao(empleadoDao);
            n=modelo.cargarEmpleados();
            vista.mostrarMensaje("Se han cargado "+n+" empleados");
            vista.mostrarEmpleados(modelo.listarEmpleados());
        } catch (DaoException ex) {
            vista.mostrarMensaje("Error en el fichero");
        }
    }
    
    public void iniciar(){
        vista.setVisible(true);
    }
}
