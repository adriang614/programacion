public class Usuario {

    //ATRIBUTOS locales de la clase (Significa que por ejemplo desde el main no lo puedo llamar)
    private String nombre;
    private String apellidos;
    private String codigoPostal;
    private String direccion;
    private String email;
    private String contraseña;


    //CONSTRUCTOR
    public Usuario(String n, String a, String c, String d, String e, String p){
        this.nombre = n;
        this.apellidos = a;
        this.codigoPostal = c;
        this.direccion = d;
        this.email = e;
        this.contraseña = p;
    }

    //MÉTODOS GET Y SET
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail (String nuevoEmail){
        if (nuevoEmail.contains("@")){
            this.email = nuevoEmail;
        }
        else {
            System.out.println("No es posible");
        }
    }

    //METODO TOSTRING
    public String toString(){
        return this.nombre + " " + this.apellidos + " " + this.codigoPostal + " " + this.email;
    }

    //METODO CHECKUSUARIO
    public  Boolean checkUsuario (String email, String contraseña){
        return this.getEmail().equals(email) && this.contraseña.equals(contraseña);
    }

}

