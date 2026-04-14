package rpg.model;

public class Raza {

    private int id;
    private String nombre;
    private int bonificadorVida;
    private int bonificadorFuerza;

    public Raza(int id, String nombre, int bonificadorVida, int bonificadorFuerza) {
        this.id = id;
        this.nombre = nombre;
        this.bonificadorVida = bonificadorVida;
        this.bonificadorFuerza = bonificadorFuerza;
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

    public int getBonificadorVida() {
        return bonificadorVida;
    }

    public void setBonificadorVida(int bonificadorVida) {
        this.bonificadorVida = bonificadorVida;
    }

    public int getBonificadorFuerza() {
        return bonificadorFuerza;
    }

    public void setBonificadorFuerza(int bonificadorFuerza) {
        this.bonificadorFuerza = bonificadorFuerza;
    }

    @Override
    public String toString() {
        return "Raza{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", bonificadorVida=" + bonificadorVida +
                ", bonificadorFuerza=" + bonificadorFuerza +
                '}';
    }
}