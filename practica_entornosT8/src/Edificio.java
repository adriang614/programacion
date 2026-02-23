
import java.util.ArrayList;
import java.util.List;

public class Edificio {
    private String nombre;
    private String direccion;
    private List<Planta> plantas;
    private List<Maquinaria> maquinarias;

    public Edificio(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.plantas = new ArrayList<>();
        this.maquinarias = new ArrayList<>();
    }
}
