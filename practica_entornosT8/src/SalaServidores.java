
public class SalaServidores extends AltaSeguridad {
    private int numero;
    private String nombre;

    public SalaServidores(double tamano, int numero, String nombre) {
        super(tamano);
        this.numero = numero;
        this.nombre = nombre;
    }
}
