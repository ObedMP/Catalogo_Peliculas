package sv.com.dm.peliculas.datos;

import java.io.*;
import java.io.File;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.com.dm.peliculas.domain.*;
import sv.com.dm.peliculas.excepciones.AccesoDatosEx;
import sv.com.dm.peliculas.excepciones.EscrituraDatosEx;
import sv.com.dm.peliculas.excepciones.LecturaDatosEx;

public  class AccesoDatosImpl implements IAccesoDatos {

    @Override
    public boolean existe(String nombreArchivo) throws AccesoDatosEx {
        File Archivo = new File(nombreArchivo);
        return Archivo.exists();
        
    }

    @Override
    public List<Pelicula> listar(String nombreArchivo) throws LecturaDatosEx {
        var Archivo = new File(nombreArchivo);
        List<Pelicula> peliculas= new ArrayList<>();
        try {
            var entrada = new BufferedReader (new FileReader(Archivo));
            //variable que almacena cada una de las lineas
            String linea= null;
            linea = entrada.readLine();
            while(linea != null){
                //aqui se regresa  una lista de objetos tipo pelicula
                var pelicula = new Pelicula(linea);
                peliculas.add(pelicula);
                linea = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Excepcion al listar peliculas"+ex.getMessage());
        } catch (IOException ex) {
            throw new LecturaDatosEx("Excepcion al listar peliculas"+ex.getMessage());
        }
        return peliculas;
    }

    @Override
    public void escribir(Pelicula pelicula, String nombreArchivo, boolean anexar) throws EscrituraDatosEx {
        var archivo = new File (nombreArchivo);
        try {
            var salida = new PrintWriter(new FileWriter(archivo,anexar));
            salida.println(pelicula.toString());
            salida.close();
            System.out.println("Se ha escrito infromacion al archivo"+ pelicula );
        } catch (IOException ex) {
           throw new EscrituraDatosEx("Excepcion al escribir peliculas"+ex.getMessage());
        }
    }

    @Override
    public String buscar(String nombreArchivo, String buscar) throws LecturaDatosEx {
        var Archivo =new File(nombreArchivo);
        String resultado=null;
        try {
            var entrada =new BufferedReader(new FileReader(Archivo));
            String linea=null;
            linea =entrada.readLine();
            int indice=1;
            while(linea != null){
                if (buscar != null && buscar.equals(linea)){
                   resultado = "Pelicula"+ linea + "encontrada en el indice "+indice;
                   break;
                }
                linea=entrada.readLine();
                indice++;
            }
        } catch (FileNotFoundException ex) {
            throw new LecturaDatosEx("Excepcion al buscar peliculas"+ex.getMessage());
        } catch (IOException ex) {
            throw new LecturaDatosEx("Excepcion al buscar peliculas"+ex.getMessage());
        }
        return resultado;
    }

    @Override
    public void crear(String nombreArchivo) throws AccesoDatosEx {
        var archivo =new File(nombreArchivo);
        try{
        var salida =new PrintWriter(new FileWriter(archivo));
        salida.close();
            System.out.println("Se ha creado el archivo");
        } catch (IOException ex){
            ex.printStackTrace();
            throw new AccesoDatosEx("Excepcion al crear archivo"+ex.getMessage());
        }
    }

    @Override
    public void borrar(String nombreArchivo) throws AccesoDatosEx {
        var archivo = new File(nombreArchivo);
        if (archivo.exists())
            archivo.delete();
        System.out.println("Se ha borrado archivo");
    }

    
    
    
    
}
