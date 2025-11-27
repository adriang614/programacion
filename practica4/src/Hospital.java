import java.util.ArrayList;

public class Hospital {

    //ATRIBUTOS
    private String nombre;
    private String cif;
    private Direccion d;
    private ArrayList<Area> areas;

    //CONSTRUCTOR
    public Hospital (String n, String cif, Direccion d, ArrayList<Area> areas){
        this.nombre = n;
        this.cif = cif;
        this.d = d;
        this.areas = new ArrayList<Area>();
    }


    //METODO GET
    public String getNombre() {
        return nombre;
    }

    public String getCif() {
        return cif;
    }

    public Direccion getD() {
        return d;
    }

    public ArrayList<Area> getAreas() {
        return areas;
    }

    //METODO SET
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public void setD(Direccion d) {
        this.d = d;
    }

    public void setAreas(ArrayList<Area> areas) {
        this.areas = areas;
    }

    //METODO CALCULO AGREGADO
    public int getNumeroTotalMedicos() {
        int total = 0;
        for (int i = 0; i < areas.size(); i++) {
            total += areas.get(i).getNumMedicos();
        }
        return total;
    }

    //METODO CALCULO DE PROPORCION


}
