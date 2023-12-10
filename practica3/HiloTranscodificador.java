package fase2;

import ssoo.videos.Transcodificador;

public class HiloTranscodificador implements Runnable {

	private Trabajo t;
	private ColaTrabajosArrayBlockingQueue cola;
	private Transcodificador transcodificador;
	
	public HiloTranscodificador(ColaTrabajosArrayBlockingQueue cola2)
	{
		this.cola = cola2;
		transcodificador = new Transcodificador();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(cola.numTrabajos() > 0)
			{
				t = cola.desencolar();
				//v = transcodificador.transcodificar(t.getVideoOriginal());
				t.setVideoTranscodificado(transcodificador.transcodificar(t.getVideoOriginal()));
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
