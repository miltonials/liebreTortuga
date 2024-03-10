package modelo;

/**
 * Clase de utilidades complementarias para simular el cuento de la liebre 
 * y la tortuga.
 * 
 * @author Milton Barrera
 */
public class utils {
    public static int TAM_CARRERA = 100; // Tamaño de la carrera
    private static int DEFAULT_SLEEP_TIME = 200;
    private static int posTortuga;
    private static int posLiebre;
    
    /**
     * Método que permite imprimir las fichas de la liebre y la tortuga.
     */
    private static void imprimirPosicionCorredores() {
        StringBuilder carrera = new StringBuilder();
        for (int i = 0; i < posTortuga; i++) {
            carrera.append("-");
        }
        carrera.append("\n");
        for (int i = 0; i < posLiebre; i++) {
            carrera.append("+");
        }

        System.out.println(carrera.toString());
    }
    
    /**
     * Método que permite imprimir los bordes que delimitan la pista de la carrera.
     */
    private static void imprimirBorde() {
        for (int i = 0; i < utils.TAM_CARRERA - 5; i++) {
            System.out.print("=");
        }
        System.out.println("|Meta|");
    }
    
    /**
     * Método que permite detener el tiempo de ejecución.
     * @param milisegundos : tiempo a detener la ejecución en milisegundos.
     */
    public static void detenerTiempo(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public static void detenerTiempo() {
        try {
            Thread.sleep(utils.DEFAULT_SLEEP_TIME);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Método que permite imprimir las posiciones de la tortuga y la liebre
     * @param posLiebre : posición de la liebre
     * @param posTortuga : posición de la tortuga
     */
    public static void imprimirCarrera(int posLiebre, int posTortuga) {
        utils.posLiebre = posLiebre;
        utils.posTortuga = posTortuga;
        
        System.out.println("\n\n\n");
        imprimirBorde();
        imprimirPosicionCorredores();
        imprimirBorde();
    }
}
