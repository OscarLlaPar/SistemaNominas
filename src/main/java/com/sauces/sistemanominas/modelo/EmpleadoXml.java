/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.sistemanominas.modelo;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.StreamException;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daw1
 */
public class EmpleadoXml implements EmpleadoDao {
    private Path path;

    public EmpleadoXml(String path){
        this.path=Paths.get(path);
    }
    
    @Override
    public List<Empleado> listar() throws DaoException {
        List<Empleado> listado=new ArrayList<>();
        
        XStream xstream=new XStream(new DomDriver());
        
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypeHierarchy(EmpleadoFijo.class);
        xstream.allowTypeHierarchy(EmpleadoEventual.class);
        
        try(BufferedReader fichero=Files.newBufferedReader(path)){
            listado=(List<Empleado>)xstream.fromXML(fichero);
        }catch (NoSuchFileException nsfe){
            throw new DaoException("Error en el nombre del fichero");
        }catch(StreamException se){
            throw new DaoException("Formato de datos incorrecto");
        }catch (IOException ex) {
            throw new DaoException("Error de E/S");
        }
        
        return listado;
    }

    @Override
    public int insertar(List<Empleado> listado) throws DaoException {
        int n=0;
        
        XStream xstream=new XStream(new DomDriver());
        
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypeHierarchy(EmpleadoFijo.class);
        xstream.allowTypeHierarchy(EmpleadoEventual.class);
        
        try(BufferedWriter fichero=Files.newBufferedWriter(path)){
            xstream.toXML(listado,fichero);
            n=listado.size();
        } catch (NoSuchFileException nsfe){
            throw new DaoException("Error en el nombre del fichero");
        } catch (IOException ex) {
            throw new DaoException("Error de E/S");
        }
        
        return n;
    }
}
