package practica3;

import ssoo.videos.Numerable;

public class ColaTrabajos<Trabajo> implements Numerable {
    private final Trabajo[] cola;
    private int inicio;
    private int fin;
    private final int tamaño;
    private int nelem;

    @SuppressWarnings("unchecked")
    public ColaTrabajos(int tamaño) {
        cola = (Trabajo[]) new Object[tamaño];
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

    public void meter(Trabajo e) throws ExcepcionColaLlena{
        if (estaLlena())
            throw new ExcepcionColaLlena();
        cola[fin] = e;
        fin = (fin + 1) % tamaño;
        nelem++;
    }

    public Trabajo sacar() throws ExcepcionColaVacia {
        Trabajo e;
        if (estaVacia())
            throw new ExcepcionColaVacia();
        e = cola[inicio];
        inicio = (inicio + 1) % tamaño;
        nelem--;
        return e;
    }

    @Override
    public int numTrabajos() {
        return nelem;
    }
}