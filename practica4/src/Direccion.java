public class Direccion {

    //ATRIBUTOS
    private String calle;
    private Integer numero;
    private Integer codigoPostal;
    private String localidad;
    private String provincia;


    //CONSTRUCTOR
    public Direccion (String c, Integer n, Integer cp, String l, String p){
        this.calle = c;
        this.numero = n;
        this.codigoPostal = cp;
        this.localidad = l;
        this.provincia = p;
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