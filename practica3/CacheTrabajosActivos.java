import java.util.concurrent.ConcurrentHashMap;

import ssoo.videos.Numerable;
import ssoo.videos.Video;

public class CacheTrabajosActivos implements Numerable {

    private final ConcurrentHashMap<Video, Trabajo> map;
    public CacheTrabajosActivos(){
        map = new ConcurrentHashMap<Video, Trabajo>();
    }
    public void anadirMap(Trabajo t)
    {
        map.putIfAbsent(t.getVideoOriginal(), t);
    }
    public boolean yaExisteTrabajo(Trabajo t)
    {
        return map.contains(t.getVideoOriginal());
    }
    public Trabajo obtenerTrabajo(Trabajo t)
    {
        return map.get(t.getVideoOriginal());
    }

    @Override
    public int numTrabajos() {
        return map.size();
    }
    
}
