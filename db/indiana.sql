# HeidiSQL Dump 
#
# --------------------------------------------------------
# Host:                         127.0.0.1
# Database:                     indiana
# Server version:               5.0.27-community
# Server OS:                    Win32
# Target compatibility:         ANSI SQL
# HeidiSQL version:             4.0
# Date/time:                    2009-06-01 19:57:38
# --------------------------------------------------------

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ANSI,NO_BACKSLASH_ESCAPES';*/
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;*/


#
# Database structure for database 'indiana'
#

CREATE DATABASE /*!32312 IF NOT EXISTS*/ "indiana" /*!40100 DEFAULT CHARACTER SET utf8 */;

USE "indiana";


#
# Table structure for table 'acessorio'
#

CREATE TABLE /*!32312 IF NOT EXISTS*/ "acessorio" (
  "id" int(11) NOT NULL auto_increment,
  "descricao" longtext,
  "nome" varchar(60) NOT NULL,
  "preco" double default NULL,
  "id_tipo_de_acessorio" int(11) default NULL,
  PRIMARY KEY  ("id")
) AUTO_INCREMENT=12;



#
# Dumping data for table 'acessorio'
#

LOCK TABLES "acessorio" WRITE;
/*!40000 ALTER TABLE "acessorio" DISABLE KEYS;*/
REPLACE INTO "acessorio" ("id", "descricao", "nome", "preco", "id_tipo_de_acessorio") VALUES
	(1,'Roda de liga leve esportiva aro 18','Roda Aro 18','1400',1);
REPLACE INTO "acessorio" ("id", "descricao", "nome", "preco", "id_tipo_de_acessorio") VALUES
	(2,'Alarme com controle remoto','Alarme c/ controle','489',4);
REPLACE INTO "acessorio" ("id", "descricao", "nome", "preco", "id_tipo_de_acessorio") VALUES
	(3,'Kit Cambio Automatico','Cambio Automatico','4300',3);
REPLACE INTO "acessorio" ("id", "descricao", "nome", "preco", "id_tipo_de_acessorio") VALUES
	(4,'DVD Player 7" Retractil','DVD Player','998',2);
REPLACE INTO "acessorio" ("id", "descricao", "nome", "preco", "id_tipo_de_acessorio") VALUES
	(5,'GPS','GPS Automotivo','1899',3);
REPLACE INTO "acessorio" ("id", "descricao", "nome", "preco", "id_tipo_de_acessorio") VALUES
	(6,'Kit de Vidro Eletrico','Vidro Eletrico','1100',3);
REPLACE INTO "acessorio" ("id", "descricao", "nome", "preco", "id_tipo_de_acessorio") VALUES
	(7,'Kit de Direção Hidráulica','Direção Hidráulica','1999',3);
REPLACE INTO "acessorio" ("id", "descricao", "nome", "preco", "id_tipo_de_acessorio") VALUES
	(8,'Kit de Travas Elétricas','Travas Elétricas','349',4);
REPLACE INTO "acessorio" ("id", "descricao", "nome", "preco", "id_tipo_de_acessorio") VALUES
	(9,'Ar Condicionado','Ar Condicionado','1799',3);
REPLACE INTO "acessorio" ("id", "descricao", "nome", "preco", "id_tipo_de_acessorio") VALUES
	(10,'Computador de Bordo','Computador de Bordo','899',3);
REPLACE INTO "acessorio" ("id", "descricao", "nome", "preco", "id_tipo_de_acessorio") VALUES
	(11,'Teto Solar','Teto Solar','4000',3);
/*!40000 ALTER TABLE "acessorio" ENABLE KEYS;*/
UNLOCK TABLES;


#
# Table structure for table 'acessorio_carro'
#

CREATE TABLE /*!32312 IF NOT EXISTS*/ "acessorio_carro" (
  "carro_id" int(11) NOT NULL,
  "acessorio_id" int(11) NOT NULL
);



#
# Dumping data for table 'acessorio_carro'
#

LOCK TABLES "acessorio_carro" WRITE;
/*!40000 ALTER TABLE "acessorio_carro" DISABLE KEYS;*/
REPLACE INTO "acessorio_carro" ("carro_id", "acessorio_id") VALUES
	(3,2);
REPLACE INTO "acessorio_carro" ("carro_id", "acessorio_id") VALUES
	(2,1);
REPLACE INTO "acessorio_carro" ("carro_id", "acessorio_id") VALUES
	(2,2);
/*!40000 ALTER TABLE "acessorio_carro" ENABLE KEYS;*/
UNLOCK TABLES;


