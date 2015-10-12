CREATE TABLE user (
    ID_Number           INTEGER(6) ZEROFILL NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    balance             FLOAT(6) NOT NULL
    pin                 INTEGER(4) NOT NULL
    name                VARCHAR(30) NOT NULL
);

CREATE TABLE buy_transaction (
    Buy_Transaction_ID INTEGER(6) ZEROFILL NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    TODO: timestamp
    TODO: total cost
    TODO: articles
);

CREATE TABLE shop_terminal (
    Shop_Terminal_ID    INTEGER(3) ZEROFILL NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    balance             FLOAT(8) NOT NULL
    pin                 INTEGER(4) NOT NULL
    TODO: SALES
);

CREATE TABLE order (
    Buy_Transaction_ID  INTEGER(6) ZEROFILL NOT NULL,
    Item_ID             INTEGER(3) ZEROFILL NOT NULL,
    quantity            INTEGER(2) NOT NULL
    FOREIGN KEY (Buy_Transaction_ID) REFERENCES buy_transaction(Buy_Transaction_ID) ON DELETE CASCADE ON UPDATE RESTRICT
    FOREIGN KEY (Item_ID) REFERENCES item(Item_ID) ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE item (
    Item_ID             INTEGER(3) ZEROFILL NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    name                VARCHAR(20) NOT NULL
    cost                FLOAT(6) NOT NULL
);

CREATE TABLE load_transaction (
    Load_Transaction_ID INTEGER(6) ZEROFILL NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    amount_loaded       FLOAT(6) NOT NULL
    ID_Number           INTEGER(6) ZEROFILL NOT NULL
    FOREIGN KEY (ID_Number) REFERENCES user(ID_Number) ON DELETE CASCADE ON UPDATE RESTRICT

);

CREATE TABLE load_terminal (
    Load_Terminal_ID    INTEGER(3) ZEROFILL NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    pin                 INTEGER(4) NOT NULL
    TODO: LOADS    
);