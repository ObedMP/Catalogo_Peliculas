package sv.com.dm.peliculas.negocio;

public interface ICatalogoPelicula {
    String NOMBRE_RECURSO="peliculas.txt";
    
    void agregarPelicula(String nombrePelicula);
    
    void listarPelicula();
    
    void buscarPelicula(String buscar);
    
    void iniciarArchivo();
}
