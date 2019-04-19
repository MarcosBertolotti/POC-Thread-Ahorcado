package utn;

import java.util.List;
import java.util.Random;

public class Contenedor {

    private boolean turno = Boolean.TRUE;    // define si un jugador juega o se queda esperando a su turno.
    private boolean ganador = Boolean.FALSE; // define el ganador cuando se encuentran todas las letras.
    private int contAsertadas = 0;           // cantidad de letras asertadas, para comparar con la cantidad total de letras de palabra.
    private String palabra;
    private List<Character> abecedario;

    public Contenedor(String palabra,List<Character> abecedario){
        this.palabra = palabra.toLowerCase();
        this.abecedario = abecedario;
    }

    public synchronized int jugar(Jugador j,Random rd){

        while (!turno) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        turno = Boolean.FALSE;

        int encontrado = 1;
        char letra;

        while(encontrado > 0 && !ganador){

            letra = seleccionarLetra(rd);
            System.out.println(j.getNombre() + " selecciono la letra '" + letra + "'");

            encontrado = verificarLetra(letra);

            if(encontrado > 0)
                System.out.println("La letra '" + letra  + "' esta dentro de la palabra!, vuelve a jugar.");
            else
                System.out.println("La letra '" + letra  + "' no pertenece a la palabra.");

            verificarGanador();

            if(ganador) {
                j.setResultado(ganador);
                System.out.println("\nSe completo la palabra '" + palabra.toUpperCase() + "', " + "El ganador es: " + j.getNombre().toUpperCase());

                new ConexionBD().guardarResultadoBD(j.getNombre(),palabra);
            }

            System.out.println("**********************");
        }

        turno = Boolean.TRUE;  // si no encontro la palabra avisa al otro jugador para que pueda jugar su turno.
        notify();

        return encontrado;
    }

    //selecciona la letra aleatoreamente de la lista y la remueve.
    public char seleccionarLetra(Random rd){
        return abecedario.remove(rd.nextInt(abecedario.size()));
    }

    // verifica si la letra esta dentro de la palabra
    private int verificarLetra(char letra){
        String auxPalabra = palabra;
        int cont = 0;

        while (auxPalabra.indexOf(letra) > -1) { // si la letra esta varias veces en la palabra, las cuenta.
            auxPalabra = auxPalabra.substring(auxPalabra.indexOf(letra) + 1);
            cont++;
        }
        if(cont > 0)
            contAsertadas += cont;

        return cont;
    }

    // verifica si se encontraron todas las letras de la palabra.
    private void verificarGanador(){
        if(contAsertadas == palabra.length()) {
            ganador = Boolean.TRUE;
        }

    }

    public boolean getGanador() {
        return ganador;
    }

    public void setGanador(boolean ganador) {
        this.ganador = ganador;
    }
}
