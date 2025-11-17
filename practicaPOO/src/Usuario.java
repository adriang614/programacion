public class Usuario {
    private String Nombre;
    private String Apellidos;
    private String CodigoPostal;
    private String Direccion;
    private String Email;
    private String Contraseña;

    public Usuario(String Nombre, String Apellidos, String CodigoPostal, String Direccion, String Email, String Contraseña){
        Nombre = "Adrián";
        Apellidos = "González Cumbreras";
        CodigoPostal = "11408";
        Direccion = "Calle Sol";
        Email = "adriangc@gmail.com";
        Contraseña = "1234";
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public String getCodigoPostal() {
        return CodigoPostal;
    }

    public String getDireccion() {
        return Direccion;
    }

    public String getEmail() {
        return Email;
    }

    public String getContraseña() {
        return Contraseña;
    }



}

