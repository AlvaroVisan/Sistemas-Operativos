package practica3;

import ssoo.videos.Encargo;
import ssoo.videos.servidor.Cliente;
import ssoo.videos.servidor.Peticion;

public class HiloEncargo implements Runnable {
    Encargo encargo;
    Cliente cliente;
    public HiloEncargo(Peticion peticion) {
        this.encargo = peticion.getEncargo();
        this.cliente = peticion.getCliente();
    }
    @Override
    public void run() {
        System.out.println("El usuario del encargo: " + encargo.getNombreUsuario()
                + " ha solicitado " + encargo.getVideos().size()+ " videos");
    }
    
}
