package practica3;

import ssoo.videos.Video;

public class Trabajo {
    Video videoOriginal;
    Video videoProcesado;
    public Trabajo(Video videoOriginal) {
        this.videoOriginal = videoOriginal;
    }
    public Video getVideoOriginal() {
        return videoOriginal;
    }
    public Video getVideoProcesado() {
        return videoProcesado;
    }   
    public void setVideoProcesado(Video videoProcesado) {
        this.videoProcesado = videoProcesado;
    }
}
