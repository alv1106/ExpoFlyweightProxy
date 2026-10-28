package Proxy;

// NotasProxy.java
// PROXY (proteccion): verifica el rol del usuario antes de dejar pasar la solicitud.
public class NotasProxy implements Notas {

    private final String archivo;
    private final String usuario;
    private final String rol;     
    private NotasReal real;

    public NotasProxy(String archivo, String usuario, String rol) {
        this.archivo = archivo;
        this.usuario = usuario;
        this.rol = rol;
    }

    @Override
    public void verNotas() {
        if (!rol.equals("PROFESOR")) {
            System.out.println("  Acceso denegado para " + usuario + "por que es un estudiante");
            return;                           
        }
        if (real == null) {
            real = new NotasReal(archivo);     // se crea solo si hay permiso
        }
        System.out.println("  Acceso permitido a " + usuario);
        real.verNotas();                       // delega
    }
}
