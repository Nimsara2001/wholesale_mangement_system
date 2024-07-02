DROP DATABASE IF EXISTS WholesaleDB;
CREATE DATABASE IF NOT EXISTS WholesaleDB;
SHOW DATABASES;
USE WholesaleDB;
#--------------------------------------------------------------
DROP TABLE IF EXISTS Customer;
CREATE TABLE IF NOT EXISTS Customer(
    CustID VARCHAR(6),
    CustTitle VARCHAR(5) ,
    CustName VARCHAR(30) NOT NULL DEFAULT "Unknown",
    CustAddress VARCHAR(30),
    City VARCHAR(20),
    Province VARCHAR(20),
    PostalCode VARCHAR (9),
    CONSTRAINT PRIMARY KEY (CustID)
    );
SHOW TABLES ;
DESCRIBE Customer;

#----------------------------------------------------------------
DROP TABLE IF EXISTS Orders;
CREATE TABLE IF NOT EXISTS Orders(
    OrderID VARCHAR (6),
    CustID VARCHAR (6),
    OrderDate DATE,
    OrderTime VARCHAR (10),
    TotalCost DOUBLE ,
    CONSTRAINT PRIMARY KEY (OrderID),
    CONSTRAINT FOREIGN KEY (CustID) REFERENCES Customer(CustID) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES;
DESCRIBE Orders;
#----------------------------------------------------------------

DROP TABLE IF EXISTS Item;
CREATE TABLE IF NOT EXISTS Item(
    ItemCode VARCHAR(6),
    Description VARCHAR (50),
    PackSize VARCHAR (20),
    UnitPrice DOUBLE DEFAULT 0.00,
    QtyOnHand INT(5) DEFAULT 0,
    DiscRate DOUBLE DEFAULT 0.0,
    CONSTRAINT PRIMARY KEY (ItemCode)
    );
SHOW TABLES ;
DESCRIBE Item;
#-----------------------------------------------------------
DROP TABLE IF EXISTS OrderDetail;
CREATE TABLE IF NOT EXISTS OrderDetail (
    OrderID VARCHAR (6),
    ItemCode VARCHAR(6),
    OrderQTY INT(11),
    Discount DOUBLE ,
    ItemCost DOUBLE,
    CONSTRAINT PRIMARY KEY (OrderID, ItemCode),
    CONSTRAINT FOREIGN KEY (OrderID) REFERENCES Orders(OrderID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (ItemCode) REFERENCES Item(ItemCode) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE OrderDetail;

#=================================================================
INSERT INTO Customer VALUES("No-ID"," ","No Name","No Address","No City","No Province","No P-Code");
