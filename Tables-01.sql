CREATE TABLE user (
    ID_Number           INTEGER(6) ZEROFILL NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    balance             DOUBLE(6) NOT NULL
    pin                 INTEGER(6) NOT NULL
    name                VARCHAR(30) NOT NULL
);

CREATE TABLE buy_transaction (
    Buy_Transaction_ID
    timestamp

);

CREATE TABLE shop_terminal (

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
    cost                DOUBLE(6) NOT NULL
);

CREATE TABLE load_transaction (
    Load_Transaction_ID INTEGER(6) ZEROFILL NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    amount_loaded       DOUBLE(6) NOT NULL
    ID_Number           INTEGER(6) ZEROFILL NOT NULL
    FOREIGN KEY (ID_Number) REFERENCES user(ID_Number) ON DELETE CASCADE ON UPDATE RESTRICT

);

CREATE TABLE load_terminal (

);