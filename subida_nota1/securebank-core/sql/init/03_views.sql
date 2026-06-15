-- =============================================================================
-- VISTAS
-- =============================================================================

-- Vista resumen de cliente
CREATE OR REPLACE VIEW v_resumen_cliente AS
SELECT 
    c.id AS cliente_id,
    c.dni,
    c.nombre || ' ' || c.apellidos AS nombre_completo,
    c.estado AS estado_cliente,
    COUNT(DISTINCT cb.id) AS total_cuentas,
    COALESCE(SUM(cb.saldo), 0) AS saldo_global,
    COUNT(DISTINCT CASE WHEN p.estado = 'VIGENTE' THEN p.id END) AS prestamos_activos,
    COALESCE(SUM(CASE WHEN p.estado = 'VIGENTE' THEN p.capital_pendiente ELSE 0 END), 0) AS deuda_total,
    COUNT(DISTINCT tc.id) AS total_tarjetas
FROM clientes c
LEFT JOIN cuentas_bancarias cb ON c.id = cb.cliente_id AND cb.estado = 'ACTIVA'
LEFT JOIN prestamos p ON c.id = p.cliente_id
LEFT JOIN tarjetas_credito tc ON cb.id = tc.cuenta_id AND tc.estado = 'ACTIVA'
GROUP BY c.id, c.dni, c.nombre, c.apellidos, c.estado;

-- Vista de movimientos recientes
CREATE OR REPLACE VIEW v_movimientos_recientes AS
SELECT 
    t.id AS transaccion_id,
    c.dni AS cliente_dni,
    cb.iban,
    t.tipo,
    t.importe,
    t.saldo_posterior,
    t.concepto,
    t.fecha
FROM transacciones t
JOIN cuentas_bancarias cb ON t.cuenta_id = cb.id
JOIN clientes c ON cb.cliente_id = c.id
ORDER BY t.fecha DESC;

-- Vista de alertas de fraude
CREATE OR REPLACE VIEW v_alertas_fraude AS
SELECT 
    af.id,
    c.dni,
    c.nombre || ' ' || c.apellidos AS cliente,
    cb.iban,
    af.tipo_fraude,
    af.descripcion,
    af.fecha_deteccion,
    af.accion_tomada
FROM auditoria_fraude af
JOIN clientes c ON af.cliente_id = c.id
JOIN cuentas_bancarias cb ON af.cuenta_id = cb.id
ORDER BY af.fecha_deteccion DESC;
