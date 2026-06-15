package es.securebank.core.dao.impl;

import es.securebank.core.dao.CuentaDao;
import es.securebank.core.model.CuentaBancaria;
import es.securebank.core.model.enums.EstadoCuenta;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CuentaDaoJdbc implements CuentaDao {
    private final DataSource dataSource;

    public CuentaDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private CuentaBancaria mapRow(ResultSet rs) throws SQLException {
        CuentaBancaria c = new CuentaBancaria();
        c.setId(rs.getInt("id"));
        c.setIban(rs.getString("iban"));
        c.setClienteId(rs.getInt("cliente_id"));
        c.setTipo(rs.getString("tipo"));
        c.setSaldo(rs.getBigDecimal("saldo"));
        c.setSaldoRetenido(rs.getBigDecimal("saldo_retenido"));
        c.setFechaApertura(rs.getTimestamp("fecha_apertura").toInstant()
                .atZone(java.time.ZoneId.systemDefault()));
        c.setEstado(EstadoCuenta.valueOf(rs.getString("estado")));
        return c;
    }

    @Override
    public Optional<CuentaBancaria> findByIban(String iban) {
        String sql = "SELECT * FROM cuentas_bancarias WHERE iban = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, iban);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(mapRow(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando cuenta por IBAN", e);
        }
        return Optional.empty();
    }

    @Override
    public List<CuentaBancaria> findByClienteId(int clienteId) {
        List<CuentaBancaria> lista = new ArrayList<>();
        String sql = "SELECT * FROM cuentas_bancarias WHERE cliente_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, clienteId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapRow(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando cuentas por cliente", e);
        }
        return lista;
    }

    @Override
    public List<CuentaBancaria> findAllActivas() {
        List<CuentaBancaria> lista = new ArrayList<>();
        String sql = "SELECT * FROM cuentas_bancarias WHERE estado = 'ACTIVA'";
        try (Connection conn = dataSource.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapRow(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Error listando cuentas activas", e);
        }
        return lista;
    }

    @Override
    public void updateSaldo(String iban, BigDecimal nuevoSaldo) {
        String sql = "UPDATE cuentas_bancarias SET saldo = ? WHERE iban = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, nuevoSaldo);
            ps.setString(2, iban);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando saldo", e);
        }
    }

    @Override
    public void updateEstado(String iban, String estado) {
        String sql = "UPDATE cuentas_bancarias SET estado = ? WHERE iban = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, estado);
            ps.setString(2, iban);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando estado de cuenta", e);
        }
    }

    @Override
    public Optional<CuentaBancaria> findById(Integer id) {
        String sql = "SELECT * FROM cuentas_bancarias WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(mapRow(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando cuenta por id", e);
        }
        return Optional.empty();
    }

    @Override
    public List<CuentaBancaria> findAll() {
        List<CuentaBancaria> lista = new ArrayList<>();
        String sql = "SELECT * FROM cuentas_bancarias";
        try (Connection conn = dataSource.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapRow(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Error listando cuentas", e);
        }
        return lista;
    }

    @Override
    public void save(CuentaBancaria c) {
        String sql = "INSERT INTO cuentas_bancarias (iban, cliente_id, tipo, saldo, saldo_retenido, estado) VALUES (?,?,?,?,?,?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getIban());
            ps.setInt(2, c.getClienteId());
            ps.setString(3, c.getTipo());
            ps.setBigDecimal(4, c.getSaldo());
            ps.setBigDecimal(5, c.getSaldoRetenido());
            ps.setString(6, c.getEstado().name());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error guardando cuenta", e);
        }
    }

    @Override
    public void update(CuentaBancaria c) {
        String sql = "UPDATE cuentas_bancarias SET saldo=?, saldo_retenido=?, estado=? WHERE iban=?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, c.getSaldo());
            ps.setBigDecimal(2, c.getSaldoRetenido());
            ps.setString(3, c.getEstado().name());
            ps.setString(4, c.getIban());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando cuenta", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM cuentas_bancarias WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando cuenta", e);
        }
    }
}
