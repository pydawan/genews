-- ================================================================================================
-- script: uninstall.sql
-- sistema: genews
-- autor: thiago-amm
-- local: Goiânia
-- data: 10/01/2018
--
-- descrição:
--  Script SQL de desinstalação da estrutura básica de persistência de objetos do sistema genews.
--  Remove o banco de dados, o usuário admin do banco de dados, as tabelas e seus relacionamentos.
-- =================================================================================================

use mysql;

drop database genews;
drop user genews;

