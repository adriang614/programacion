package rpg.model;

public class Habilidad {
    private int id;
    private String nombre;
    private int dañoBase;
    private int usosMaximos;
    private Clase clase;

    public Habilidad(int id, String nombre, int dañoBase, int usosMaximos, Clase clase) {
        this.id = id;
        this.nombre = nombre;
        this.dañoBase = dañoBase;
        this.usosMaximos = usosMaximos;
        this.clase = clase;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDañoBase() {
        return dañoBase;
    }

    public void setDañoBase(int dañoBase) {
        this.dañoBase = dañoBase;
    }

    public int getUsosMaximos() {
        return usosMaximos;
    }

    public void setUsosMaximos(int usosMaximos) {
        this.usosMaximos = usosMaximos;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    @Override
    public String toString() {
        return "Habilidad{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", dañoBase=" + dañoBase +
                ", usosMaximos=" + usosMaximos +
                ", clase=" + clase +
                '}';
    }
}
