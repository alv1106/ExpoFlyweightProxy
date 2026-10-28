package flayweight;
import java.util.ArrayList;
import java.util.List;

// CLIENTE / CONTENEDOR: crea muchas ubicaciones pero pide los distintivos a la fabrica.
public class Mapa {

    private final List<Ubicacion> ubicaciones = new ArrayList<>();

    public void agregarUbicacion(double latitud, double longitud, String categoria, String icono, String color) {
        Distintivo distintivo = FabricaDistintivos.obtener(categoria, icono, color);
        ubicaciones.add(new Ubicacion(latitud, longitud, distintivo));
    }

    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }
}
