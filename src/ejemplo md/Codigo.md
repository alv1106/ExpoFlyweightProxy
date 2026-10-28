### Estructura general
![Estructura general de Facade](imgs/diagramaFacade.jpeg)


**Qué representa cada número de la figura:**

1. **La Fachada (Facade)** (`MundoFachada`) proporciona una interfaz sencilla y coordina las operaciones necesarias para crear un mundo, utilizando los distintos componentes del subsistema.

2. **La Fachada adicional (Additional Facade)** ofrece operaciones complementarias y permite distribuir las responsabilidades para evitar que la fachada principal se vuelva demasiado compleja.

3. **El Subsistema complejo (Subsystem)** está compuesto por las clases que realizan las tareas internas. En este ejemplo, `GuardarMundo`, `GeneradorSemilla`, `GeneradorBiomas` y `GeneradorEstructuras` validan y guardan el mundo, calculan su semilla, seleccionan un bioma y generan una estructura.

4. **El Cliente (Client)** (`App`) solicita la creación de un mundo mediante `MundoFachada`, sin tener que coordinar directamente los componentes del subsistema.

## El problema

Imagina que estás desarrollando un videojuego en el que los jugadores pueden crear sus propios mundos. Para realizar este proceso, el sistema debe verificar que el nombre tenga una longitud válida y que no exista otro mundo con el mismo nombre. Si las validaciones se cumplen, debe generar una semilla a partir del nombre, seleccionar aleatoriamente un bioma (bosque, desierto o nieve), generar una estructura correspondiente a ese bioma y guardar el mundo asociado al jugador.

Si la clase cliente tuviera que ejecutar directamente cada una de estas operaciones, tendría que conocer el funcionamiento y el orden de varios componentes diferentes. Esto aumentaría la complejidad del código y dificultaría el mantenimiento del proceso de creación de mundos.

## La solución

Se implementa el patrón Fachada mediante la clase `MundoFachada`, que proporciona un único método, `crearMundo()`, para coordinar todo el proceso. La fachada utiliza `GuardarMundo` para validar el nombre y comprobar si ya existe, `GeneradorSemilla` para calcular la semilla, `GeneradorBiomas` para seleccionar el bioma y `GeneradorEstructuras` para generar una estructura compatible con él.

![Estructura general de la solucion](imgs/diagramaSolucion.jpeg)

## Código para probar

### `MundoFachada.java`

```java
// FACHADA: coordina los subsistemas para crear un mundo.
public class MundoFachada {

    private final GuardarMundo guardado = new GuardarMundo();
    private final GeneradorSemilla semilla = new GeneradorSemilla();
    private final GeneradorBiomas biomas = new GeneradorBiomas();
    private final GeneradorEstructuras estructuras =
            new GeneradorEstructuras();

    public boolean crearMundo(String jugador, String nombre) {

        if (!guardado.nombreValido(nombre)) {
            System.out.println("No se pudo crear el mundo '"
                    + nombre + "': nombre invalido");
            return false;
        }

        if (guardado.existeMundo(nombre)) {
            System.out.println("No se pudo crear el mundo '"
                    + nombre + "': ya existe");
            return false;
        }

        int s = semilla.generar(nombre);
        String bioma = biomas.generar();
        String estructura = estructuras.generar(bioma);

        guardado.guardar(nombre, jugador);

        System.out.println("Mundo listo: " + nombre
                + " (semilla " + s + ") -> " + bioma
                + " con " + estructura);

        return true;
    }
}
```

### `GuardarMundo.java`

```java
// valida los nombres y registra los mundos.
public class GuardarMundo {

    private String nombresGuardados = ",";

    public boolean nombreValido(String nombre) {
        System.out.println("[Guardado] Verificando nombre del mundo: "
                + nombre);

        return nombre.length() >= 3 && nombre.length() <= 15;
    }

    public boolean existeMundo(String nombre) {
        System.out.println("[Guardado] Buscando si ya existe un mundo llamado "
                + nombre);

        return nombresGuardados.contains(
                "," + nombre.toLowerCase() + ",");
    }

    public void guardar(String nombre, String jugador) {
        nombresGuardados += nombre.toLowerCase() + ",";

        System.out.println("[Guardado] Mundo " + nombre
                + " guardado para " + jugador);
    }
}
```

### `GeneradorSemilla.java`

```java
//calcula la semilla del mundo.
public class GeneradorSemilla {

    public int generar(String nombre) {
        int semilla = nombre.length() * 1234 + 77;

        System.out.println("[Semilla] Semilla del mundo "
                + nombre + ": " + semilla);

        return semilla;
    }
}
```

### `GeneradorBiomas.java`

```java
//selecciona aleatoriamente el bioma principal.
public class GeneradorBiomas {

    public String generar() {
        //de forma aleatoria genera el bioma 
        int aleatorio = (int) (Math.random() * 3);

        String bioma;

        if (aleatorio == 0) {
            bioma = "Bosque";
        } else if (aleatorio == 1) {
            bioma = "Desierto";
        } else {
            bioma = "Nieve";
        }

        System.out.println("[Biomas] Bioma principal: " + bioma);

        return bioma;
    }
}
```

### `GeneradorEstructuras.java`

```java
// genera una estructura según el bioma.
public class GeneradorEstructuras {

    public String generar(String bioma) {
        String estructura;

        if (bioma.equals("Bosque")) {
            estructura = "Mansion del bosque";
        } else if (bioma.equals("Desierto")) {
            estructura = "Piramide";
        } else {
            estructura = "Iglu";
        }

        System.out.println("[Estructuras] Generando: " + estructura);

        return estructura;
    }
}
```

### `App.java`

```java
// CLIENTE: utiliza la fachada sin coordinar los subsistemas.
public class App {

    public static void main(String[] args) {

        MundoFachada mundo = new MundoFachada();

        System.out.println("-- Mundo 1 --");
        mundo.crearMundo("Ferney", "Mi primer mundo");

        System.out.println("-- Nombre repetido --");
        mundo.crearMundo("Luis", "mi primer mundo");

        System.out.println("-- Nombre invalido (muy corto) --");
        mundo.crearMundo("Pedro", "Ab");
    }
}
```

## Salida esperada

La selección del bioma es aleatoria, por lo que la estructura generada puede variar entre ejecuciones. El resultado general será similar al siguiente:

```text
-- Mundo 1 --
[Guardado] Verificando nombre del mundo: Mi primer mundo
[Guardado] Buscando si ya existe un mundo llamado Mi primer mundo
[Semilla] Semilla del mundo Mi primer mundo: 18587
[Biomas] Bioma principal: Bosque
[Estructuras] Generando: Mansion del bosque
[Guardado] Mundo Mi primer mundo guardado para Ferney
Mundo listo: Mi primer mundo (semilla 18587) -> Bosque con Mansion del bosque

-- Nombre repetido --
[Guardado] Verificando nombre del mundo: mi primer mundo
[Guardado] Buscando si ya existe un mundo llamado mi primer mundo
No se pudo crear el mundo 'mi primer mundo': ya existe

-- Nombre invalido (muy corto) --
[Guardado] Verificando nombre del mundo: Ab
No se pudo crear el mundo 'Ab': nombre invalido
```

## ¿Qué demuestra el patrón Fachada?

`MundoFachada` centraliza el proceso de creación y decide el orden de ejecución de los subsistemas. `App` no necesita conocer cómo se calcula la semilla, cómo se selecciona el bioma, cómo se genera la estructura ni cómo se guardan los nombres. Solo solicita la operación completa mediante `crearMundo()`.