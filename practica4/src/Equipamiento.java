import java.util.ArrayList;

public class Equipamiento {
    private String nombre;
    private String codigo;
    private Integer fechaAdquisicion;
    private ArrayList<Area> areas;

    public Equipamiento(String nombre, String codigo, Integer fechaAd, ArrayList areas){
        this.nombre = nombre;
        this.codigo = codigo;
        this.fechaAdquisicion = fechaAd;
        this.areas = areas;
    }

    public boolean esAntiguo (){
        int años = 2025 - this.fechaAdquisicion;
        if (años > 10){
            return true;
        }
        return false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(Integer fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public ArrayList<Area> getAreas() {
        return areas;
    }

    public void setAreas(ArrayList<Area> areas) {
        this.areas = areas;
    }
}
