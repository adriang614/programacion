import java.time.LocalDate;
import java.util.Scanner;

public class Medico {
    //ATRIBUTOS
    private String dni;
    private String nombre;
    private String sexo;
    private Integer sueldoBruto;
    private Integer fechaInicio;
    private Area area;

    //CONSTRUCTOR
    public Medico (String d, String n, String s, Integer sB, Integer fI, Area a) {
        this.dni = d;
        this.nombre = n;
        this.sexo = s;
        this.sueldoBruto = sB;
        this.fechaInicio = fI;
        this.area = a;
    }

    //METODO GET
    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public Integer getSueldoBruto() {
        return sueldoBruto;
    }

    public Integer getFechaInicio() {
        return fechaInicio;
    }

    public Area getArea() {
        return area;
    }

    //METODO SET
    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setSueldoBruto(Integer sueldoBruto) {
        this.sueldoBruto = sueldoBruto;
    }

    public void setFechaInicio(Integer fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    //METODO SUELDO NETO
    public double calcularSueldo(double retencion) {
        double sueldoNeto = sueldoBruto * (retencion / 100);
        return sueldoNeto;
    }

    //METODO ANTIGUEDAD
    public int getAniosAntiguedad(){
        int antiguedad = 2025 - getFechaInicio();
        return antiguedad;
    }

    //METODO IMPUESTOS ANUALES
    public double calcularImpuestosAnuales(double tasaImpositiva) {
        double tasaImpuesto = this.sueldoBruto * tasaImpositiva;
        return tasaImpositiva;
    }

    //METODO MAYOR DE EDAD
    public boolean esMayorDeEdad (int mayoriaDeEdad) {
        return mayoriaDeEdad >= 18;
    }

    //METODO AUMENTO
    public double proximoAumento (double porcentajeAumento, int aniosRequeridos) {
        double resultado = this.sueldoBruto;
        if (this.getAniosAntiguedad() >= aniosRequeridos){
            resultado = sueldoBruto + (sueldoBruto * (porcentajeAumento/100));
        }
        return resultado;
    }

    //METODO AREA
    public void cambiarArea(Area nuevaArea){
        // Primero restamos el médico del área actual
        this.area.restaMedicos();

        // Cambiamos el área del médico
        this.area = nuevaArea;

        // Sumamos el médico al área nueva
        this.area.sumaMedicos();
    }
}