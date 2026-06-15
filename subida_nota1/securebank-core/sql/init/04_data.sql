-- =============================================================================
-- DATOS DE PRUEBA
-- =============================================================================

-- Clientes (PIN: 1234 para todos - hash SHA256 de ejemplo)
INSERT INTO clientes (dni, nombre, apellidos, email, telefono, pin_hash, estado) VALUES
('12345678A', 'Juan', 'García López', 'juan.garcia@email.com', '600111222', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ACTIVO'),
('23456789B', 'María', 'Fernández Ruiz', 'maria.fernandez@email.com', '600222333', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ACTIVO'),
('34567890C', 'Carlos', 'Martínez Sanz', 'carlos.martinez@email.com', '600333444', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ACTIVO'),
('45678901D', 'Ana', 'López Vega', 'ana.lopez@email.com', '600444555', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ACTIVO'),
('56789012E', 'Pedro', 'Sánchez Gil', 'pedro.sanchez@email.com', '600555666', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ACTIVO'),
('67890123F', 'Laura', 'Rodríguez Paz', 'laura.rodriguez@email.com', '600666777', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'BLOQUEADO'),
('78901234G', 'Miguel', 'Hernández Sol', 'miguel.hernandez@email.com', '600777888', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ACTIVO'),
('89012345H', 'Sara', 'Jiménez Luna', 'sara.jimenez@email.com', '600888999', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ACTIVO'),
('90123456J', 'David', 'Moreno Cruz', 'david.moreno@email.com', '600999000', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ACTIVO'),
('01234567K', 'Elena', 'Díaz Ramos', 'elena.diaz@email.com', '601000111', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'BAJA');

-- Cuentas bancarias
INSERT INTO cuentas_bancarias (iban, cliente_id, tipo, saldo, estado) VALUES
('ES1234567890123456789012', 1, 'CORRIENTE', 5000.00, 'ACTIVA'),
('ES2345678901234567890123', 1, 'AHORRO', 15000.00, 'ACTIVA'),
('ES3456789012345678901234', 2, 'CORRIENTE', 3500.00, 'ACTIVA'),
('ES4567890123456789012345', 2, 'NOMINA', 2800.00, 'ACTIVA'),
('ES5678901234567890123456', 3, 'CORRIENTE', 12000.00, 'ACTIVA'),
('ES6789012345678901234567', 4, 'CORRIENTE', 850.00, 'ACTIVA'),
('ES7890123456789012345678', 5, 'AHORRO', 25000.00, 'ACTIVA'),
('ES8901234567890123456789', 5, 'CORRIENTE', 4200.00, 'ACTIVA'),
('ES9012345678901234567890', 6, 'CORRIENTE', 150.00, 'BLOQUEADA'),
('ES0123456789012345678901', 7, 'CORRIENTE', 8900.00, 'ACTIVA'),
('ES1111222233334444555566', 7, 'AHORRO', 45000.00, 'ACTIVA'),
('ES2222333344445555666677', 8, 'CORRIENTE', 3200.00, 'ACTIVA'),
('ES3333444455556666777788', 8, 'NOMINA', 1800.00, 'ACTIVA'),
('ES4444555566667777888899', 9, 'CORRIENTE', 6500.00, 'ACTIVA'),
('ES5555666677778888999900', 9, 'AHORRO', 18000.00, 'ACTIVA'),
('ES6666777788889999000011', 3, 'AHORRO', 7500.00, 'ACTIVA'),
('ES7777888899990000111122', 4, 'AHORRO', 2100.00, 'ACTIVA'),
('ES8888999900001111222233', 1, 'NOMINA', 3200.00, 'ACTIVA'),
('ES9999000011112222333344', 2, 'AHORRO', 9800.00, 'ACTIVA'),
('ES0000111122223333444455', 5, 'NOMINA', 2750.00, 'ACTIVA');

-- Tarjetas de crédito
INSERT INTO tarjetas_credito (cuenta_id, numero, cvv_hash, limite_credito, saldo_dispuesto, fecha_caducidad, estado) VALUES
(1, '4111111111111111', 'hash_cvv_1', 3000.00, 500.00, '2028-12-31', 'ACTIVA'),
(3, '4222222222222222', 'hash_cvv_2', 5000.00, 1200.00, '2027-06-30', 'ACTIVA'),
(5, '4333333333333333', 'hash_cvv_3', 10000.00, 3500.00, '2028-03-31', 'ACTIVA'),
(7, '4444444444444444', 'hash_cvv_4', 2000.00, 0.00, '2026-09-30', 'ACTIVA'),
(10, '4555555555555555', 'hash_cvv_5', 8000.00, 2800.00, '2027-12-31', 'BLOQUEADA');

-- Préstamos
INSERT INTO prestamos (cliente_id, cuenta_id, capital_inicial, capital_pendiente, interes_anual, cuotas_totales, cuotas_pagadas, cuota_mensual, fecha_vencimiento, estado) VALUES
(1, 1, 10000.00, 8500.00, 5.5, 24, 6, 450.00, '2028-01-15', 'VIGENTE'),
(3, 5, 25000.00, 22000.00, 4.8, 60, 12, 480.00, '2030-06-01', 'VIGENTE'),
(5, 7, 50000.00, 0.00, 3.9, 120, 120, 520.00, '2025-03-01', 'LIQUIDADO'),
(7, 10, 15000.00, 14200.00, 6.2, 36, 4, 465.00, '2029-02-15', 'VIGENTE'),
(9, 14, 8000.00, 8000.00, 7.5, 18, 0, 495.00, '2027-12-01', 'IMPAGADO');

-- Transacciones de ejemplo (últimos 30 días simulados)
INSERT INTO transacciones (cuenta_id, tipo, importe, saldo_posterior, concepto, fecha) VALUES
(1, 'INGRESO', 2500.00, 2500.00, 'Nómina Junio', NOW() - INTERVAL '25 days'),
(1, 'RETIRO', -200.00, 2300.00, 'Cajero automático', NOW() - INTERVAL '22 days'),
(1, 'TRANSFERENCIA_SALIDA', -150.00, 2150.00, 'Pago alquiler', NOW() - INTERVAL '20 days'),
(3, 'INGRESO', 1800.00, 1800.00, 'Transferencia recibida', NOW() - INTERVAL '18 days'),
(3, 'COMISION', -2.00, 1798.00, 'Comisión mantenimiento', NOW() - INTERVAL '15 days'),
(5, 'INGRESO', 5000.00, 5000.00, 'Ingreso en ventanilla', NOW() - INTERVAL '12 days'),
(5, 'RETIRO', -500.00, 4500.00, 'Cajero automático', NOW() - INTERVAL '10 days'),
(7, 'INGRESO', 3000.00, 3000.00, 'Nómina', NOW() - INTERVAL '8 days'),
(7, 'PAGO_PRESTAMO', -450.00, 2550.00, 'Cuota préstamo #1', NOW() - INTERVAL '5 days'),
(10, 'INGRESO', 4500.00, 4500.00, 'Ingreso efectivo', NOW() - INTERVAL '3 days'),
(1, 'RETIRO', -100.00, 5000.00, 'Cajero', NOW() - INTERVAL '2 days'),
(1, 'INGRESO', 2600.00, 7600.00, 'Nómina Julio', NOW() - INTERVAL '1 day');

-- Más transacciones para simular actividad
DO $$
DECLARE
    i INTEGER;
    cuenta_random INTEGER;
    tipo_random TEXT;
    importe_random DECIMAL;
BEGIN
    FOR i IN 1..88 LOOP
        cuenta_random := (SELECT id FROM cuentas_bancarias WHERE estado = 'ACTIVA' ORDER BY RANDOM() LIMIT 1);
        tipo_random := (ARRAY['INGRESO', 'RETIRO', 'COMISION'])[floor(random() * 3 + 1)];
        importe_random := round((random() * 500 + 10)::numeric, 2);
        
        IF tipo_random != 'INGRESO' THEN
            importe_random := -importe_random;
        END IF;
        
        INSERT INTO transacciones (cuenta_id, tipo, importe, saldo_posterior, concepto, fecha)
        VALUES (
            cuenta_random,
            tipo_random,
            importe_random,
            0, -- Se actualizará por trigger
            'Operación automática #' || i,
            NOW() - (random() * 60 || ' days')::interval
        );
    END LOOP;
END $$;

-- Actualizar saldos_posteriores basándose en el saldo actual
UPDATE transacciones t
SET saldo_posterior = (SELECT saldo FROM cuentas_bancarias WHERE id = t.cuenta_id);
