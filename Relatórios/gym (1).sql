-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 31-Out-2020 às 14:35
-- Versão do servidor: 5.7.31
-- versão do PHP: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `gym`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `auth_assignment`
--

DROP TABLE IF EXISTS `auth_assignment`;
CREATE TABLE IF NOT EXISTS `auth_assignment` (
  `item_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` int(11) DEFAULT NULL,
  PRIMARY KEY (`item_name`,`user_id`),
  KEY `idx-auth_assignment-user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Extraindo dados da tabela `auth_assignment`
--

INSERT INTO `auth_assignment` (`item_name`, `user_id`, `created_at`) VALUES
('admin', '1', 1603462880),
('author', '2', 1603462880);

-- --------------------------------------------------------

--
-- Estrutura da tabela `auth_item`
--

DROP TABLE IF EXISTS `auth_item`;
CREATE TABLE IF NOT EXISTS `auth_item` (
  `name` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `type` smallint(6) NOT NULL,
  `description` text COLLATE utf8_unicode_ci,
  `rule_name` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `data` blob,
  `created_at` int(11) DEFAULT NULL,
  `updated_at` int(11) DEFAULT NULL,
  PRIMARY KEY (`name`),
  KEY `rule_name` (`rule_name`),
  KEY `idx-auth_item-type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Extraindo dados da tabela `auth_item`
--

INSERT INTO `auth_item` (`name`, `type`, `description`, `rule_name`, `data`, `created_at`, `updated_at`) VALUES
('admin', 1, NULL, NULL, NULL, 1603462880, 1603462880),
('author', 1, NULL, NULL, NULL, 1603462880, 1603462880),
('createPost', 2, 'Create a post', NULL, NULL, 1603462880, 1603462880),
('updatePost', 2, 'Update post', NULL, NULL, 1603462880, 1603462880);

-- --------------------------------------------------------

--
-- Estrutura da tabela `auth_item_child`
--

DROP TABLE IF EXISTS `auth_item_child`;
CREATE TABLE IF NOT EXISTS `auth_item_child` (
  `parent` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `child` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`parent`,`child`),
  KEY `child` (`child`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Extraindo dados da tabela `auth_item_child`
--

INSERT INTO `auth_item_child` (`parent`, `child`) VALUES
('admin', 'author'),
('author', 'createPost'),
('admin', 'updatePost');

-- --------------------------------------------------------

--
-- Estrutura da tabela `auth_rule`
--

DROP TABLE IF EXISTS `auth_rule`;
CREATE TABLE IF NOT EXISTS `auth_rule` (
  `name` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `data` blob,
  `created_at` int(11) DEFAULT NULL,
  `updated_at` int(11) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cargo`
--

DROP TABLE IF EXISTS `cargo`;
CREATE TABLE IF NOT EXISTS `cargo` (
  `IDCargo` int(11) NOT NULL AUTO_INCREMENT,
  `cargo` varchar(255) NOT NULL,
  PRIMARY KEY (`IDCargo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `User_id` int(11) NOT NULL,
  `primeiroNome` varchar(50) NOT NULL,
  `apelido` varchar(50) NOT NULL,
  `dt_nascimento` date NOT NULL,
  `sexo` varchar(50) NOT NULL,
  `IDCliente` int(11) NOT NULL AUTO_INCREMENT,
  `avatar` blob NOT NULL,
  `nif` int(9) NOT NULL,
  `num_tele` int(9) NOT NULL,
  PRIMARY KEY (`IDCliente`),
  KEY `User_id` (`User_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `desconto`
--

DROP TABLE IF EXISTS `desconto`;
CREATE TABLE IF NOT EXISTS `desconto` (
  `IDDesconto` int(11) NOT NULL AUTO_INCREMENT,
  `quantidade` float NOT NULL,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`IDDesconto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `ementa`
--

DROP TABLE IF EXISTS `ementa`;
CREATE TABLE IF NOT EXISTS `ementa` (
  `IDEmenta` int(11) NOT NULL AUTO_INCREMENT,
  `PequenoAlmoco` varchar(255) DEFAULT NULL,
  `Almoco` varchar(255) DEFAULT NULL,
  `Lanche1` varchar(255) DEFAULT NULL,
  `Lanche2` varchar(255) DEFAULT NULL,
  `Jantar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDEmenta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
CREATE TABLE IF NOT EXISTS `funcionario` (
  `User_id` int(11) NOT NULL,
  `primeiroNome` varchar(50) NOT NULL,
  `apelido` varchar(50) NOT NULL,
  `dt_nascimento` date NOT NULL,
  `sexo` varchar(50) NOT NULL,
  `IDFuncionario` int(11) NOT NULL AUTO_INCREMENT,
  `cargo_id` int(11) NOT NULL,
  `avatar` blob NOT NULL,
  `num_tele` int(9) NOT NULL,
  PRIMARY KEY (`IDFuncionario`),
  KEY `User_id` (`User_id`),
  KEY `cargo_id` (`cargo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `lista_planos`
--

DROP TABLE IF EXISTS `lista_planos`;
CREATE TABLE IF NOT EXISTS `lista_planos` (
  `IDPlano` int(11) NOT NULL,
  `IDCliente` int(11) NOT NULL,
  KEY `id_cliente` (`IDCliente`),
  KEY `IDPlano` (`IDPlano`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `migration`
--

DROP TABLE IF EXISTS `migration`;
CREATE TABLE IF NOT EXISTS `migration` (
  `version` varchar(180) NOT NULL,
  `apply_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`version`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `migration`
--

INSERT INTO `migration` (`version`, `apply_time`) VALUES
('m000000_000000_base', 1602599430),
('m130524_201442_init', 1602599433),
('m190124_110200_add_verification_token_column_to_user_table', 1602599434),
('m140506_102106_rbac_init', 1603461500),
('m170907_052038_rbac_add_index_on_auth_assignment_user_id', 1603461500),
('m180523_151638_rbac_updates_indexes_without_prefix', 1603461500),
('m200409_110543_rbac_update_mssql_trigger', 1603461500),
('m201023_140556_init_rbac', 1603462880);

-- --------------------------------------------------------

--
-- Estrutura da tabela `planonutricao`
--

DROP TABLE IF EXISTS `planonutricao`;
CREATE TABLE IF NOT EXISTS `planonutricao` (
  `IDPlanoNutricao` int(11) NOT NULL AUTO_INCREMENT,
  `Segunda` int(11) DEFAULT NULL,
  `Terca` int(11) DEFAULT NULL,
  `Quarta` int(11) DEFAULT NULL,
  `Quinta` int(11) DEFAULT NULL,
  `Sexta` int(11) DEFAULT NULL,
  `Sabado` int(11) DEFAULT NULL,
  `IDNutricionista` int(11) NOT NULL,
  PRIMARY KEY (`IDPlanoNutricao`),
  KEY `Segunda` (`Segunda`),
  KEY `Terca` (`Terca`),
  KEY `Quarta` (`Quarta`),
  KEY `Quinta` (`Quinta`),
  KEY `Sexta` (`Sexta`),
  KEY `Sabado` (`Sabado`),
  KEY `IDNutricionista` (`IDNutricionista`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `planos_treino`
--

DROP TABLE IF EXISTS `planos_treino`;
CREATE TABLE IF NOT EXISTS `planos_treino` (
  `IDPlanoTreino` int(11) NOT NULL AUTO_INCREMENT,
  `nome_exercicio` varchar(255) NOT NULL,
  `repeticoes` int(11) DEFAULT NULL,
  `tempo` time DEFAULT NULL,
  `serie` int(11) NOT NULL,
  `repouso` time NOT NULL,
  `tempo_total` time NOT NULL,
  `num_maquina` int(11) DEFAULT NULL,
  `id_cliente` int(11) NOT NULL,
  PRIMARY KEY (`IDPlanoTreino`),
  KEY `id_cliente` (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `subscricao`
--

DROP TABLE IF EXISTS `subscricao`;
CREATE TABLE IF NOT EXISTS `subscricao` (
  `IDSubscricao` int(11) NOT NULL AUTO_INCREMENT,
  `preco` double NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_desconto` int(11) NOT NULL,
  `id_tipo` int(11) NOT NULL,
  `data_subscricao` date NOT NULL,
  `data_expirar` date NOT NULL,
  PRIMARY KEY (`IDSubscricao`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_desconto` (`id_desconto`),
  KEY `id_tipo` (`id_tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipo_subscricao`
--

DROP TABLE IF EXISTS `tipo_subscricao`;
CREATE TABLE IF NOT EXISTS `tipo_subscricao` (
  `IDTipoSubscricao` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(255) NOT NULL,
  PRIMARY KEY (`IDTipoSubscricao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `auth_key` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `password_hash` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password_reset_token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` smallint(6) NOT NULL DEFAULT '10',
  `created_at` int(11) NOT NULL,
  `updated_at` int(11) NOT NULL,
  `verification_token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `password_reset_token` (`password_reset_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `auth_assignment`
--
ALTER TABLE `auth_assignment`
  ADD CONSTRAINT `auth_assignment_ibfk_1` FOREIGN KEY (`item_name`) REFERENCES `auth_item` (`name`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `auth_item`
--
ALTER TABLE `auth_item`
  ADD CONSTRAINT `auth_item_ibfk_1` FOREIGN KEY (`rule_name`) REFERENCES `auth_rule` (`name`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Limitadores para a tabela `auth_item_child`
--
ALTER TABLE `auth_item_child`
  ADD CONSTRAINT `auth_item_child_ibfk_1` FOREIGN KEY (`parent`) REFERENCES `auth_item` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `auth_item_child_ibfk_2` FOREIGN KEY (`child`) REFERENCES `auth_item` (`name`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`);

--
-- Limitadores para a tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD CONSTRAINT `funcionario_ibfk_1` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `funcionario_ibfk_2` FOREIGN KEY (`cargo_id`) REFERENCES `cargo` (`IDCargo`);

--
-- Limitadores para a tabela `lista_planos`
--
ALTER TABLE `lista_planos`
  ADD CONSTRAINT `lista_planos_ibfk_1` FOREIGN KEY (`IDPlano`) REFERENCES `planos_treino` (`IDPlanoTreino`),
  ADD CONSTRAINT `lista_planos_ibfk_2` FOREIGN KEY (`IDCliente`) REFERENCES `cliente` (`IDCliente`),
  ADD CONSTRAINT `lista_planos_ibfk_3` FOREIGN KEY (`IDPlano`) REFERENCES `planonutricao` (`IDPlanoNutricao`);

--
-- Limitadores para a tabela `planonutricao`
--
ALTER TABLE `planonutricao`
  ADD CONSTRAINT `planonutricao_ibfk_1` FOREIGN KEY (`Segunda`) REFERENCES `ementa` (`IDEmenta`),
  ADD CONSTRAINT `planonutricao_ibfk_2` FOREIGN KEY (`Terca`) REFERENCES `ementa` (`IDEmenta`),
  ADD CONSTRAINT `planonutricao_ibfk_3` FOREIGN KEY (`Quarta`) REFERENCES `ementa` (`IDEmenta`),
  ADD CONSTRAINT `planonutricao_ibfk_4` FOREIGN KEY (`Quinta`) REFERENCES `ementa` (`IDEmenta`),
  ADD CONSTRAINT `planonutricao_ibfk_5` FOREIGN KEY (`Sexta`) REFERENCES `ementa` (`IDEmenta`),
  ADD CONSTRAINT `planonutricao_ibfk_6` FOREIGN KEY (`Sabado`) REFERENCES `ementa` (`IDEmenta`),
  ADD CONSTRAINT `planonutricao_ibfk_7` FOREIGN KEY (`IDNutricionista`) REFERENCES `funcionario` (`IDFuncionario`);

--
-- Limitadores para a tabela `planos_treino`
--
ALTER TABLE `planos_treino`
  ADD CONSTRAINT `planos_treino_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`IDCliente`);

--
-- Limitadores para a tabela `subscricao`
--
ALTER TABLE `subscricao`
  ADD CONSTRAINT `subscricao_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`IDCliente`),
  ADD CONSTRAINT `subscricao_ibfk_2` FOREIGN KEY (`id_desconto`) REFERENCES `desconto` (`IDDesconto`),
  ADD CONSTRAINT `subscricao_ibfk_3` FOREIGN KEY (`id_tipo`) REFERENCES `tipo_subscricao` (`IDTipoSubscricao`);

--
-- Limitadores para a tabela `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`id`) REFERENCES `funcionario` (`User_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
