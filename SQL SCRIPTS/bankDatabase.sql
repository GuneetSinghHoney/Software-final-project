-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bank
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bank
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bank` DEFAULT CHARACTER SET utf8 ;
USE `bank` ;

-- -----------------------------------------------------
-- Table `bank`.`balance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bank`.`balance` (
  `account` VARCHAR(10) NOT NULL DEFAULT 'null',
  `saving` VARCHAR(45) NOT NULL DEFAULT '0',
  `current` VARCHAR(45) NOT NULL DEFAULT '0',
  `credit` VARCHAR(45) NOT NULL DEFAULT '0',
  `limit` VARCHAR(45) NOT NULL DEFAULT '0',
  PRIMARY KEY (`account`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bank`.`information`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bank`.`information` (
  `account` VARCHAR(10) NOT NULL DEFAULT 'null',
  `name` VARCHAR(45) NOT NULL,
  `age` INT(11) NOT NULL,
  `gender` VARCHAR(45) NOT NULL DEFAULT 'Not Disclosed',
  `Address` VARCHAR(200) NOT NULL,
  `SIN` VARCHAR(45) NULL DEFAULT NULL,
  `driving` VARCHAR(45) NULL DEFAULT NULL,
  `passport` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`account`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bank`.`login`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bank`.`login` (
  `username` VARCHAR(10) NOT NULL,
  `pass` VARCHAR(20) NOT NULL DEFAULT 'null',
  PRIMARY KEY (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bank`.`transactions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bank`.`transactions` (
  `sender` VARCHAR(12) NOT NULL,
  `receiver` VARCHAR(12) NOT NULL,
  `date` DATETIME NOT NULL,
  `amount` VARCHAR(45) NOT NULL,
  `message` VARCHAR(200) NULL DEFAULT 'No Explanation',
  `details` VARCHAR(45) NULL DEFAULT 'No Details',
  PRIMARY KEY (`sender`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
