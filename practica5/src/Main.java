import com.juego.presentacion.Presentador;
import com.juego.presentacion.Vista;

public class Main {
    public void main (String[] args){
        Vista vista = new Vista();
        Presentador presentador = new Presentador(vista);
        presentador.iniciar();
    }
}