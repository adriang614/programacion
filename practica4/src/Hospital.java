import java.util.ArrayList;

public class Hospital {

    //ATRIBUTOS
    private String nombre;
    private Integer cif;
    private ArrayList<Area> areas;
    private  Direccion d;

    //CONSTRUCTOR
    public Hospital (String n, Integer cif, Direccion d){
        this.nombre = n;
        this.cif = cif;
        this.d = d;
        this.areas = new ArrayList<Area>();
    }


    //METODO GET
    public String getCalle() {
        return calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getProvincia() {
        return provincia;
    }


    //METODO SET
    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
}
