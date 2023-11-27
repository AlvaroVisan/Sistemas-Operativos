package practica3;

import java.io.IOException;

import ssoo.videos.servidor.Peticion;
import ssoo.videos.servidor.ReceptorPeticiones;

public class HiloRecepcion implements Runnable {
    ReceptorPeticiones receptorPeticiones;
    public HiloRecepcion() {
  
    }
    @Override
    public void run() {
        try {
            receptorPeticiones = new ReceptorPeticiones();
            Peticion peticion;
            while((peticion = receptorPeticiones.recibirPeticion()) != null) {
                HiloEncargo hiloEncargo = new HiloEncargo(peticion);
                Thread hilo = new Thread(hiloEncargo);
                hilo.start();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}
