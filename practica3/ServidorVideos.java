



public class ServidorVideos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

			HiloRecepcion hiloR = new HiloRecepcion();
			Thread hilo = new Thread(hiloR);
			hilo.start();

	}

}
