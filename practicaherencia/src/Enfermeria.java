public class Enfermeria extends Persona{
    private Integer antiguedad;
    private String sueldo;

    public Enfermeria(String nombre, Integer edad, String dni, String sexo, Integer antiguedad, String sueldo) {
        super(nombre, edad, dni, sexo);
        this.antiguedad = antiguedad;
        this.sueldo = sueldo;
    }

    public Integer getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Integer antiguedad) {
        this.antiguedad = antiguedad;
    }

    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }
}
