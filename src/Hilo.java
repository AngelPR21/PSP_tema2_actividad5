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
        Random r = new Random();

        while (true) {
            int partidaActual = numeroOculto.obtenerIDPartida();
            //Bernardo si lees esto: Si meto esta linea en syncronized no deberia de dar fallo por partida antigua no? Ya que solo un hilo podria ejecutarlo a la vez
            //La he puesto fuera porque el ejercicio dice que podria pasar que un hilo juegue una partida anterior
            synchronized (numeroOculto) {


                int numero = r.nextInt(100);
                System.out.printf("Hilo %d (Partida %d). Propongo %d\n", idhilo, partidaActual, numero);
                int resp = numeroOculto.propuestaNumero(numero, partidaActual);

                if (resp == -1) {
                    System.out.printf("Hilo %d. La partida %d ya terminó, paso a la siguiente.\n", idhilo, partidaActual);
                } else if (resp == 1) {
                    System.out.printf("Hilo %d. El número %d es el número oculto en la partida %d\n",
                            idhilo, numero, partidaActual);
                } else if (resp == 0) {
                    System.out.printf("Hilo %d. %d no es el número oculto (Partida %d).\n", idhilo, numero, partidaActual);
                }

            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.printf("Hilo %d interrumpido.\n", idhilo);
            }
        }
    }
}
