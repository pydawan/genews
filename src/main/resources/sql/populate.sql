-- ===============================================================================================
-- script: insert.sql
-- sistema: genews
-- autor: thiago-amm
-- local: Goiânia
-- data: 10/01/2018
--
-- descrição:
--  Script SQL de inserção de dados do genews.
-- =================================================================================================

use genews;

-- usuarios
insert into usuarios (id, nome, sobrenome, email, login, senha, ativo, admin, data_cadastro) 
values (1, 'CI', 'Coordenação de Informática', 'informatica@agr.go.gov.br', 'admin', md5('admin123.'), 1, 1, now());

insert into usuarios (id, nome, sobrenome, email, login, senha, ativo, admin, data_cadastro)
values (2, 'GECOM', 'Gerência de Comunicação', 'comunicacaoagr@gmail.com', 'gecom', md5('comunicaagr'), 1, 0, now());

-- newsletters
insert into newsletters values (1, 'Comunica AGR', 'comunicacaoagr@gmail.com', 'Gerência de Comunicação', '2014-09-12 16:00:45', 2);