#
# Table structure for table 'carro'
#

CREATE TABLE /*!32312 IF NOT EXISTS*/ "carro" (
  "id" int(11) NOT NULL auto_increment,
  "descricao" longtext,
  "nome" varchar(60) NOT NULL,
  "id_marca" int(11) default NULL,
  PRIMARY KEY  ("id"),
  KEY "FK5A0E911EE080A8C" ("id_marca")
) AUTO_INCREMENT=9;



#
# Dumping data for table 'carro'
#

LOCK TABLES "carro" WRITE;
/*!40000 ALTER TABLE "carro" DISABLE KEYS;*/
REPLACE INTO "carro" ("id", "descricao", "nome", "id_marca") VALUES
	(2,'Ar, Direcao, Trio Eletrico, ABS, Ari Bag, MP3 Player e Computador de Bordo de Serie','Focus Hatch',1);
REPLACE INTO "carro" ("id", "descricao", "nome", "id_marca") VALUES
	(5,'Parachoques na cor do carro, Trava eletrica, Abertura eletrica do Porta malas de Serie','Ka',1);
REPLACE INTO "carro" ("id", "descricao", "nome", "id_marca") VALUES
	(6,'Ar, Direcao, Trio Eletrico, ABS, Ari Bag, MP3 Player e Computador de Bordo de Serie','Focus Sedam',1);
REPLACE INTO "carro" ("id", "descricao", "nome", "id_marca") VALUES
	(7,'Completo sem Teto Solar','Fusion',1);
REPLACE INTO "carro" ("id", "descricao", "nome", "id_marca") VALUES
	(8,'Direção e Ar Condicionado de Serie','EcoSport',1);
/*!40000 ALTER TABLE "carro" ENABLE KEYS;*/
UNLOCK TABLES;


#
# Table structure for table 'marca'
#

CREATE TABLE /*!32312 IF NOT EXISTS*/ "marca" (
  "id" int(11) NOT NULL auto_increment,
  "nome" varchar(60) NOT NULL,
  PRIMARY KEY  ("id")
) AUTO_INCREMENT=3;



#
# Dumping data for table 'marca'
#

LOCK TABLES "marca" WRITE;
/*!40000 ALTER TABLE "marca" DISABLE KEYS;*/
REPLACE INTO "marca" ("id", "nome") VALUES
	(1,'Ford');
REPLACE INTO "marca" ("id", "nome") VALUES
	(2,'Fiat');
/*!40000 ALTER TABLE "marca" ENABLE KEYS;*/
UNLOCK TABLES;


#
# Table structure for table 'modelo'
#

CREATE TABLE /*!32312 IF NOT EXISTS*/ "modelo" (
  "id" int(11) NOT NULL auto_increment,
  "descricao" longtext,
  "nome" varchar(60) NOT NULL,
  "preco" double default NULL,
  "id_carro" int(11) default NULL,
  PRIMARY KEY  ("id")
) AUTO_INCREMENT=14;



#
# Dumping data for table 'modelo'
#

LOCK TABLES "modelo" WRITE;
/*!40000 ALTER TABLE "modelo" DISABLE KEYS;*/
REPLACE INTO "modelo" ("id", "descricao", "nome", "preco", "id_carro") VALUES
	(1,'Gasolina','GHIA2.0L','66105',2);
REPLACE INTO "modelo" ("id", "descricao", "nome", "preco", "id_carro") VALUES
	(2,'Gasolina','GLX2.0L','55840',2);
REPLACE INTO "modelo" ("id", "descricao", "nome", "preco", "id_carro") VALUES
	(5,'Flex','1000L','24880',5);
REPLACE INTO "modelo" ("id", "descricao", "nome", "preco", "id_carro") VALUES
	(6,'Flex','1600L','31750',5);
REPLACE INTO "modelo" ("id", "descricao", "nome", "preco", "id_carro") VALUES
	(7,'Gasolina','2000GLX','57280',6);
REPLACE INTO "modelo" ("id", "descricao", "nome", "preco", "id_carro") VALUES
	(8,'Galosina','2000GHIA','67550',6);
REPLACE INTO "modelo" ("id", "descricao", "nome", "preco", "id_carro") VALUES
	(9,'Flex','XL1.6L','50340',8);
REPLACE INTO "modelo" ("id", "descricao", "nome", "preco", "id_carro") VALUES
	(10,'Flex','XLS1.6L','54185',8);
