package practica3;

import ssoo.videos.Encargo;

public class HiloEncargo implements Runnable {
    Encargo encargo;
    public HiloEncargo(Encargo encargo) {
        this.encargo = encargo;
    }
    @Override
    public void run() {
        new Encargo(encargo.getNombreUsuario(), encargo.getTitulo());
    }
    
}
