package flayweight;
// CONTEXTO: guarda el estado EXTRINSECO (unico de cada punto) y una referencia al flyweight.

public class Ubicacion {

    private final double latitud;
    private final double longitud;
    private final Distintivo distintivo;

    public Ubicacion(double latitud, double longitud, Distintivo distintivo) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.distintivo = distintivo;
    }

    public Distintivo getDistintivo() {
        return distintivo;
    }

    public void mostrarEnMapa() {
        distintivo.mostrarEnMapa(latitud, longitud);
    }
}
