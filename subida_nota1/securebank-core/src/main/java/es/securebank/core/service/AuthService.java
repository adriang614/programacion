package es.securebank.core.service;

import es.securebank.core.dao.ClienteDao;
import es.securebank.core.model.Cliente;
import es.securebank.core.model.enums.EstadoCliente;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class AuthService {

    private final ClienteDao clienteDao;
    private Cliente clienteActual = null;

    public AuthService(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    // Convierte el pin a SHA-256
    private String hashPin(String pin) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(pin.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear el pin", e);
        }
    }

    public boolean login(String dni, String pin) {
        Optional<Cliente> cliente = clienteDao.findByDni(dni);

        if (cliente.isEmpty()) {
            System.out.println("[AUTH] Cliente con DNI " + dni + " no encontrado.");
            return false;
        }

        if (cliente.get().getEstado() == EstadoCliente.BLOQUEADO) {
            System.out.println("[AUTH] Cliente bloqueado.");
            return false;
        }

        if (cliente.get().getEstado() == EstadoCliente.BAJA) {
            System.out.println("[AUTH] Cliente dado de baja.");
            return false;
        }

        String pinHash = hashPin(pin);
        if (!pinHash.equals(cliente.get().getPinHash())) {
            System.out.println("[AUTH] PIN incorrecto.");
            return false;
        }

        clienteActual = cliente.get();
        System.out.println("[AUTH] Bienvenido, " + clienteActual.getNombre() + "!");
        return true;
    }

    public void logout() {
        if (clienteActual != null) {
            System.out.println("[AUTH] Hasta luego, " + clienteActual.getNombre() + ".");
            clienteActual = null;
        }
    }

    public boolean estaLogueado() {
        return clienteActual != null;
    }

    public Cliente getClienteActual() {
        return clienteActual;
    }
}
