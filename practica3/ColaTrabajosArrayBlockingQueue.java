
import java.util.concurrent.ArrayBlockingQueue;
import ssoo.videos.Numerable;

public class ColaTrabajosArrayBlockingQueue implements Numerable{

	private ArrayBlockingQueue<Trabajo> cola;

	public ColaTrabajosArrayBlockingQueue ( int tamano ) {

		cola = new ArrayBlockingQueue<Trabajo>(tamano);
	}

	public void encolar ( Trabajo e ) throws InterruptedException {

		cola.put(e);

	}
	public Trabajo desencolar () throws InterruptedException {
		Trabajo e;

		e = cola.take();

		return e;
	}
	@Override
	public int numTrabajos() {
		// TODO Auto-generated method stub
		return cola.size();
	}

}
