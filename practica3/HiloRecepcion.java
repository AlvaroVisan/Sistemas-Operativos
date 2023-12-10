

import java.io.IOException;

import ssoo.videos.PanelVisualizador;
import ssoo.videos.servidor.Peticion;
import ssoo.videos.servidor.ReceptorPeticiones;

public class HiloRecepcion implements Runnable {


	private ColaTrabajosArrayBlockingQueue cola;


	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			final ReceptorPeticiones receptor = new ReceptorPeticiones();
			cola = new ColaTrabajosArrayBlockingQueue(10);
			PanelVisualizador.getPanel().registrarColaPeticiones(cola);
			int i = 0;
			while(true)
			{
				final Peticion peticion = receptor.recibirPeticion();
				HiloEncargo hiloEncargo = new HiloEncargo(peticion,cola);
				Thread hilo = new Thread(hiloEncargo);
				hilo.start();
				if(i<Runtime.getRuntime().availableProcessors())
				{
					HiloTranscodificador hiloTranscodificador = new HiloTranscodificador(cola);
					hilo = new Thread(hiloTranscodificador);
					hilo.start();
					i++;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
