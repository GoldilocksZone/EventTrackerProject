-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema plantstats
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `plantstats` ;

-- -----------------------------------------------------
-- Schema plantstats
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `plantstats` DEFAULT CHARACTER SET utf8 ;
USE `plantstats` ;

-- -----------------------------------------------------
-- Table `soil_moisture`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `soil_moisture` ;

CREATE TABLE IF NOT EXISTS `soil_moisture` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date_recorded` VARCHAR(45) NULL,
  `moisture` VARCHAR(45) NULL,
  `plant_id` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `plant` ;

CREATE TABLE IF NOT EXISTS `plant` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `scientific_name` VARCHAR(45) NULL,
  `nickname` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS planttracker;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'planttracker' IDENTIFIED BY 'planttracker';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `plant`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantstats`;
INSERT INTO `plant` (`id`, `scientific_name`, `nickname`) VALUES (1, 'Ficus Lyrata', 'Figgy');
INSERT INTO `plant` (`id`, `scientific_name`, `nickname`) VALUES (2, 'Monstera Deliciosa', 'Monster');
INSERT INTO `plant` (`id`, `scientific_name`, `nickname`) VALUES (3, 'Geogenanthus ciliatus', 'Geo');

COMMIT;

