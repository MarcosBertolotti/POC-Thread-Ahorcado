package utn;

import java.util.List;
import java.util.Random;

public class Contenedor {

    private boolean turno = Boolean.TRUE;    // define si un jugador juega o se queda esperando a su turno.
    private boolean ganador = Boolean.FALSE; // define el ganador cuando se encuentran todas las letras.
    private int contAsertadas = 0;           // cantidad de letras asertadas, para comparar con la cantidad total de letras de palabra.
    private String palabra;
    private List<Character> abecedario;
    private StringBuffer stringBuffer;

    public Contenedor(String palabra,List<Character> abecedario, StringBuffer stringBuffer){
        this.palabra = palabra.toLowerCase();
        this.abecedario = abecedario;
        this.stringBuffer = stringBuffer;
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
            System.out.println(j.getNombre().toUpperCase() + " selecciono la letra '" + letra + "'");

            encontrado = buscarLetra(letra);

            verificarGanador();

            if(ganador){
                j.setResultado(ganador);
                System.out.println("La letra '" + letra + "' esta dentro de la palabra!");
                System.out.println("\nSe completo la palabra '" + palabra.toUpperCase() + "', " + "El ganador es: " + j.getNombre().toUpperCase() + "!");

                new ConexionBD().guardarResultadoBD(j.getNombre(),palabra);
            }
            else if(encontrado > 0) {
                System.out.println("La letra '" + letra + "' esta dentro de la palabra!, vuelve a jugar " + j.getNombre().toUpperCase());
                System.out.println("Encontrado: " + stringBuffer.toString().toUpperCase());
            }
            else
                System.out.println("La letra '" + letra  + "' no pertenece a la palabra.");

            System.out.println("**********************");
        }

        turno = Boolean.TRUE;  // si no encontro la palabra avisa al otro jugador para que pueda jugar su turno.
        notify();

        return encontrado;
    }


    // selecciona la letra aleatoreamente de la lista y la remueve.
    public char seleccionarLetra(Random rd){
        return abecedario.remove(rd.nextInt(abecedario.size()));
    }


    // método para calcular el número de veces que se repite un carácter en un String
    public int buscarLetra(char letra) {
        int cont = 0;
        int index = palabra.indexOf(letra); // retorna la posicion si lo encuentra, si no -1

        while (index != -1) {
            agregarLetraEncontrada(index,letra);

            index = palabra.indexOf(letra, index + 1); //se sigue buscando a partir de la posición siguiente a la encontrada

            cont++;
            contAsertadas++;
        }
        return cont;
    }

    public void agregarLetraEncontrada(int index, char letra){

        stringBuffer.replace(index,index+1, String.valueOf(letra));
    }

    // verifica si se encontraron todas las letras de la palabra.
    private void verificarGanador(){
        if(contAsertadas == palabra.length())
            ganador = Boolean.TRUE;
    }

    public boolean getGanador() {
        return ganador;
    }

    public void setGanador(boolean ganador) {
        this.ganador = ganador;
    }
}
