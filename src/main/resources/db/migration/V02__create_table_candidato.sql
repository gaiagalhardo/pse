CREATE TABLE candidato(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    registro BINARY(16) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    rg VARCHAR(20),
    email VARCHAR(100) NOT NULL,
    fone VARCHAR(20) NOT NULL,
    nome_pai VARCHAR(255),
    nome_mae VARCHAR(255) NOT NULL,
    data_nascimento DATETIME NOT NULL,
    data_cadastro DATETIME NOT NULL,
    data_atualizacao DATETIME NOT NULL,
    naturalidade VARCHAR(100),

    -- ENDERECO
    cep VARCHAR(9) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    cidade VARCHAR(200) NOT NULL,
    logradouro TEXT NOT NULL,
    complemento TEXT,
    numero VARCHAR(10),

    ativo BOOLEAN

);