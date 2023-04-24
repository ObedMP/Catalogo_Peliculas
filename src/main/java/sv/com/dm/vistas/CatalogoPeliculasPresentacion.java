package sv.com.dm.vistas;

import java.util.Scanner;
import sv.com.dm.peliculas.negocio.*;

public class CatalogoPeliculasPresentacion {
    public static void main(String[] args) {
        var opcion = -1;
        var scanner= new Scanner(System.in);
        ICatalogoPelicula catalogo= new CatalogoPeliculasImpl() {};
        
        while(opcion != 0){
            System.out.println("Elije una opcion: \n"
            + "1.-Iniciar Catalogo Peliculas\n"
            + "2.-Agregar pelicula\n"
            + "3.-Listar Peliculas\n"
            + "4.-Buscar Pelicula \n"
            + "0.-Salir"
            );
            opcion = Integer.parseInt(scanner.nextLine());
            
            switch(opcion){
                case 1:
                        catalogo.iniciarArchivo();
                        break;
                case 2:
                        System.out.println("Introduce el nombre de la pelicula");
                        var nombre = scanner.nextLine();
                        catalogo.agregarPelicula(nombre);
                        break;
                case 3: 
                        catalogo.listarPelicula();
                        break;
                case 4: 
                        System.out.println("Introduce un pelicula a buscar");
                        var buscar = scanner.nextLine();
                        catalogo.buscarPelicula(buscar);
                        break;
                case 0:
                    System.out.println("HAsta Pronto");
                    break;
                default: 
                        System.out.println("Opcion no reconocida");
                
            }
        }
    }
}
