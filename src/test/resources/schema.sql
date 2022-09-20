-- CREATE SCHEMA IF not exists CGA101G1;
-- set schema CGA101G1;


CREATE TABLE  gameplatformtype (
                        `GamePlatformNo` int NOT NULL AUTO_INCREMENT,
                        `GamePlatformName`  varchar(20) NOT NULL,
                        PRIMARY KEY (`GamePlatformNo`)
);

CREATE TABLE IF NOT exists `gametype` (
                            `GameTypeNo` int NOT NULL AUTO_INCREMENT,
                            `GameTypeName` varchar(20) NOT NULL,
                            PRIMARY KEY (`GameTypeNo`)
);

CREATE TABLE IF NOT exists `gamecompany` (
                               `GameCompanyNo` int NOT NULL AUTO_INCREMENT,
                               `GameCompanyName` varchar(20) NOT NULL,
                               PRIMARY KEY (`GameCompanyNo`)
);

CREATE TABLE IF NOT exists `product` (
                           `ProductNo` int NOT NULL AUTO_INCREMENT,
                           `GameTypeNo` int NOT NULL,
                           `GamePlatformNo` int NOT NULL,
                           `GameCompanyNo` int NOT NULL,
                           `ProductName` varchar(100) NOT NULL,
                           `ProductPrice` int NOT NULL,
                           `ProductState` int NOT NULL DEFAULT '1',
                           `SoldTime` datetime DEFAULT NULL,
                           `LaunchedTime` datetime DEFAULT NULL,
                           `ItemProdDescription` varchar(1000) NOT NULL,
                           `UpcNum` varchar(50) NOT NULL,
                           PRIMARY KEY (`ProductNo`)
--                            KEY `GameTypeNo_idx` (`GameTypeNo`),
--                            KEY `GamePlatformNo_idx` (`GamePlatformNo`),
--                            KEY `GameCompanyNo_product_idx` (`GameCompanyNo`),
--                            CONSTRAINT `GameCompanyNo_product` FOREIGN KEY (`GameCompanyNo`) REFERENCES `gamecompany` (`GameCompanyNo`),
--                            CONSTRAINT `GamePlatformNo_product` FOREIGN KEY (`GamePlatformNo`) REFERENCES `gameplatformtype` (`GamePlatformNo`),
--                            CONSTRAINT `GameTypeNo_product` FOREIGN KEY (`GameTypeNo`) REFERENCES `gametype` (`GameTypeNo`)
);