import java.util.ArrayList;
import java.util.List;

public class Planta {
    private int numero;
    private String especialidad;
    private List<Espacio> espacios;

    public Planta(int numero, String especialidad) {
        this.numero = numero;
        this.especialidad = especialidad;
        this.espacios = new ArrayList<>();
    }
}

