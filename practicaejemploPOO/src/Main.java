public class Main {
    public static void main(String [] args) {
        Usuario usuario1= new Usuario("Pepe","Pérez Gálvez","11026","Calle Sol","pepe@gmail.com","abc123");
        Usuario usuario2= new Usuario("Juan","Sánchez Guerrero","11405","Calle Lune","juan@gmail.com","456rdf");
        Usuario usuario3= new Usuario("Ana","Romero Mateos","28485","Calle Almendras","an@gmail.com","987zxy");
        Usuario[] usuario = {usuario1,usuario2,usuario3};
        validarRegistro(usuario);

        if (usuario1.checkusuario("pepe@gmail.com", "abc123")){
            System.out.println("Acceso concedido a " + usuario1.getNombre());
        }
        if (usuario2.checkusuario("juan@gmail.com", "123rdf")){
            System.out.println("Acceso concedido a " + usuario2.getNombre());
        }
        else {
            System.out.println("¡ERROR! Contraseña incorrecta para " + usuario2.getEmail() + ". Acceso denegado.");
        }
    }


    public static void validarRegistro(Usuario[] usuario){
        for (int i = 0; i < usuario.length; i++) {
            if (usuario[i].getCodigopostal().startsWith("28")) {
                System.out.println("Codigo postal Valido");
            } else {
                System.out.println("Código Postal no valido");
            }
        }
    }
}