public class Paciente extends Persona {
    private Integer numSeguridadSocial;
    private String causa;

    public Paciente(String nombre, Integer edad, String dni, String sexo, Integer numSeguridadSocial, String causa) {
        super(nombre, edad, dni, sexo);
        this.numSeguridadSocial = numSeguridadSocial;
        this.causa = causa;
    }

    public Integer getNumSeguridadSocial() {
        return numSeguridadSocial;
    }

    public void setNumSeguridadSocial(Integer numSeguridadSocial) {
        this.numSeguridadSocial = numSeguridadSocial;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }
}
