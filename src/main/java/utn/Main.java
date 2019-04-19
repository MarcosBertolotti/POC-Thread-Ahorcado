package utn;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String args[]){

        ConexionBD conexion = new ConexionBD();
        conexion.crearBD();

        List<Character> abecedario = new LinkedList<Character>();
        abecedario = addLetras(abecedario);

        String palabra = conexion.getPalabraBD();
        Contenedor contenedor = new Contenedor(palabra,abecedario);

        System.out.println("PALABRA SELECCIONADA: " + palabra.toUpperCase() + "\n");

        Thread j1 = new Thread(new Jugador("Marcos",contenedor));
        Thread j2 = new Thread(new Jugador("Fabian",contenedor));

        j1.start();
        j2.start();
    }

    //agrega todas las letras al abecedario
    public static List<Character> addLetras(List<Character> abecedario){
        for(int i = 0; ('a' + i) <= 'z' ; i++)
            abecedario.add((char) ('a' + i));

        return abecedario;
    }



}
