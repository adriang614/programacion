import java.util.HashSet;

public class Ascensor extends Maquinaria {
    private int idAscensor;
    private boolean vip;
    private HashSet<String> registroAccesos;

    public Ascensor(int idAscensor, boolean vip, String funcion) {
        super(funcion);
        this.idAscensor = idAscensor;
        this.vip = vip;
        this.registroAccesos = new HashSet<>();
    }

    public void validacionBiometrica(String dniPersonal, int ordenLlegada) {
        registroAccesos.add(ordenLlegada + " - " + dniPersonal);
    }
}
