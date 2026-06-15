package es.securebank.core.dao.impl;

import es.securebank.core.dao.ClienteDao;
import es.securebank.core.model.Cliente;
import es.securebank.core.model.enums.EstadoCliente;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteDaoJdbc implements ClienteDao {
    private final DataSource dataSource;

    public ClienteDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Cliente mapRow(ResultSet rs) throws SQLException {
        Cliente c = new Cliente();
        c.setId(rs.getInt("id"));
        c.setDni(rs.getString("dni"));
        c.setNombre(rs.getString("nombre"));
        c.setApellidos(rs.getString("apellidos"));
        c.setEmail(rs.getString("email"));
        c.setTelefono(rs.getString("telefono"));
        c.setPinHash(rs.getString("pin_hash"));
        c.setFechaAlta(rs.getTimestamp("fecha_alta").toInstant()
                .atZone(java.time.ZoneId.systemDefault()));
        c.setEstado(EstadoCliente.valueOf(rs.getString("estado")));
        return c;
    }

    @Override
    public Optional<Cliente> findById(Integer id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(mapRow(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando cliente por id", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Cliente> findByDni(String dni) {
        String sql = "SELECT * FROM clientes WHERE dni = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(mapRow(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando cliente por DNI", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Cliente> findByEmail(String email) {
        String sql = "SELECT * FROM clientes WHERE email = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(mapRow(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando cliente por email", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection conn = dataSource.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapRow(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Error listando clientes", e);
        }
        return lista;
    }

    @Override
    public void save(Cliente c) {
        String sql = "INSERT INTO clientes (dni, nombre, apellidos, email, telefono, pin_hash, estado) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getDni());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getApellidos());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getTelefono());
            ps.setString(6, c.getPinHash());
            ps.setString(7, c.getEstado().name());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error guardando cliente", e);
        }
    }

    @Override
    public void update(Cliente c) {
        String sql = "UPDATE clientes SET nombre=?, apellidos=?, email=?, telefono=?, estado=? WHERE id=?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellidos());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getTelefono());
            ps.setString(5, c.getEstado().name());
            ps.setInt(6, c.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando cliente", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando cliente", e);
        }
    }
}
