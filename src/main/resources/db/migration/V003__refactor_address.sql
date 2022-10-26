ALTER TABLE `restaurant`
DROP FOREIGN KEY `FK_city`;
ALTER TABLE `restaurant`
CHANGE COLUMN `address_neighborhood` `address_neighborhood` VARCHAR(255) NULL ,
CHANGE COLUMN `address_number` `address_number` SMALLINT NULL ,
CHANGE COLUMN `address_patio` `address_patio` VARCHAR(100) NULL ,
CHANGE COLUMN `address_zip_cod` `address_zip_cod` VARCHAR(10) NULL ,
CHANGE COLUMN `address_id_city` `address_id_city` BIGINT NULL ;
ALTER TABLE `restaurant`
ADD CONSTRAINT `FK_city`
  FOREIGN KEY (`address_id_city`)
  REFERENCES `city` (`id_city`);

  ALTER TABLE `demand`
  DROP FOREIGN KEY `fk_demand_address_city`;
  ALTER TABLE `demand`
  CHANGE COLUMN `address_neighborhood` `address_neighborhood` VARCHAR(255) NULL ,
  CHANGE COLUMN `address_number` `address_number` SMALLINT NULL ,
  CHANGE COLUMN `address_patio` `address_patio` VARCHAR(100) NULL ,
  CHANGE COLUMN `address_zip_cod` `address_zip_cod` VARCHAR(10) NULL ,
  CHANGE COLUMN `address_id_city` `address_id_city` BIGINT NULL ;
  ALTER TABLE `demand`
  ADD CONSTRAINT `fk_demand_address_city`
    FOREIGN KEY (`address_id_city`)
    REFERENCES `city` (`id_city`);
