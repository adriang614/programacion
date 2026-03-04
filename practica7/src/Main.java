import com.rpg.model.Ciudad;
import com.rpg.utils.TxtHelper;

import java.io.FileNotFoundException;

public class Main {
    public static void main (String[] args) throws FileNotFoundException {
        TxtHelper th = new TxtHelper();
        th.writeList(new Ciudad("Jerez", 1, "Hot", 100));
        th.leerLinea();
    }
}