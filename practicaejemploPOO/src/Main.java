public class Main {
    public static void main (String[] args){
        Usuario user = new Usuario("Adrián", "González", "11408", "calle sol", "adrian@gmail.com", "asd182");

        System.out.println(user.getNombre());

        user.setNombre("Juan");

        System.out.println(user.getNombre());

        System.out.println(user.getEmail());

        user.setEmail("adrian@.com");

        System.out.println(user.getEmail());

        System.out.println(user.toString());

        System.out.println(user.checkUsuario("adrian@.com", "asd182"));
    }
}