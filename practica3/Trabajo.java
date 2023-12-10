package fase2;

import ssoo.videos.Video;

public class Trabajo {
	
	private Video videoOriginal;
	private Video videoTranscodificado;
	private boolean estaVT;
	
	public boolean isEstaVT() {
		return estaVT;
	}

	public Trabajo(Video videoO)
	{
		this.videoOriginal = videoO;
		estaVT = false;
	}
	
	public Video getVideoOriginal()
	{
		return videoOriginal;
	}
	
	public void setVideoTranscodificado(Video videoT)
	{
		this.videoTranscodificado = videoT;
		estaVT = true;
	}
	
	public Video getVideoTranscodificado()
	{
		return videoTranscodificado;
	}

}