REPLACE INTO "modelo" ("id", "descricao", "nome", "preco", "id_carro") VALUES
	(11,'Flex','XLT1.6L','57060',8);
REPLACE INTO "modelo" ("id", "descricao", "nome", "preco", "id_carro") VALUES
	(12,'Flex','XLT2.0','60255',8);
REPLACE INTO "modelo" ("id", "descricao", "nome", "preco", "id_carro") VALUES
	(13,'Gasolina','SEL2.3L','84900',7);
/*!40000 ALTER TABLE "modelo" ENABLE KEYS;*/
UNLOCK TABLES;


#
# Table structure for table 'pedido'
#

CREATE TABLE /*!32312 IF NOT EXISTS*/ "pedido" (
  "id" int(11) NOT NULL auto_increment,
  "cep" varchar(40) default NULL,
  "cidade" varchar(100) default NULL,
  "dia" datetime default NULL,
  "email" varchar(100) default NULL,
  "estado" varchar(40) default NULL,
  "nome" varchar(100) NOT NULL,
  "telefone" varchar(40) default NULL,
  "preco" double default NULL,
  "id_modelo" int(11) default NULL,
  "id_pintura" int(11) default NULL,
  PRIMARY KEY  ("id")
) AUTO_INCREMENT=2;



#
# Dumping data for table 'pedido'
#

LOCK TABLES "pedido" WRITE;
/*!40000 ALTER TABLE "pedido" DISABLE KEYS;*/
REPLACE INTO "pedido" ("id", "cep", "cidade", "dia", "email", "estado", "nome", "telefone", "preco", "id_modelo", "id_pintura") VALUES
	(1,'42700-000','Lauro de Freitas','2009-05-10 23:02:29','potapczuk@gmail.com','Rua: Abreo Silve, 20','Diego Potapczuk','(71) 3241-4214','67400',3,4);
/*!40000 ALTER TABLE "pedido" ENABLE KEYS;*/
UNLOCK TABLES;


#
# Table structure for table 'pedido_acessorio'
#

CREATE TABLE /*!32312 IF NOT EXISTS*/ "pedido_acessorio" (
  "pedido_id" int(11) NOT NULL,
  "acessorio_id" int(11) NOT NULL
);



#
# Dumping data for table 'pedido_acessorio'
#

LOCK TABLES "pedido_acessorio" WRITE;
/*!40000 ALTER TABLE "pedido_acessorio" DISABLE KEYS;*/
REPLACE INTO "pedido_acessorio" ("pedido_id", "acessorio_id") VALUES
	(1,1);
REPLACE INTO "pedido_acessorio" ("pedido_id", "acessorio_id") VALUES
	(1,2);
/*!40000 ALTER TABLE "pedido_acessorio" ENABLE KEYS;*/
UNLOCK TABLES;


#
# Table structure for table 'pintura'
#

CREATE TABLE /*!32312 IF NOT EXISTS*/ "pintura" (
  "id" int(11) NOT NULL auto_increment,
  "nome" varchar(60) NOT NULL,
  "id_tipo_de_pintura" int(11) default NULL,
  PRIMARY KEY  ("id")
) AUTO_INCREMENT=13;



#
# Dumping data for table 'pintura'
#

LOCK TABLES "pintura" WRITE;
/*!40000 ALTER TABLE "pintura" DISABLE KEYS;*/
REPLACE INTO "pintura" ("id", "nome", "id_tipo_de_pintura") VALUES
	(6,'Prata Atenas',2);
REPLACE INTO "pintura" ("id", "nome", "id_tipo_de_pintura") VALUES
	(5,'Branco Artico',4);
REPLACE INTO "pintura" ("id", "nome", "id_tipo_de_pintura") VALUES
	(7,'Prata Geada',2);
REPLACE INTO "pintura" ("id", "nome", "id_tipo_de_pintura") VALUES
	(8,'Azul Monaco',3);
REPLACE INTO "pintura" ("id", "nome", "id_tipo_de_pintura") VALUES
	(9,'Cinza Ubatuba',3);
REPLACE INTO "pintura" ("id", "nome", "id_tipo_de_pintura") VALUES
	(10,'Preto Gales',3);
REPLACE INTO "pintura" ("id", "nome", "id_tipo_de_pintura") VALUES
	(11,'Verde Ipanema',3);
REPLACE INTO "pintura" ("id", "nome", "id_tipo_de_pintura") VALUES
	(12,'Vermelho Paris',3);
/*!40000 ALTER TABLE "pintura" ENABLE KEYS;*/
UNLOCK TABLES;


#
# Table structure for table 'pintura_carro'
#

