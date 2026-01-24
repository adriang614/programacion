public class Medico extends Persona {
    private Integer telefono;
    private Integer numConsulta;
    private String especialidad;

    public Medico(String nombre, Integer edad, String dni, String sexo, Integer telefono, Integer numConsulta, String especialidad) {
        super(nombre, edad, dni, sexo);
        this.telefono = telefono;
        this.numConsulta = numConsulta;
        this.especialidad = especialidad;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Integer getNumConsulta() {
        return numConsulta;
    }

    public void setNumConsulta(Integer numConsulta) {
        this.numConsulta = numConsulta;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
