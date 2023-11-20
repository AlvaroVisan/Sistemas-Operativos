package practica3;

import ssoo.videos.Encargo;
import ssoo.videos.servidor.Peticion;

public class HiloRecepcion implements Runnable {
    Encargo encargo;
    public HiloRecepcion(Peticion peticion) {
        this.encargo = peticion.getEncargo();
    }
    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            HiloEncargo hiloEncargo = new HiloEncargo(encargo);
            Thread hilo = new Thread(hiloEncargo);
            hilo.start();
        }
    }
    
}
