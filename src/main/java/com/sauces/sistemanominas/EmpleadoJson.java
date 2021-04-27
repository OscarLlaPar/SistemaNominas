/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.sistemanominas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daw1
 */
public class EmpleadoJson implements EmpleadoDao {
    private Path path;

    public EmpleadoJson(String path){
        this.path=Paths.get(path);
    }
    
    @Override
    public List<Empleado> listar() throws DaoException {
        List<Empleado> listado=new ArrayList<>();
        //Definir tipo para poder trabajar con colecciones
        java.lang.reflect.Type tipo = new TypeToken<List<Empleado>>(){}.getType();
        //Registrar clases hijas
        RuntimeTypeAdapterFactory<Empleado> empleadoAdapter = RuntimeTypeAdapterFactory.of(Empleado.class, "type");
        empleadoAdapter.registerSubtype(EmpleadoFijo.class, "EmpleadoFijo");
        empleadoAdapter.registerSubtype(EmpleadoEventual.class, "EmpleadoEventual");
        //Crear objeto GsonBuilder y configurarlo
        GsonBuilder builder=new GsonBuilder();
        builder.setPrettyPrinting();
        builder.registerTypeAdapterFactory(empleadoAdapter);
        
        Gson gson=builder.create();
        
        try(BufferedReader fichero=Files.newBufferedReader(path)){
        
            listado=gson.fromJson(fichero, tipo);
            
        } catch (NoSuchFileException nsfe){
            throw new DaoException("Error en el nombre del fichero");
        } catch (JsonParseException jpe) {
            throw new DaoException("Formato de datos incorrecto");
        } catch (IOException ex) {
            throw new DaoException("Error de E/S");
        }
        
        return listado;
    }

    @Override
    public int insertar(List<Empleado> listado) throws DaoException {
        int n=0;
        //Definir tipo para poder trabajar con colecciones
        java.lang.reflect.Type tipo = new TypeToken<List<Empleado>>(){}.getType();
        //Registrar clases hijas
        RuntimeTypeAdapterFactory<Empleado> empleadoAdapter = RuntimeTypeAdapterFactory.of(Empleado.class, "type");
        empleadoAdapter.registerSubtype(EmpleadoFijo.class, "EmpleadoFijo");
        empleadoAdapter.registerSubtype(EmpleadoEventual.class, "EmpleadoEventual");
        //Crear objeto GsonBuilder y configurarlo
        GsonBuilder builder=new GsonBuilder();
        builder.setPrettyPrinting();
        builder.registerTypeAdapterFactory(empleadoAdapter);
        
        Gson gson=builder.create();
        
        try(BufferedWriter fichero=Files.newBufferedWriter(path)){
            gson.toJson(listado, tipo, fichero);
            n=listado.size();
        } catch (NoSuchFileException nsfe){
            throw new DaoException("Error en el nombre del fichero");
        } catch (IOException ex) {
            throw new DaoException("Error de E/S");
        }
        
        return n;
    }
    
    
}
