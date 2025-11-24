public class Contrato {
    //ATRIBUTOS
    private String fechaCreacion;
    private Medico medico;
    private Hospital hospital;

    //CONSTRUCTOR
    public Contrato (String fC, Medico m, Hospital h){
        this.fechaCreacion = fC;
        this.medico = m;
        this.hospital = h;
    }

    //METODO GET
    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public Medico getMedico() {
        return medico;
    }

    public Hospital getHospital() {
        return hospital;
    }

    //METODO SET
    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}
