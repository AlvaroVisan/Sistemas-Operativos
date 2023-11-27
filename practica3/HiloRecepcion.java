package practica3;

import java.io.IOException;

import ssoo.videos.PanelVisualizador;
import ssoo.videos.servidor.Peticion;
import ssoo.videos.servidor.ReceptorPeticiones;

public class HiloRecepcion implements Runnable {
    ReceptorPeticiones receptorPeticiones;
    ColaTrabajos<Trabajo> colaTrabajos;
    public HiloRecepcion() {
  
    }
    @Override
    public void run() {
        try {
            receptorPeticiones = new ReceptorPeticiones();
            Peticion peticion;
            colaTrabajos = new ColaTrabajos<Trabajo>(10);
            PanelVisualizador.getPanel().registrarColaPeticiones(colaTrabajos);
            while((peticion = receptorPeticiones.recibirPeticion()) != null) {
                HiloEncargo hiloEncargo = new HiloEncargo(peticion, colaTrabajos);
                Thread hilo = new Thread(hiloEncargo);
                hilo.start();
            }
            int i = 0;
            while (i < Runtime.getRuntime().availableProcessors() ) {
                HiloTranscodificador hiloTranscodificador = new HiloTranscodificador(colaTrabajos);
                Thread hilo = new Thread(hiloTranscodificador);
                hilo.start();
                i++;  
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    
}
