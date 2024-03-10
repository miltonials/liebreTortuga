package modelo;

import java.util.Random;

/**
 * Clase que modela a la liebre del cuento de la liebre y la tortuga. Siempre
 * dormirá antes de que termine la carrera.
 *
 * • Velocidad por defecto: 3
 *
 * @author Milton Barrera
 */
public class Liebre extends Thread {

    private int posicion;
    private int velocidad;
    private boolean tomarSiesta;
    private Object lock = new Object();

    public Liebre() {
        this.posicion = 0;
        this.velocidad = 3;
        this.tomarSiesta = true;
    }

    public int getPosicion() {
        return this.posicion;
    }

    /**
     * Cambia la velocidad por defecto de la liebre.
     * Velocidad mínima: 1
     * Velocidad máxima: 10
     *
     * @param velocidad
     */
    public void setVelocidad(int velocidad) {
        if (velocidad > 0 && velocidad <= 10) {
            synchronized (lock) {
                this.velocidad = velocidad;
            }
        }
    }
    
    public int getVelocidad() {
        return this.velocidad;
    }

    /**
     * Método que simula el avance de la liebre y cuando está entre el 70% y el 80%
     * de la carrera, esta se duerme 7 segundos.
     */
    @Override
    public void run() {
        while (posicion < utils.TAM_CARRERA) {
            synchronized (lock) {
                posicion += velocidad;
            }

            if ((posicion >= utils.TAM_CARRERA * 0.7
                    && posicion <= utils.TAM_CARRERA * 0.8
                    && tomarSiesta)) {
                utils.detenerTiempo(15000); // Upss! Se durmió la liebre 15segundos
                tomarSiesta = false;
                continue;
            }

            utils.detenerTiempo(); // se congela 2ms para que no avance demasiado rápido
        }
    }
}
