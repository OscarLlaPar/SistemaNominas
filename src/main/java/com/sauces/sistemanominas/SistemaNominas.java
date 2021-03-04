/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.sistemanominas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author daw1
 */
public class SistemaNominas {
    private List<Empleado> empleados;

    public SistemaNominas() {
        empleados=new ArrayList<>();
    }
    
    public boolean incluirEmpleado(Empleado empleado){
        boolean incluido=false;
        
        if(!empleados.contains(empleado)){
            empleados.add(empleado);
            incluido=true;
        }    
        
        return incluido;
    }
    
    public Empleado getEmpleado(String dni){
        Empleado empleado=null;
        Iterator<Empleado> iterador=empleados.iterator();
        
        while(iterador.hasNext()){
            empleado=iterador.next();
            if (empleado.getDni().equals(dni)){
                return empleado;
            }
        }
        
        return null;
    }
    
    public boolean eliminarEmpleado(Empleado empleado){
        return empleados.remove(empleado);
    }
    
    public List<Empleado> listarEmpleados(){
        List<Empleado> listado=new ArrayList<>();
        
        Collections.copy(empleados, listado);
        Collections.sort(listado);
        
        return listado;
    }
    
    public List<Empleado> listarEmpleadosPorSueldo(){
        List<Empleado> listado=new ArrayList<>();
        
        Collections.copy(empleados, listado);
        Collections.sort(listado, new ComparadorSueldo());
        
        
        return listado;
    }
    
    public float getTotalSalarios(){
        float acumulador=0;
        Iterator<Empleado> iterador=empleados.iterator();
        
        while(iterador.hasNext()){
            acumulador+=iterador.next().ingresos();
        }
        
        return acumulador;
    }
}
