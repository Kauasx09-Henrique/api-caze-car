-- =====================================================================
-- V1 — Schema inicial da Loja de Carros
-- Modelagem em 3ª Forma Normal (3FN)
-- =====================================================================

-- ---------------------------------------------------------------------
-- Tabelas de referência (catálogo)
-- ---------------------------------------------------------------------

CREATE TABLE marca (
    id   BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE modelo (
    id       BIGSERIAL PRIMARY KEY,
    marca_id BIGINT       NOT NULL REFERENCES marca (id),
    nome     VARCHAR(100) NOT NULL,
    CONSTRAINT uq_modelo_marca_nome UNIQUE (marca_id, nome)
);

CREATE TABLE combustivel (
    id   BIGSERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE cambio (
    id   BIGSERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE carroceria (
    id   BIGSERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE cor (
    id   BIGSERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);

-- ---------------------------------------------------------------------
-- Usuário (ADMIN, VENDEDOR, CLIENTE)
-- ---------------------------------------------------------------------

CREATE TABLE usuario (
    id           BIGSERIAL PRIMARY KEY,
    nome         VARCHAR(150) NOT NULL,
    email        VARCHAR(150) NOT NULL UNIQUE,
    senha        VARCHAR(255) NOT NULL,
    telefone     VARCHAR(20),
    cpf          VARCHAR(11)  NOT NULL UNIQUE,
    tipo_usuario VARCHAR(20)  NOT NULL,
    ativo        BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ---------------------------------------------------------------------
-- Veículos
-- ---------------------------------------------------------------------

CREATE TABLE veiculo (
    id             BIGSERIAL PRIMARY KEY,
    vendedor_id    BIGINT        NOT NULL REFERENCES usuario (id),
    modelo_id      BIGINT        NOT NULL REFERENCES modelo (id),
    combustivel_id BIGINT        NOT NULL REFERENCES combustivel (id),
    cambio_id      BIGINT        NOT NULL REFERENCES cambio (id),
    carroceria_id  BIGINT        NOT NULL REFERENCES carroceria (id),
    cor_id         BIGINT        NOT NULL REFERENCES cor (id),
    ano_fabricacao SMALLINT      NOT NULL,
    ano_modelo     SMALLINT      NOT NULL,
    quilometragem  INTEGER       NOT NULL,
    preco          NUMERIC(12,2) NOT NULL,
    placa          VARCHAR(7)    NOT NULL UNIQUE,
    descricao      TEXT,
    status         VARCHAR(20)   NOT NULL,
    created_at     TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE veiculo_foto (
    id         BIGSERIAL PRIMARY KEY,
    veiculo_id BIGINT   NOT NULL REFERENCES veiculo (id) ON DELETE CASCADE,
    url        TEXT     NOT NULL,
    ordem      SMALLINT NOT NULL DEFAULT 0,
    principal  BOOLEAN  NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ---------------------------------------------------------------------
-- Interações
-- ---------------------------------------------------------------------

CREATE TABLE favorito (
    id         BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT    NOT NULL REFERENCES usuario (id) ON DELETE CASCADE,
    veiculo_id BIGINT    NOT NULL REFERENCES veiculo (id) ON DELETE CASCADE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_favorito_usuario_veiculo UNIQUE (usuario_id, veiculo_id)
);

CREATE TABLE proposta (
    id         BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT        NOT NULL REFERENCES usuario (id),
    veiculo_id BIGINT        NOT NULL REFERENCES veiculo (id),
    valor      NUMERIC(12,2) NOT NULL,
    observacao TEXT,
    status     VARCHAR(20)   NOT NULL,
    created_at TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE conversa (
    id          BIGSERIAL PRIMARY KEY,
    usuario_id  BIGINT      NOT NULL REFERENCES usuario (id),
    vendedor_id BIGINT      NOT NULL REFERENCES usuario (id),
    veiculo_id  BIGINT      NOT NULL REFERENCES veiculo (id),
    status      VARCHAR(20) NOT NULL,
    created_at  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE mensagem (
    id           BIGSERIAL PRIMARY KEY,
    conversa_id  BIGINT    NOT NULL REFERENCES conversa (id) ON DELETE CASCADE,
    remetente_id BIGINT    NOT NULL REFERENCES usuario (id),
    conteudo     TEXT      NOT NULL,
    lida         BOOLEAN   NOT NULL DEFAULT FALSE,
    created_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE notificacao (
    id         BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT       NOT NULL REFERENCES usuario (id) ON DELETE CASCADE,
    titulo     VARCHAR(150) NOT NULL,
    mensagem   TEXT         NOT NULL,
    tipo       VARCHAR(50)  NOT NULL,
    lida       BOOLEAN      NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE historico_veiculo (
    id         BIGSERIAL PRIMARY KEY,
    veiculo_id BIGINT      NOT NULL REFERENCES veiculo (id) ON DELETE CASCADE,
    usuario_id BIGINT      NOT NULL REFERENCES usuario (id),
    acao       VARCHAR(50) NOT NULL,
    descricao  TEXT,
    created_at TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ---------------------------------------------------------------------
-- Índices para chaves estrangeiras mais consultadas
-- ---------------------------------------------------------------------

CREATE INDEX idx_modelo_marca         ON modelo (marca_id);
CREATE INDEX idx_veiculo_vendedor     ON veiculo (vendedor_id);
CREATE INDEX idx_veiculo_modelo       ON veiculo (modelo_id);
CREATE INDEX idx_veiculo_status       ON veiculo (status);
CREATE INDEX idx_veiculo_foto_veiculo ON veiculo_foto (veiculo_id);
CREATE INDEX idx_favorito_usuario     ON favorito (usuario_id);
CREATE INDEX idx_proposta_veiculo     ON proposta (veiculo_id);
CREATE INDEX idx_proposta_usuario     ON proposta (usuario_id);
CREATE INDEX idx_conversa_veiculo     ON conversa (veiculo_id);
CREATE INDEX idx_mensagem_conversa    ON mensagem (conversa_id);
CREATE INDEX idx_notificacao_usuario  ON notificacao (usuario_id);
CREATE INDEX idx_historico_veiculo    ON historico_veiculo (veiculo_id);
