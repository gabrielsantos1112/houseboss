CREATE TABLE atendimentos (
    id serial PRIMARY KEY,
    descricao_atendimento TEXT,
    data_horario TIMESTAMP NOT NULL,
    tipo_pagamento VARCHAR(50) NOT NULL,
    status_pagamento VARCHAR(50) NOT NULL,
    valor_atendimento NUMERIC(10, 2) NOT NULL,
    cliente_id INT NOT NULL REFERENCES clientes(id),
    funcionario_id INT NOT NULL REFERENCES funcionarios(id)
);