/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.sistemanominas.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author daw1
 */
public class SistemaNominas {
    private List<Empleado> empleados;
    private EmpleadoDao empleadoDao;

    /**
     * Get the value of empleadoDao
     *
     * @return the value of empleadoDao
     */
    public EmpleadoDao getEmpleadoDao() {
        return empleadoDao;
    }

    /**
     * Set the value of empleadoDao
     *
     * @param empleadoDao new value of empleadoDao
     */
    public void setEmpleadoDao(EmpleadoDao empleadoDao) {
        this.empleadoDao = empleadoDao;
    }


    /**
     *
     */
    public SistemaNominas() {
        empleados=new ArrayList<>();
    }
    
    /**
     * Permite incluir un  Empleado en el sistema de nóminas
     * @param empleado El empleado que va a ser añadido
     * @return verdadero si el empleado ha sido añadido, falso en otro caso
     */
    public boolean incluirEmpleado(Empleado empleado){
        boolean incluido=false;
        
        if(!empleados.contains(empleado)){
            empleados.add(empleado);
            incluido=true;
        }    
        
        return incluido;
    }
    
    /**
     *
     * @param dni
     * @return
     */
    public Empleado getEmpleado(String dni){
        Empleado empleado=null;
        Iterator<Empleado> iterador=empleados.iterator();
        
        while(iterador.hasNext()){
            empleado=iterador.next();
            if (dni.equals(empleado.getDni().toString())){
                return empleado;
            }
        }
        
        return null;
    }
    
    /**
     *
     * @param empleado
     * @return
     */
    public boolean eliminarEmpleado(Empleado empleado){
        return empleados.remove(empleado);
    }
    
    /**
     *
     * @return
     */
    public List<Empleado> listarEmpleados(){
        
        Collections.sort(empleados);
        
        return empleados;
    }
    
    /**
     *
     * @return
     */
    public List<Empleado> listarEmpleadosPorSueldo(){
        
        Collections.sort(empleados, new ComparadorSueldo());
        
        
        return empleados;
    }
    
    /**
     *
     * @return
     */
    public float getTotalSalarios(){
        float acumulador=0;
        Iterator<Empleado> iterador=empleados.iterator();
        
        while(iterador.hasNext()){
            acumulador+=iterador.next().ingresos();
        }
        
        return acumulador;
    }
    
    public int guardarEmpleados() throws DaoException{
        if(empleadoDao!=null){
            return empleadoDao.insertar(empleados);
        }
        return 0;
    }
    
    public int cargarEmpleados() throws DaoException{
        int n=0;
        if(empleadoDao!=null){
            List<Empleado> listado=empleadoDao.listar();
            for(Empleado e: listado){
                if(this.incluirEmpleado(e)){
                    n++;
                }
            }
        }
        return n;
    }
}
