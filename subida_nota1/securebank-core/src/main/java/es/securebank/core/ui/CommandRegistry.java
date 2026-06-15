package es.securebank.core.ui;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class CommandRegistry {
    private final Map<String, Command> comandos = new LinkedHashMap<>();

    public void registrar(Command cmd) {
        comandos.put(cmd.getNombre().toLowerCase(), cmd);
    }

    public Optional<Command> buscar(String nombre) {
        return Optional.ofNullable(comandos.get(nombre.toLowerCase()));
    }

    public Collection<Command> listarTodos() {
        return Collections.unmodifiableCollection(comandos.values());
    }
}
