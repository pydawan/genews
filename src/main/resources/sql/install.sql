-- ================================================================================================
-- script: install.sql
-- sistema: genews
-- autor: thiago-amm
-- local: Goiânia
-- data: 10/01/2018
--
-- descrição:
--  Script SQL de instalação da estrutura básica de persistência de objetos do sistema genews.
--  Cria o banco de dados, o usuário admin do banco de dados, as tabelas e seus relacionamentos.
-- =================================================================================================

use mysql;

create database genews default character set utf8 default collate utf8_general_ci;
grant all privileges on genews.* to 'genews'@'%' identified by 'genews';

flush privileges;

use genews;

CREATE TABLE IF NOT EXISTS newsletters (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL,
    remetente VARCHAR(50) NOT NULL,
    data_criacao DATETIME NOT NULL,
    autor_id INT NOT NULL COMMENT 'Usuário responsável pela criação da newsletter.'
);

CREATE TABLE IF NOT EXISTS noticias (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    conteudo TEXT NOT NULL,
    template_id INT NOT NULL,
    url VARCHAR(200) NOT NULL,
    intranet TINYINT(1) NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS templates (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    newsletter_id INT NOT NULL,
    edicao INT(11),
    autor_id INT NOT NULL COMMENT 'Usuário responsável pela geração do template da newsletter.',
    data_geracao DATETIME NOT NULL ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS usuarios (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    sobrenome VARCHAR(50) NOT NULL,
    email VARCHAR(50),
    login VARCHAR(50) NOT NULL,
    senha VARCHAR(50) NOT NULL,
    ativo TINYINT(1) NOT NULL DEFAULT 0,
    admin TINYINT(1) NOT NULL DEFAULT 0,
    data_cadastro DATETIME NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    data_inativacao DATETIME
);

ALTER TABLE newsletters ADD CONSTRAINT fk_newsletters_usuarios_autor FOREIGN KEY(autor_id) REFERENCES usuarios(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE noticias ADD CONSTRAINT fk_noticias_templates FOREIGN KEY(template_id) REFERENCES templates(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE templates ADD CONSTRAINT fk_templates_newsletters FOREIGN KEY(newsletter_id) REFERENCES newsletters(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE templates ADD CONSTRAINT fk_templates_usuarios_autor FOREIGN KEY(autor_id) REFERENCES usuarios(id) ON DELETE CASCADE ON UPDATE CASCADE;
