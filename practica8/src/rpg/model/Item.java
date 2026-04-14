package rpg.model;

public class Item {
    private int id;
    private String nombre;
    private String tipo;
    private int precioOro;
    private int bonificadorAtaque;
    private int bonificadorDefensa;

    public Item() {}

    public Item(int id, String nombre, String tipo, int precioOro, int bonificadorAtaque, int bonificadorDefensa) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precioOro = precioOro;
        this.bonificadorAtaque = bonificadorAtaque;
        this.bonificadorDefensa = bonificadorDefensa;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPrecioOro() {
        return precioOro;
    }

    public void setPrecioOro(int precioOro) {
        this.precioOro = precioOro;
    }

    public int getBonificadorAtaque() {
        return bonificadorAtaque;
    }

    public void setBonificadorAtaque(int bonificadorAtaque) {
        this.bonificadorAtaque = bonificadorAtaque;
    }

    public int getBonificadorDefensa() {
        return bonificadorDefensa;
    }

    public void setBonificadorDefensa(int bonificadorDefensa) {
        this.bonificadorDefensa = bonificadorDefensa;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", precioOro=" + precioOro +
                ", bonificadorAtaque=" + bonificadorAtaque +
                ", bonificadorDefensa=" + bonificadorDefensa +
                '}';
    }
}