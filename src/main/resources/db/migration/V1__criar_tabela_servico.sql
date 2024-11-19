CREATE TABLE `clothes_servico` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `categoria` varchar(255) NOT NULL,
  `cor` varchar(255) NOT NULL,
  `marca` varchar(255) NOT NULL,
  `produto` varchar(255) NOT NULL,
  `tamanho` enum('G','GG','M','P','PP','X') NOT NULL,
  `tecido` varchar(255) NOT NULL,
  `tipo_de_aviamento` varchar(255) NOT NULL,
  `valor` decimal(38,2) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);