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
  `date_recorded` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `moisture` VARCHAR(45) NOT NULL,
  `plant_id` VARCHAR(45) NOT NULL,
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

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'planttracker';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `soil_moisture`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantstats`;
INSERT INTO `soil_moisture` (`id`, `date_recorded`, `moisture`, `plant_id`) VALUES (1, '2022-04-02 12:22:01', '11', '1');
INSERT INTO `soil_moisture` (`id`, `date_recorded`, `moisture`, `plant_id`) VALUES (2, '2022-04-11 13:13:13', '13', '2');
INSERT INTO `soil_moisture` (`id`, `date_recorded`, `moisture`, `plant_id`) VALUES (3, '2022-04-18 18:18:18', '9', '1');
INSERT INTO `soil_moisture` (`id`, `date_recorded`, `moisture`, `plant_id`) VALUES (4, '2022-04-25 02:05:25', '12', '2');
INSERT INTO `soil_moisture` (`id`, `date_recorded`, `moisture`, `plant_id`) VALUES (5, '2022-05-02 05:02:52', '10', '3');
INSERT INTO `soil_moisture` (`id`, `date_recorded`, `moisture`, `plant_id`) VALUES (6, '2022-05-09 09:05:59', '12', '3');

COMMIT;


-- -----------------------------------------------------
-- Data for table `plant`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantstats`;
INSERT INTO `plant` (`id`, `scientific_name`, `nickname`) VALUES (1, 'Ficus Lyrata', 'Figgy');
INSERT INTO `plant` (`id`, `scientific_name`, `nickname`) VALUES (2, 'Monstera Deliciosa', 'Monster');
INSERT INTO `plant` (`id`, `scientific_name`, `nickname`) VALUES (3, 'Geogenanthus ciliatus', 'Geo');

COMMIT;

