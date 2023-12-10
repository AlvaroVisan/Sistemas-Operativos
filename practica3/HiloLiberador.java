import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import ssoo.videos.Video;

public class HiloLiberador extends Thread {
	private ColaTrabajosArrayBlockingQueue colaTrabajos;
	private CacheTrabajosActivos cacheTrabajosActivos;

	public HiloLiberador(ColaTrabajosArrayBlockingQueue colaTrabajos, CacheTrabajosActivos cacheTrabajosActivos) {
		this.colaTrabajos = colaTrabajos;
		this.cacheTrabajosActivos = cacheTrabajosActivos;
	}

	public void run() {
		int rango = (colaTrabajos.numTrabajos()+Runtime.getRuntime().availableProcessors());
		while(true)
		{
			try {
				if (cacheTrabajosActivos.numTrabajos() >= rango
						&& cacheTrabajosActivos.numTrabajos()<= (2*rango))
					Thread.sleep(1000);
				else{
					ConcurrentHashMap<Video,Trabajo> map = cacheTrabajosActivos.obtenerMapa();
					Collection<Trabajo> lista = map.values();
					for (Trabajo trabajo : lista) {
						if(trabajo.getVideoTranscodificado() != null)
							cacheTrabajosActivos.borrarTrabajo(trabajo);
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
