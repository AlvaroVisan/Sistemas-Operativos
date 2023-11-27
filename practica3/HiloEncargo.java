package practica3;

import ssoo.videos.Encargo;
import ssoo.videos.servidor.Cliente;
import ssoo.videos.servidor.Peticion;

public class HiloEncargo implements Runnable {
    Encargo encargo;
    Cliente cliente;
    ColaTrabajos<Trabajo> colaTrabajos;
    public HiloEncargo(Peticion peticion, ColaTrabajos<Trabajo> colaTrabajos) {
        this.encargo = peticion.getEncargo();
        this.cliente = peticion.getCliente();
        this.colaTrabajos = colaTrabajos;
    }
    @Override
    public void run() {
        System.out.println("El usuario del encargo: " + encargo.getNombreUsuario()
                + " ha solicitado " + encargo.getVideos().size()+ " videos");

        for (int i = 0; i < encargo.getVideos().size(); i++) {
            Trabajo trabajo = new Trabajo(encargo.getVideos().get(i));
            try {
                colaTrabajos.meter(trabajo);
            } catch (ExcepcionColaLlena e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
}
