CREATE TABLE curso (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    valor_gerado BINARY(16), -- Valor gerado de forma automatica para usar como refencia nos endpoints
    nome VARCHAR(255) NOT NULL,
    nivel_escolar VARCHAR(100) NOT NULL,
    duracao_anos DECIMAL(4,2),
    ativo BOOLEAN default true,
    data_cadastro_curso DATETIME NOT NULL,
    data_atualizacao_curso DATETIME NOT NULL

);