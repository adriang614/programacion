public class Contrato {
    //ATRIBUTOS
    private Integer fechaCreacion;
    private Medico medico;
    private Hospital hospital;

    //CONSTRUCTOR
    public Contrato (Integer fC, Medico m, Hospital h){
        this.fechaCreacion = fC;
        this.medico = m;
        this.hospital = h;
    }

    //METODO GET
    public Integer getFechaCreacion() {
        return fechaCreacion;
    }

    public Medico getMedico() {
        return medico;
    }

    public Hospital getHospital() {
        return hospital;
    }

    //METODO SET
    public void setFechaCreacion(Integer fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    //METODO COMPROBACION DE AÑO
    public boolean esDeAnio(int anio) {
        if (this.fechaCreacion == anio) {
            return true;
        }
        return false;
    }

    //METODO CALCULO DE VIGENCIA
    public int diasDesdeCreacion() {
        int anioActual = 2025;
        int diferenciaAnios = anioActual - this.fechaCreacion;

        return diferenciaAnios * 365;
    }
}
