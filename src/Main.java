
public class Main {

    private static final int NUM_HILOS = 10;

    public static void main(String[] args) {

        NumeroOculto numoc = new NumeroOculto();

        Thread[] hilos = new Thread[NUM_HILOS];
        // Crea uno a uno los hilos. Cada hilo creado lo arranca y luego lo mete en el array hilos
        // Cada hilo generará un valor aleatorio y usará el objeto de la clase NumeroOculto para saber
        // si su número es el número oculto
        for (int i = 0; i < NUM_HILOS; i++) {
            Thread th = new Thread(new Hilo(i, numoc));
            th.start();
            hilos[i] = th;
        }
        // el thread main espera a que terminen todos los hilos antes de terminar
        for (Thread h: hilos) {
            try {
                h.join();
            } catch (InterruptedException e) {
            }
        }
    }
}