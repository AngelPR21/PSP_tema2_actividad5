import java.util.Random;

class Hilo implements Runnable {

    private final int idhilo;
    private final NumeroOculto numeroOculto;

    /**
     * Constructor
     * @paramnombre
     */
    Hilo(int idhilo, NumeroOculto numeroOculto) {
        this.idhilo = idhilo;
        this.numeroOculto = numeroOculto;
    }
    @Override
    public void run() {
        // genera un numero entre 0 y 100 (sin incluir el 100)
        Random r = new Random();
        while(true) {
            // el siguiente bloque synchronized bloquea toda la transacción

            synchronized(this.numeroOculto) {
                int numero = r.nextInt(100);
                int idPartida = numeroOculto.obtenerIDPartida();
                // comprueba si es el numero oculto
                System.out.printf("Hilo %d (Partida %d). Voy a probar %d.\n", this.idhilo, idPartida, numero);
                int resp = this.numeroOculto.propuestaNumero(numero);
                if (resp == -1) {
                    System.out.printf("Hilo %d. ___ Juego ya terminado, no pruebo %d___\n", this.idhilo,numero,idPartida);
                    continue;
                } else if (resp == 1) {
                    System.out.printf("Hilo %d. *** EL NÚMERO %d SÍ ES EL NÚMERO OCULTO ***\n", this.idhilo,numero,idPartida);
                    continue;
                } else if (resp == 0) {
                    System.out.printf("Hilo %d. %d no es.\n", this.idhilo, numero,idPartida);
                } else {
                    System.out.printf("Hilo %d. Respuesta desconocida %d.\n", this.idhilo, resp);
                    continue;
                }
            }
            // esta pausa entre intentos permite que otros hilos hagan sus intentos
            // es una pausa de "cortesia"
            try {
                Thread.sleep(1);
            } catch(InterruptedException e) {
                System.out.printf("Hilo %d. Pausa interrumpida.\n", this.idhilo);
            }
        }
    }
}
