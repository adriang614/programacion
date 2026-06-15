package es.securebank.core.dao.impl;

import es.securebank.core.dao.TransaccionDao;
import es.securebank.core.model.Transaccion;
import es.securebank.core.model.enums.TipoTransaccion;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransaccionDaoJdbc implements TransaccionDao {
    private final DataSource dataSource;

    public TransaccionDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Transaccion mapRow(ResultSet rs) throws SQLException {
        Transaccion t = new Transaccion();
        t.setId(rs.getInt("id"));
        t.setCuentaId(rs.getInt("cuenta_id"));
        int destino = rs.getInt("cuenta_destino_id");
        if (!rs.wasNull()) t.setCuentaDestinoId(destino);
        t.setTipo(TipoTransaccion.valueOf(rs.getString("tipo")));
        t.setImporte(rs.getBigDecimal("importe"));
        t.setSaldoPosterior(rs.getBigDecimal("saldo_posterior"));
        t.setConcepto(rs.getString("concepto"));
        t.setFecha(rs.getTimestamp("fecha").toInstant()
                .atZone(java.time.ZoneId.systemDefault()));
        t.setReferencia(rs.getString("referencia"));
        return t;
    }

    @Override
    public List<Transaccion> findByCuentaId(int cuentaId) {
        return findByCuentaId(cuentaId, 100);
    }

    @Override
    public List<Transaccion> findByCuentaId(int cuentaId, int limite) {
        List<Transaccion> lista = new ArrayList<>();
        String sql = "SELECT * FROM transacciones WHERE cuenta_id = ? ORDER BY fecha DESC LIMIT ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cuentaId);
            ps.setInt(2, limite);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapRow(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando transacciones", e);
        }
        return lista;
    }

    @Override
    public Optional<Transaccion> findById(Integer id) {
        String sql = "SELECT * FROM transacciones WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(mapRow(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando transaccion por id", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Transaccion> findAll() {
        List<Transaccion> lista = new ArrayList<>();
        String sql = "SELECT * FROM transacciones ORDER BY fecha DESC";
        try (Connection conn = dataSource.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapRow(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Error listando transacciones", e);
        }
        return lista;
    }

    @Override
    public void save(Transaccion t) {
        String sql = "INSERT INTO transacciones (cuenta_id, cuenta_destino_id, tipo, importe, saldo_posterior, concepto, referencia) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, t.getCuentaId());
            if (t.getCuentaDestinoId() != null) ps.setInt(2, t.getCuentaDestinoId());
            else ps.setNull(2, Types.INTEGER);
            ps.setString(3, t.getTipo().name());
            ps.setBigDecimal(4, t.getImporte());
            ps.setBigDecimal(5, t.getSaldoPosterior());
            ps.setString(6, t.getConcepto());
            ps.setString(7, t.getReferencia());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error guardando transaccion", e);
        }
    }

    @Override
    public void update(Transaccion t) {}

    @Override
    public void delete(Integer id) {}
}