CREATE TABLE /*!32312 IF NOT EXISTS*/ "pintura_carro" (
  "carro_id" int(11) NOT NULL,
  "pintura_id" int(11) NOT NULL
);



#
# Dumping data for table 'pintura_carro'
#

LOCK TABLES "pintura_carro" WRITE;
/*!40000 ALTER TABLE "pintura_carro" DISABLE KEYS;*/
REPLACE INTO "pintura_carro" ("carro_id", "pintura_id") VALUES
	(3,3);
REPLACE INTO "pintura_carro" ("carro_id", "pintura_id") VALUES
	(3,4);
REPLACE INTO "pintura_carro" ("carro_id", "pintura_id") VALUES
	(3,2);
REPLACE INTO "pintura_carro" ("carro_id", "pintura_id") VALUES
	(2,3);
REPLACE INTO "pintura_carro" ("carro_id", "pintura_id") VALUES
	(2,4);
REPLACE INTO "pintura_carro" ("carro_id", "pintura_id") VALUES
	(2,1);
/*!40000 ALTER TABLE "pintura_carro" ENABLE KEYS;*/
UNLOCK TABLES;


#
# Table structure for table 'tipodeacessorio'
#

CREATE TABLE /*!32312 IF NOT EXISTS*/ "tipodeacessorio" (
  "id" int(11) NOT NULL auto_increment,
  "nome" varchar(60) NOT NULL,
  PRIMARY KEY  ("id")
) AUTO_INCREMENT=5;



#
# Dumping data for table 'tipodeacessorio'
#

LOCK TABLES "tipodeacessorio" WRITE;
/*!40000 ALTER TABLE "tipodeacessorio" DISABLE KEYS;*/
REPLACE INTO "tipodeacessorio" ("id", "nome") VALUES
	(1,'Esportivo');
REPLACE INTO "tipodeacessorio" ("id", "nome") VALUES
	(2,'Som');
REPLACE INTO "tipodeacessorio" ("id", "nome") VALUES
	(3,'Conforto');
REPLACE INTO "tipodeacessorio" ("id", "nome") VALUES
	(4,'Segurança');
/*!40000 ALTER TABLE "tipodeacessorio" ENABLE KEYS;*/
UNLOCK TABLES;


#
# Table structure for table 'tipodepintura'
#

CREATE TABLE /*!32312 IF NOT EXISTS*/ "tipodepintura" (
  "id" int(11) NOT NULL auto_increment,
  "nome" varchar(60) NOT NULL,
  "preco" double default NULL,
  PRIMARY KEY  ("id")
) AUTO_INCREMENT=5;



#
# Dumping data for table 'tipodepintura'
#

LOCK TABLES "tipodepintura" WRITE;
/*!40000 ALTER TABLE "tipodepintura" DISABLE KEYS;*/
REPLACE INTO "tipodepintura" ("id", "nome", "preco") VALUES
	(4,'Solida','300');
REPLACE INTO "tipodepintura" ("id", "nome", "preco") VALUES
	(2,'Metálica','700');
REPLACE INTO "tipodepintura" ("id", "nome", "preco") VALUES
	(3,'Perolizada','1200');
/*!40000 ALTER TABLE "tipodepintura" ENABLE KEYS;*/
UNLOCK TABLES;


#
# Table structure for table 'usuario'
#

CREATE TABLE /*!32312 IF NOT EXISTS*/ "usuario" (
  "id" int(11) NOT NULL auto_increment,
  "grupo" varchar(30) NOT NULL,
  "nome" varchar(100) NOT NULL,
  "password" varchar(80) NOT NULL,
  "username" varchar(60) NOT NULL,
  PRIMARY KEY  ("id")
) AUTO_INCREMENT=4;



#
# Dumping data for table 'usuario'
#

LOCK TABLES "usuario" WRITE;
/*!40000 ALTER TABLE "usuario" DISABLE KEYS;*/
REPLACE INTO "usuario" ("id", "grupo", "nome", "password", "username") VALUES
	(1,'admin','Diego Potapczuk','teste123','potapczuk');
REPLACE INTO "usuario" ("id", "grupo", "nome", "password", "username") VALUES
	(2,'user','teste','teste123','teste');
REPLACE INTO "usuario" ("id", "grupo", "nome", "password", "username") VALUES
	(3,'admin','Marcelo Tosta Silva','lol1250','tchelo');
/*!40000 ALTER TABLE "usuario" ENABLE KEYS;*/
UNLOCK TABLES;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE;*/
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;*/
