import java.rmi.*;
import java.rmi.registry .*;
import java.sql.Ref;
import java.util.Scanner;

public class cLibros {
    public static void main ( String [] args )
    throws Exception
    {
        Scanner s = new Scanner(System.in);
        String titulo, autor, editorial;
        int publicacion;
        iLibros RefObRemoto = (iLibros)Naming.lookup("//localhost/servidor");
        Libro l = new Libro();
        int n = 0;

        
        System.out.println("Introduce Libro: ");
        System.out.println("Introduce Titulo: ");   
        titulo = s.nextLine();
        System.out.println("Introduce Autor: ");
        autor = s.nextLine();
        System.out.println("Introduce Editorial: ");
        editorial = s.nextLine();
        System.out.println("Introduce Publicacion: ");
        publicacion = s.nextInt();

        
        l = new Libro(titulo,autor,editorial,publicacion);
        RefObRemoto.addLibro(l);
    

        System.out.println(RefObRemoto.consultar("sombras", "ale"));

        l = RefObRemoto.extraer("sombras", "ale");


    }
}