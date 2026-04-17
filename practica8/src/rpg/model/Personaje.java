package rpg.model;

import java.util.ArrayList;
import java.util.List;

public class Personaje {
    private int id;
    private String nombre;
    private int nivel;
    private int oro;
    private int vidaActual;
    private Raza raza;
    private Clase clase;
    private Ciudad ciudadActual;
    private List<Item> inventario;
    private List<Habilidad> habilidades;

    public Personaje(String nombre, Raza raza, Clase clase) {
        this.nombre = nombre;
        this.raza = raza;
        this.clase = clase;
        this.nivel = 1;
        this.oro = 100;
        this.vidaActual = 150;
    }

    public Personaje() {}

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

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public void setVidaActual(int vidaActual) {
        this.vidaActual = vidaActual;
    }

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public Ciudad getCiudadActual() {
        return ciudadActual;
    }

    public void setCiudadActual(Ciudad ciudadActual) {
        this.ciudadActual = ciudadActual;
    }

    @Override
    public String toString() {
        return "Personaje{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nivel=" + nivel +
                ", oro=" + oro +
                ", vidaActual=" + vidaActual +
                ", raza=" + raza +
                ", clase=" + clase +
                ", ciudadActual=" + ciudadActual +
                '}';
    }

    public List<Habilidad>  getHabilidades() {
        return habilidades;
    }

    public void setHabilidades (List<Habilidad> habilidades) {
        this.habilidades = habilidades;
    }

}