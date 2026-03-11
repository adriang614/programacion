import com.rpg.handler.RecursoNoEncontradoException;
import com.rpg.model.Ciudad;
import com.rpg.utils.TxtHelper;

import java.io.FileNotFoundException;

public class Main {
    public static void main (String[] args) throws FileNotFoundException, RecursoNoEncontradoException {
        TxtHelper th = new TxtHelper();
        th.leerLineas();
    }
}