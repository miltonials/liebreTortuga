package modelo;

import java.util.Scanner;

/**
 * Clase que modela el bosque en el que corrieron la liebre y la tortuga.
 *
 * @author Milton Barrera
 */
public class Bosque extends Thread {

    private Liebre liebre;
    private Tortuga tortuga;
    private String infoCarrera;
    private String msgVelocidades;
    private boolean hayGanador;
    private Object lock = new Object();
    private Scanner es;
    private boolean seCambioVelocidadTortuga;

    public Bosque() {
        liebre = new Liebre();
        tortuga = new Tortuga();
        infoCarrera = "";
        msgVelocidades = "";
        hayGanador = false;
        seCambioVelocidadTortuga = false;
        es = new Scanner(System.in);
    }

    

    /**
     * Método que permite actualizar el mensaje al final de la carrera para
     * saber si ganó la liebre, la tortuga o hubo un empate.
     */
    private void actualizarInfoCarrera() {
        if (hayGanador) {
            return;
        } else if (liebre.getPosicion() >= utils.TAM_CARRERA && tortuga.getPosicion() < liebre.getPosicion()) {
            infoCarrera = "La liebre ganó la carrera.";
            hayGanador = true;
        } else if (tortuga.getPosicion() >= utils.TAM_CARRERA && liebre.getPosicion() < tortuga.getPosicion()) {
            infoCarrera = "La tortuga ganó la carrera.";
            hayGanador = true;
        } else if (tortuga.getPosicion() >= utils.TAM_CARRERA && liebre.getPosicion() >= utils.TAM_CARRERA) {
            infoCarrera = "¡¡Hubo un empate entre la liebre y la tortuga!!";
        }
    }
    
    /**
     * Función que se ejecuta en un hilo diferente para que se puede modificar
     * la velocidad a la que corren la liebre y la tortuga. El cambio de velocidad
     * se va alternando iniciando por la tortuga y después la liebre.
     * 
     * Si solo se desea cambiar la velocidad de uno de los dos participantes
     * (liebre o tortuga), entonces, se debe introducir la velocidad indicada
     * entre los paréntesis (esta es la velocidad a la que el personaje está
     * corriendo actualmente).
     */
    private void solicitarVelocidades() {
        while (!hayGanador) {
            synchronized (lock) {
                if (seCambioVelocidadTortuga) {
                    msgVelocidades = "Velocidad de tortuga (" + tortuga.getVelocidad() + "): ";
                    tortuga.setVelocidad(es.nextInt());
                } else {
                    msgVelocidades = "Velocidad de liebre (" + liebre.getVelocidad() + "): ";
                    liebre.setVelocidad(es.nextInt());
                }
                seCambioVelocidadTortuga = !seCambioVelocidadTortuga;
            }
        }
    }

    /**
     * Método que inicia la carrera. Es run de Threads para que se logre
     * apreciar el cambio en caso de que en el transcurso de la carrera se
     * decida cambiar la velocidad de los participantes.
     */
    @Override
    public void run() {
        liebre.start();
        tortuga.start();

        while (!(liebre.getPosicion() >= utils.TAM_CARRERA && tortuga.getPosicion() >= utils.TAM_CARRERA)) {
            utils.imprimirCarrera(liebre.getPosicion(), tortuga.getPosicion());
            actualizarInfoCarrera();
            System.out.println(msgVelocidades);
            utils.detenerTiempo();
        }

        System.out.println(infoCarrera + "\n¡Fin de la carrera del bosque!");
        System.out.println(">>> Ingresa un número y presiona enter para terminar la ejecución del programa.");
    }

    /**
     * Metodo que permite iniciar la impresión de la carrera.
     */
    public void iniciarCarrera() {
        this.start();
        new Thread(this::solicitarVelocidades).start();
    }
}
