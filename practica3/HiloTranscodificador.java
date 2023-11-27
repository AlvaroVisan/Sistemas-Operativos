package practica3;

import ssoo.videos.Transcodificador;

public  class HiloTranscodificador implements Runnable {
    ColaTrabajos<Trabajo> colaTrabajos;
    Trabajo trabajo;
    Transcodificador transcodificador;
    public HiloTranscodificador(ColaTrabajos<Trabajo> colaTrabajos) {
        this.colaTrabajos = colaTrabajos;
        this.transcodificador = new Transcodificador();
    }

    @Override
    public void run() {
        try {
            while (colaTrabajos.numTrabajos() > 0) {
                trabajo=colaTrabajos.sacar();
                trabajo.setVideoProcesado(transcodificador.transcodificar(trabajo.getVideoOriginal()));
            }

        } catch (ExcepcionColaVacia e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
