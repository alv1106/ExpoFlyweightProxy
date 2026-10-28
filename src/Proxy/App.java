package Proxy;

// App.java
public class App {
    public static void main(String[] args) {
        Notas profe = new NotasProxy("notas_modelos.xlsx", "Prof. Norbey", "PROFESOR");
        Notas estudiante = new NotasProxy("notas_modelos.xlsx", "Garzon", "ESTUDIANTE");

        System.out.println("El estudiante intenta ver las notas:");
        estudiante.verNotas();

        System.out.println("El profesor intenta ver las notas:");
        profe.verNotas();
    }
}
