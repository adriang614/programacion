-- =============================================================================
-- TRIGGERS Y FUNCIONES
-- =============================================================================

-- Función para actualizar fecha_modificacion en clientes
CREATE OR REPLACE FUNCTION fn_actualizar_fecha_modificacion()
RETURNS TRIGGER AS $$
BEGIN
    NEW.fecha_modificacion = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_clientes_modificacion
    BEFORE UPDATE ON clientes
    FOR EACH ROW
    EXECUTE FUNCTION fn_actualizar_fecha_modificacion();

-- Función para validar saldo suficiente antes de transacción
CREATE OR REPLACE FUNCTION fn_validar_transaccion()
RETURNS TRIGGER AS $$
DECLARE
    v_saldo_actual DECIMAL(15, 2);
    v_estado_cuenta VARCHAR(20);
BEGIN
    -- Obtener estado y saldo actual
    SELECT saldo, estado INTO v_saldo_actual, v_estado_cuenta
    FROM cuentas_bancarias
    WHERE id = NEW.cuenta_id;
    
    -- Verificar que la cuenta no está bloqueada
    IF v_estado_cuenta != 'ACTIVA' THEN
        RAISE EXCEPTION 'La cuenta no está activa. Estado actual: %', v_estado_cuenta;
    END IF;
    
    -- Para retiros y transferencias salientes, verificar saldo
    IF NEW.tipo IN ('RETIRO', 'TRANSFERENCIA_SALIDA', 'COMISION', 'PAGO_PRESTAMO') THEN
        IF v_saldo_actual < ABS(NEW.importe) THEN
            RAISE EXCEPTION 'Saldo insuficiente. Disponible: %, Requerido: %', 
                v_saldo_actual, ABS(NEW.importe);
        END IF;
    END IF;
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_validar_transaccion
    BEFORE INSERT ON transacciones
    FOR EACH ROW
    EXECUTE FUNCTION fn_validar_transaccion();

-- Función para actualizar saldo tras transacción
CREATE OR REPLACE FUNCTION fn_actualizar_saldo()
RETURNS TRIGGER AS $$
BEGIN
    -- Actualizar saldo de la cuenta origen
    IF NEW.tipo IN ('INGRESO', 'TRANSFERENCIA_ENTRADA', 'DEVOLUCION', 'INTERES') THEN
        UPDATE cuentas_bancarias 
        SET saldo = saldo + ABS(NEW.importe)
        WHERE id = NEW.cuenta_id;
    ELSIF NEW.tipo IN ('RETIRO', 'TRANSFERENCIA_SALIDA', 'COMISION', 'PAGO_PRESTAMO') THEN
        UPDATE cuentas_bancarias 
        SET saldo = saldo - ABS(NEW.importe)
        WHERE id = NEW.cuenta_id;
    END IF;
    
    -- Actualizar saldo_posterior en la transacción
    SELECT saldo INTO NEW.saldo_posterior
    FROM cuentas_bancarias
    WHERE id = NEW.cuenta_id;
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_actualizar_saldo
    AFTER INSERT ON transacciones
    FOR EACH ROW
    EXECUTE FUNCTION fn_actualizar_saldo();
