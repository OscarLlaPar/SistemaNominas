/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.sistemanominas;

import java.util.Scanner;

/**
 *
 * @author daw1
 */
public class AppSistemaNominas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DaoException{
        SistemaNominas sn=new SistemaNominas();
        Scanner teclado=new Scanner(System.in);
        int opcion;
        String dni, nombre, nombreArchivo, extension="";
        float salario, salarioHora;
        int horas;
        Empleado e=null;
        EmpleadoDao empleadoDao=null;
        int n;
        int posicionPunto;
        do{
            System.out.println("1.- Crear empleado fijo");
            System.out.println("2.- Crear empleado eventual");
            System.out.println("3.- Consultar empleado");
            System.out.println("4.- Eliminar empleado");
            System.out.println("5.- Listar empleados");
            System.out.println("6.- Listar empleados por sueldo");
            System.out.println("7.- Consultar total salarios");
            System.out.println("8.- Guardar empleados");
            System.out.println("9.- Cargar empleados");
            System.out.println("0.- Salir");
            System.out.println("Seleccione una opcion: ");
            opcion=teclado.nextInt();
            teclado.nextLine();
            switch(opcion){
                case 1: try{
                        System.out.println("Introduzca DNI: ");
                        dni=teclado.nextLine();
                        System.out.println("Introduzca nombre: ");
                        nombre=teclado.nextLine();
                        System.out.println("Introduzca salario: ");
                        salario=teclado.nextFloat();
                        teclado.nextLine();
                        e=new EmpleadoFijo(dni,nombre,salario);
                        }catch(DniException de){
                            System.out.println("Error");
                        }
                        if(sn.incluirEmpleado(e)){
                            System.out.println("Empleado incluido en el sistema");
                        }
                        else{
                            System.out.println("No se ha podido incluir al empleado en el sistema");
                        }
                        break;
                case 2: try{
                        System.out.println("Introduzca DNI: ");
                        dni=teclado.nextLine();
                        
                        System.out.println("Introduzca nombre: ");
                        nombre=teclado.nextLine();
                        System.out.println("Introduzca salario/hora: ");
                        salarioHora=teclado.nextFloat();
                        System.out.println("Introduzca horas: ");
                        horas=teclado.nextInt();
                        teclado.nextLine();
                        e=new EmpleadoEventual(dni,nombre,salarioHora,horas);
                        }catch(DniException de){
                            System.out.println("Error");
                        }
                        if(sn.incluirEmpleado(e)){
                            System.out.println("Empleado incluido en el sistema");
                        }
                        else{
                            System.out.println("No se ha podido incluir al empleado en el sistema");
                        }
                        break;
                case 3: System.out.println("Introduzca DNI: ");
                        dni=teclado.nextLine();
                        e=sn.getEmpleado(dni.toString());
                        if(e!=null){
                            System.out.println(e);
                        }
                        else{
                            System.out.println("No existe un empleado con ese DNI");
                        }
                        break;
                case 4: System.out.println("Introduzca DNI del empleado a eliminar: ");
                        dni=teclado.nextLine();
                        e=sn.getEmpleado(dni.toString());
                        if(e!=null){
                            sn.eliminarEmpleado(e);
                            System.out.println("Empleado eliminado");
                        }
                        else{
                            System.out.println("No existe un empleado con ese DNI");
                        }
                        break;
                case 5: for(Empleado empleado: sn.listarEmpleados()){
                            System.out.println(empleado);
                        }
                        break;
                case 6: for(Empleado empleado: sn.listarEmpleadosPorSueldo()){
                            System.out.println(empleado);
                        }
                        break;
                case 7: System.out.println("Total: "+sn.getTotalSalarios());
                        break;
                        /*System.out.println("Listado de empleados fijos");
                        for(Empleado empleado: sn.listarEmpleados()){
                            if(empleado instanceof EmpleadoFijo){
                                System.out.println(empleado);
                            }
                        }*/
                case 8: System.out.println("Introduzca nombre del archivo: ");
                        nombreArchivo=teclado.nextLine();
                        posicionPunto=nombreArchivo.lastIndexOf('.');
                        if(posicionPunto!=-1){
                            extension=nombreArchivo.substring(posicionPunto);
                        }
                        switch(extension){
                            case ".csv": empleadoDao=new EmpleadoCsv(nombreArchivo);
                                break;
                            case ".obj": empleadoDao=new EmpleadoObj(nombreArchivo);
                                break;
                            case ".json": empleadoDao=new EmpleadoJson(nombreArchivo);
                                break;
                            case ".xml": empleadoDao=new EmpleadoXml(nombreArchivo);
                                break;
                            default: System.out.println("Formato incorrecto");
                        }
                        if(empleadoDao!=null){
                            try{
                                sn.setEmpleadoDao(empleadoDao);
                                n=sn.guardarEmpleados();
                            } catch (DaoException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                        break;
                case 9: System.out.println("Introduzca nombre del archivo: ");
                        nombreArchivo=teclado.nextLine();
                        posicionPunto=nombreArchivo.lastIndexOf('.');
                        if(posicionPunto!=-1){
                            extension=nombreArchivo.substring(posicionPunto);
                        }
                        switch(extension){
                            case ".csv": empleadoDao=new EmpleadoCsv(nombreArchivo);
                                break;
                            case ".obj": empleadoDao=new EmpleadoObj(nombreArchivo);
                                break;
                            case ".json": empleadoDao=new EmpleadoJson(nombreArchivo);
                                break;
                            case ".xml": empleadoDao=new EmpleadoXml(nombreArchivo);
                                break;
                            default: System.out.println("Formato incorrecto");
                        }
                        if(empleadoDao!=null){
                            try{
                                sn.setEmpleadoDao(empleadoDao);
                                n=sn.cargarEmpleados();
                            } catch (DaoException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                        break;
                case 0: System.out.println("Hasta luego");
                        break;
                default: System.out.println("Error en la seleccion");
            }
        }while(opcion!=0);
    }
    
}
