package sv.com.dm.peliculas.negocio;

import java.util.logging.Level;
import java.util.logging.Logger;
import sv.com.dm.peliculas.datos.AccesoDatosImpl;
import sv.com.dm.peliculas.datos.IAccesoDatos;
import sv.com.dm.peliculas.domain.Pelicula;
import sv.com.dm.peliculas.excepciones.AccesoDatosEx;
import sv.com.dm.peliculas.excepciones.LecturaDatosEx;


public abstract class CatalogoPeliculasImpl implements ICatalogoPelicula{
    
    private final IAccesoDatos datos;
    
    public CatalogoPeliculasImpl(){
        this.datos=new AccesoDatosImpl();
    }
    
    @Override
    public void agregarPelicula(String nombrePelicula){
        //estamos recibiendo variable nombre pelicula y luego la convertimos a una variable
        //tipo objeto pelicula
        Pelicula pelicula=new Pelicula(nombrePelicula);
        boolean anexar=false;
        try {
            anexar=datos.existe(NOMBRE_RECURSO);
            datos.escribir(pelicula, NOMBRE_RECURSO, anexar);
                    
                    } catch (AccesoDatosEx ex) {
                        System.out.println("Error de acesso a datos");
                        ex.printStackTrace();
        }
        
    }

    @Override
    public void listarPelicula() {
        try {
            var peliculas=this.datos.listar(NOMBRE_RECURSO);
            for(var pelicula:peliculas){
                System.out.println("pelicula = " + pelicula);
            }
        } catch (AccesoDatosEx ex) {
            System.out.println("Error de acceso datos");
            ex.printStackTrace();
        }
    }

    @Override
    public  void buscarPelicula( String buscar){
        String resultado=null;
        try {
            resultado=this.datos.buscar(NOMBRE_RECURSO, buscar);
        } catch (LecturaDatosEx ex) {
            System.out.println("Error de acceso datos");
            ex.printStackTrace(System.out);
        }
        System.out.println("resultado = " + resultado);
        
    }

    @Override
    public void iniciarArchivo() {
        try {
            if(this.datos.existe(NOMBRE_RECURSO)){
                datos.borrar(NOMBRE_RECURSO);
                datos.crear(NOMBRE_RECURSO);
            }else{
                datos.crear(NOMBRE_RECURSO);
            }
        } catch (AccesoDatosEx ex) {
            System.out.println("Error al iniciar catalogo peliculas");
            ex.printStackTrace(System.out);
        }
    }
    
    
    
}
