

import java.util.ArrayList;
import java.util.List;

import ssoo.videos.Dvd;
import ssoo.videos.Encargo;
import ssoo.videos.MenuRaiz;
import ssoo.videos.Video;
import ssoo.videos.servidor.Peticion;

public class HiloEncargo implements Runnable{

	private Peticion peticion;
	private Encargo encargo;
	private Trabajo trabajo;
	private ColaTrabajosArrayBlockingQueue cola;
	private List<Trabajo> lista;
	private List<Video> listaV;
	
	public HiloEncargo(Peticion p, ColaTrabajosArrayBlockingQueue cola2)
	{
		this.peticion=p;
		this.cola = cola2;
		lista = new ArrayList<Trabajo>();
		listaV = new ArrayList<Video>();
	}
	@Override
	public void run() {
		try {
		encargo = peticion.getEncargo();
		System.out.println("La informacion del encargo es: "+encargo.getVideos().size()+" videos "
			+encargo.getNombreUsuario()+" es el nombre del usuario");
		//Esperamos 5 segundos
		//Thread.sleep(5000);

		for(Video v : encargo.getVideos())
		{
			trabajo = new Trabajo(v);
			cola.encolar(trabajo);
			lista.add(trabajo);
		}
		Thread.sleep(5000); //Preguntar si es espera activa o inactiva
		/*
		for(Trabajo t1 : lista)
		{
			while (!t1.isEstaVT())
				Thread.sleep(100);
		}
		*/
		for(Trabajo tr : lista)
		{
			listaV.add(tr.getVideoTranscodificado());
		}
		Dvd dvd = new Dvd(encargo.getTitulo(), new MenuRaiz(listaV), listaV);
		peticion.getCliente().enviar(dvd);
		System.out.println("Acaba el hilo "+this.toString());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public String toString() {
		return "HiloEncargo [peticion=" + peticion + ", encargo=" + encargo + "]";
	}



}
