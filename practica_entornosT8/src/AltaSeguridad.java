
import java.util.HashSet;

public class AltaSeguridad extends Espacio {
    private HashSet<String> registroAccesos;

    public AltaSeguridad(double tamano) {
        super(tamano);
        this.registroAccesos = new HashSet<>();
    }

    public void validacionBiometrica(String dniPersonal, int ordenLlegada) {
        registroAccesos.add(ordenLlegada + " - " + dniPersonal);
    }
}
