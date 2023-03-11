CREATE SCHEMA IF not exists CGA101G1;
set schema CGA101G1;

-- CREATE TABLE IF NOT exists `mem` (
--                        `MemNo` int NOT NULL AUTO_INCREMENT COMMENT ,
--                        `MemAccount` varchar(20) NOT NULL,
--                        `MemPassword` varchar(20) NOT NULL,
--                        `MemStatus` int NOT NULL DEFAULT '1' COMMENT ,
--                        `MemVrfed` int NOT NULL DEFAULT '0' COMMENT ,
--                        `MemNoVrftime` timestamp NULL DEFAULT NULL COMMENT ,
--                        `MemName` varchar(100) NOT NULL,
--                        `MemMobile` varchar(10) NOT NULL,
--                        `MemCity` varchar(20) DEFAULT NULL,
--                        `MemDist` varchar(20) DEFAULT NULL,
--                        `MemAdd` varchar(100) DEFAULT NULL,
--                        `MemEmail` varchar(50) NOT NULL,
--                        `MemBirth` date DEFAULT NULL,
--                        `MemJoinTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ,
--                        `CreditcardNo` varchar(20) DEFAULT NULL,
--                        `CreditcardDate` varchar(4) DEFAULT NULL,
--                        `CreditcardSecurityNo` varchar(3) DEFAULT NULL,
--                        `BankAccount` varchar(20) DEFAULT NULL,
--                        `BankAccountOwner` varchar(10) DEFAULT NULL,
--                        `UserStatus` int NOT NULL DEFAULT '0' COMMENT ,
--                        `MyPic` mediumblob,
--                        `IsMute` int DEFAULT '0' COMMENT,
--                        PRIMARY KEY (`MemNo`),
-- };


CREATE TABLE IF NOT exists CGA101G1.gameplatformtype (
                        `GamePlatformNo` int NOT NULL AUTO_INCREMENT,
                        `GamePlatformName`  varchar(20) NOT NULL,
                        PRIMARY KEY (`GamePlatformNo`)
);

CREATE TABLE IF NOT exists CGA101G1.gametype (
                            `GameTypeNo` int NOT NULL AUTO_INCREMENT,
                            `GameTypeName` varchar(20) NOT NULL,
                            PRIMARY KEY (`GameTypeNo`)
);

CREATE TABLE IF NOT exists CGA101G1.gamecompany (
                               `GameCompanyNo` int NOT NULL AUTO_INCREMENT,
                               `GameCompanyName` varchar(20) NOT NULL,
                               PRIMARY KEY (`GameCompanyNo`)
);

CREATE TABLE IF NOT exists CGA101G1.product (
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

CREATE TABLE CGA101G1.orders
(
    `OrderNo`         int          NOT NULL AUTO_INCREMENT,
    `MemNo`           int          NOT NULL,
    `MemCouponNo`     int,
    `TranTime`        timestamp,
    `OrderTotalPrice` int          NOT NULL,
    `OrderState`      int          NOT NULL,
    `PickupMethod`    int,
    `ShippingFee`     int,
    `TrackingNum`     varchar(150),
    `ReceiverName`    varchar(100) NOT NULL,
    `ReceiverAddress` varchar(100),
    `ReceiverPhone`   varchar(100) NOT NULL,
    `PickupTime`      timestamp,
    PRIMARY KEY (`OrderNo`)
);