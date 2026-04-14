package rpg.model;

public class Ciudad {
    private int id;
    private String nombre;
    private int nivelMinimoAcceso;

    public Ciudad(int id, String nombre, int nivelMinimoAcceso) {
        this.id = id;
        this.nombre = nombre;
        this.nivelMinimoAcceso = nivelMinimoAcceso;
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

    public int getNivelMinimoAcceso() {
        return nivelMinimoAcceso;
    }

    public void setNivelMinimoAcceso(int nivelMinimoAcceso) {
        this.nivelMinimoAcceso = nivelMinimoAcceso;
    }

    @Override
    public String toString() {
        return "Ciudad{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nivelMinimoAcceso=" + nivelMinimoAcceso +
                '}';
    }
}