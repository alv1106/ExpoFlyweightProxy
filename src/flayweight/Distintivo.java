package flayweight;
// FLYWEIGHT: guarda el estado INTRINSECO (compartido e inmutable) de cada categoria.
public class Distintivo {

    private final String categoria;   
    private final String icono;       
    private final String color;      

    public Distintivo(String categoria, String icono, String color) {
        this.categoria = categoria;
        this.icono = icono;
        this.color = color;
    }

    // El estado EXTRINSECO (latitud, longitud) llega por parametro, no se guarda aqui.
    public void mostrarEnMapa(double latitud, double longitud) {
        System.out.println("Mostrando en mapa " + categoria + " icono: " + icono + ", color: " + color +  " en (" + latitud + ", " + longitud + ")");
    }
}