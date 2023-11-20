package Concurrencia.FIFO_Cola_Compartida;

public class ColaFIFO<Elemento> {
    private final Elemento[] cola;
    private int inicio;
    private int fin;
    private final int tamaño;
    private int nelem;

    @SuppressWarnings("unchecked")
    public ColaFIFO(int tamaño) {
        cola = (Elemento[]) new Object[tamaño];
        this.tamaño = tamaño;
        inicio = fin = 0;
        nelem = 0;
    }

    public boolean estaVacia() {
        return nelem == 0;
    }

    public boolean estaLlena() {
        return nelem == tamaño;
    }

    public void meter(Elemento e) throws ExcepcionColaLlena {
        if (estaLlena())
            throw new ExcepcionColaLlena();
        cola[fin] = e;
        fin = (fin + 1) % tamaño;
        nelem++;
    }

    public Elemento sacar() throws ExcepcionColaVacia {
        Elemento e;
        if (estaVacia())
            throw new ExcepcionColaVacia();
        e = cola[inicio];
        inicio = (inicio + 1) % tamaño;
        nelem--;
        return e;
    }
}