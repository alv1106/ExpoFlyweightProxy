package flayweight;
import java.util.HashMap;
import java.util.Map;

// FABRICA FLYWEIGHT: entrega un objeto existente si ya hay uno con esas caracteristicas.
public class FabricaDistintivos {

    private static final Map<String, Distintivo> cache = new HashMap<>();

    public static Distintivo obtener(String categoria, String icono, String color) {
        String clave = categoria + "|" + icono + "|" + color;
        Distintivo distintivo = cache.get(clave);
        if (distintivo == null) {
            distintivo = new Distintivo(categoria, icono, color);
            cache.put(clave, distintivo);
        }
        return distintivo;
    }

    

    public static int cantidadDistintivos() {
        return cache.size();
    }
}
    
