public class Ciudad {
    private Integer id;
    private String nombre;
    private Integer nivel_minimo_acceso;

    public Ciudad(Integer id, String nombre, Integer nivel_minimo_acceso) {
        this.id = id;
        this.nombre = nombre;
        this.nivel_minimo_acceso = nivel_minimo_acceso;
    }

    public Integer getNivel() {
        return nivel_minimo_acceso;
    }
}
