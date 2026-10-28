package flayweight;
public class App {
    public static void main(String[] args) {
        double baseLat = 4;
        double baseLon = -74;
        Mapa mapa = new Mapa();


        for (int i = 0; i < 10000; i++) {
            double lat = baseLat + i;
            double lon = baseLon + i;

            switch (i % 3) {
                case 0 -> mapa.agregarUbicacion(lat, lon, "Restaurante", "icono-restaurante.png", "rojo");
                case 1 -> mapa.agregarUbicacion(lat, lon, "Parque", "icono-parque.png", "verde");
                default -> mapa.agregarUbicacion(lat, lon, "Supermercado", "icono-supermercado.png", "azul");
            }
        }

        System.out.println("Cantidad de iconos de lugares en el mapa: " + mapa.getUbicaciones().size());
        System.out.println("Objetos intrinsicos (flyweights): " + FabricaDistintivos.cantidadDistintivos());

        Ubicacion u0 = mapa.getUbicaciones().get(0);
        Ubicacion u1 = mapa.getUbicaciones().get(1);
        Ubicacion u2 = mapa.getUbicaciones().get(2);

        u0.mostrarEnMapa();
        u1.mostrarEnMapa();
        u2.mostrarEnMapa();
    }
}