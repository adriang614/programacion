import com.juego.clases.Mago;
import com.juego.clases.Sacerdote;
import com.juego.modelo.Combate;
import com.juego.modelo.Personaje;
import com.juego.razas.Elfo;
import com.juego.razas.Humano;

public class Main {
    Personaje p1 = new Personaje("Adrian", new Humano, new Sacerdote);
    Personaje p2 = new Personaje("Paco", new Elfo, new Mago);
    Combate c = new Combate(p1, p2);
    c.inciar
}