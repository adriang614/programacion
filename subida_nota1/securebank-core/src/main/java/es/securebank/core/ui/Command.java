package es.securebank.core.ui;

import es.securebank.core.exception.CommandException;

public interface Command {
    String getNombre();
    String getDescripcion();
    String getUso();
    void execute(String[] args, AppContext context) throws CommandException;
}
