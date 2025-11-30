public class Area {
    //ATRIBUTOS
    private String nombre;
    private String identificador;
    private Integer planta;
    private Hospital hospital;
    private Integer numMedicos;

    //CONSTRUCTOR
    public Area (String n, String i, Integer p, Hospital h){
        this.nombre = n;
        this.identificador = i;
        this.planta = p;
        this.hospital = h;
        this.numMedicos = 0;
    }

    //METODO GET
    public String getNombre() {
        return nombre;
    }

    public String getIdentificador() {
        return identificador;
    }

    public Integer getPlanta() {
        return planta;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public Integer getNumMedicos() {
        return numMedicos;
    }

    //METODO SET
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public void setPlanta(Integer planta) {
        this.planta = planta;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public void setNumMedicos(Integer numMedicos) {
        this.numMedicos = numMedicos;
    }

    //FUNCION CONTADOR DE MEDICOS
    public void sumaMedicos(){
        this.numMedicos ++;
    }

    //FUNCION RESTA MEDICOS
    public void restaMedicos(){
        this.numMedicos --;
    }

    //METODO COMPROBACION Y COMPARACION
    public String compararMedicos (Area otraArea) {
        if (this.numMedicos > otraArea.getNumMedicos()){
            return "El area " + this.nombre + " tiene más médicos que " + otraArea.getNombre();
        } else {
            return "El area " + otraArea.getNombre() + " tiene más médicos que " + this.nombre;
        }
    }

    //METODO CAPACIDAD
    public int calcularCapacidadRestante(int capacidadMaxima) {
        int result = 0;
        if (this.numMedicos < capacidadMaxima) {
            int x = capacidadMaxima - this.numMedicos;
            result = x;
        }
        return result;
    }

}
