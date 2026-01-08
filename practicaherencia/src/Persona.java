public class Persona implements Humano {
    private String nombre;
    private Integer edad;
    private String dni;
    private String sexo;


    public Persona (String nombre, Integer edad, String dni, String sexo){
        this.nombre = nombre;
        this.edad = edad;
        this.dni = dni;
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void respirar() {}

    public void comer() {}

    public void beber() {}

    public void dormir() {}
}
