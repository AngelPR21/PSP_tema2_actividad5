import java.util.Random;

public class NumeroOculto {

    private int numeroOculto;
    private boolean adivinado;
    private int id = 1;

    /**
     * Constructor
     */
    public NumeroOculto() {
        Random r = new Random();
        // genera un numero entre 0 y 100 (sin incluir el 100)
        this.numeroOculto = r.nextInt(100);
        System.out.printf("El número %d es el número oculto.\n", this.numeroOculto);
        this.adivinado = false;
    }

    /**
     * Comprueba si el número propuesto es el número oculto
     *
     * @param num
     *
     * @return
     *  – 1 si el juego ya ha terminado porque un hilo ha adivinado el número
     *  1 si el número propuesto ( num ) es el número oculto.
     *  0 en otro caso.
     *
     */
    public int propuestaNumero(int num, int idPartidaHilo) {
        if (idPartidaHilo != this.id) {
            return -1; // Hilo intentando jugar en una partida antigua
        }

        if(this.adivinado) {
            // el juego ya ha terminado porque algún hilo ha adivinado el número
            return -1;
        } else if(num == this.numeroOculto) {
            // si el número propuesto ( num ) es el número oculto.
            this.adivinado = true;
            id++;
            nuevaPartida();
            return 1;
        } else {
            // nadie ha adivinado el número y el número indicado no es el oculto
            return 0;
        }
    }
    public void nuevaPartida() {
        Random r = new Random();
        this.numeroOculto = r.nextInt(100);
        this.adivinado = false;
        System.out.printf("\nNueva partida iniciada. ID: %d. (Número oculto = %d)\n", this.id, this.numeroOculto);
    }

    public synchronized int obtenerIDPartida(){
        return this.id;
    }
}
