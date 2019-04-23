package utn;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Jugador implements Runnable{

    private String nombre;
    private Contenedor contenedor;
    private int vida = 10; // limite de errores
    private boolean resultado = Boolean.FALSE; // si es true gano la partida

    public Jugador(String nombre, Contenedor contenedor){
        this.nombre = nombre;
        this.contenedor = contenedor;
    }

    public void run(){
        Random rd = new Random();
        int errores = 0;

        do{
             if(contenedor.jugar(this,rd) == 0)
                 errores++;

            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }while(!contenedor.getGanador() && errores < vida);

        if(errores == vida)
            System.out.println("El jugador " + nombre.toUpperCase() + " agoto todas sus vidas!, ya no puede continuar");

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public Contenedor getContenedor() {
        return contenedor;
    }

    public void setContenedor(Contenedor contenedor) {
        this.contenedor = contenedor;
    }

    public boolean getResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", vida=" + vida +
                '}';
    }
}
