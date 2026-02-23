
public class Oficina extends AltaSeguridad {
    private int numero;
    private String nombre;

    public Oficina(double tamano, int numero, String nombre) {
        super(tamano);
        this.numero = numero;
        this.nombre = nombre;
    }
}
