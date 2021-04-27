/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.sistemanominas;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
public class EmpleadoObj implements EmpleadoDao {
    private Path path;

    public EmpleadoObj(String path){
        this.path=Paths.get(path);
    }
    
    @Override
    public List<Empleado> listar() throws DaoException {
        List<Empleado> listado=new ArrayList<>();
        Empleado e=null;
        try(InputStream is=Files.newInputStream(path);
            ObjectInputStream fichero=new ObjectInputStream(is);){
            while(is.available()>0){
                e=(Empleado)fichero.readObject();
                listado.add(e);
            }
        } catch (NoSuchFileException nsfe){
            throw new DaoException("Error en el nombre del fichero");
        } catch (IOException ex) {
            throw new DaoException("Error de E/S");
        } catch (ClassNotFoundException ex) {
            throw new DaoException("Error en la clase");
        }
        
        return listado;
    }

    @Override
    public int insertar(List<Empleado> listado) throws DaoException {
       int n=0;
        try(ObjectOutputStream fichero=new ObjectOutputStream(Files.newOutputStream(path))){
           
           for(Empleado e: listado){
            fichero.writeObject(e);
            n++;
           }
        } catch (NoSuchFileException nsfe){
            throw new DaoException("Error en el nombre del fichero");
        } catch (IOException ex) {
            throw new DaoException ("Error de E/S");
        }
       return n;
    }
}
