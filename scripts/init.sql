CREATE TABLE IF NOT EXISTS pedido (
    id SERIAL PRIMARY KEY,
    libro_id BIGINT NOT NULL,
    cantidad INT NOT NULL,
    nombre_comprador VARCHAR(255) NOT NULL,
    email_comprador VARCHAR(255) NOT NULL,
    fecha_compra TIMESTAMP NOT NULL
);

CREATE INDEX idx_pedido_libro_id ON pedido(libro_id);
