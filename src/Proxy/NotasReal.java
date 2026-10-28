package Proxy;

// NotasReal.java
// SERVICIO: representa el Excel con las notas. No sabe nada de permisos.
public class NotasReal implements Notas {

    private final String archivo;

    public NotasReal(String archivo) {
        this.archivo = archivo;
        System.out.println("  [Disco] Abriendo " + archivo + "...");
    }

    @Override
    public void verNotas() {
        System.out.println("  Mostrando notas de la exposicion" + archivo);
        System.out.println("  Todos tienen 5 ");
    }
}
