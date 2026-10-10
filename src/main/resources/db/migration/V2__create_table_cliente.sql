CREATE TABLE clientes (
    id serial PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    numero VARCHAR(255) NOT NULL UNIQUE
);