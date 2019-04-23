package utn;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String args[]){

        ConexionBD conexion = new ConexionBD();
        List<Character> abecedario = new LinkedList<Character>();
        String palabra;
        StringBuffer stringBuffer = new StringBuffer(); // Contendra Guiones Bajos, luego se remplazan por letras a medica que se encuentren.

        conexion.crearBD();
        abecedario = addLetras(abecedario);
        palabra = conexion.getPalabraBD();
        stringBuffer = addGuionesBajosToStringBuffer(stringBuffer,palabra);

        Contenedor contenedor = new Contenedor(palabra,abecedario,stringBuffer);

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

    // agrega guiones bajos segun el largo de la palabra.
    public static StringBuffer addGuionesBajosToStringBuffer(StringBuffer stringBuffer, String palabra){

        for(int i = 0; i < palabra.length(); i++)
            stringBuffer.append("_");

        return stringBuffer;
    }


}
