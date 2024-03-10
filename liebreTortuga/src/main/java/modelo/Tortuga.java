package modelo;

/**
 * Clase que modela a la tortuga del cuento de la liebre y la tortuga.
 * 
 * • Velocidad por defecto: 1
 * 
 * @author Milton Barrera
 */
public class Tortuga extends Thread {

    private int posicion;
    private int velocidad;
    private Object lock = new Object();

    public Tortuga() {
        this.posicion = 0;
        this.velocidad = 1;
    }

    public int getPosicion() {
        return this.posicion;
    }

    public int getVelocidad() {
        return this.velocidad;
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
            utils.detenerTiempo();
        }
    }
}
