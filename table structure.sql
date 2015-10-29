CREATE TABLE IF NOT EXISTS `buy_transaction` (
  `Buy_Transaction_ID` int(6) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `buy_transaction_TS` datetime NOT NULL,
  `ID_Number` int(6) unsigned zerofill NOT NULL,
  `shop_terminal_id` int(3) unsigned zerofill NOT NULL,
  PRIMARY KEY (`Buy_Transaction_ID`),
  UNIQUE KEY `Buy_Transaction_ID` (`Buy_Transaction_ID`),
  KEY `ID_Number` (`ID_Number`),
  KEY `shop_terminal_id` (`shop_terminal_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

CREATE TABLE IF NOT EXISTS `item` (
  `Item_ID` int(3) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `item_name` varchar(20) NOT NULL,
  `item_price` float unsigned NOT NULL,
  PRIMARY KEY (`Item_ID`),
  UNIQUE KEY `Item_ID` (`Item_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

CREATE TABLE IF NOT EXISTS `item_order` (
  `Buy_Transaction_ID` int(6) unsigned zerofill NOT NULL,
  `Item_ID` int(3) unsigned zerofill NOT NULL,
  `quantity` int(2) unsigned NOT NULL,
  KEY `Buy_Transaction_ID` (`Buy_Transaction_ID`),
  KEY `Item_ID` (`Item_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `load_terminal` (
  `Load_Terminal_ID` int(3) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `pin` int(4) unsigned zerofill NOT NULL,
  `load_transaction_TS` datetime NOT NULL,
  PRIMARY KEY (`Load_Terminal_ID`),
  UNIQUE KEY `Load_Terminal_ID` (`Load_Terminal_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

CREATE TABLE IF NOT EXISTS `load_transaction` (
  `Load_Transaction_ID` int(6) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `amount_loaded` float unsigned NOT NULL,
  `ID_Number` int(6) unsigned zerofill NOT NULL,
  `load_terminal_id` int(3) unsigned zerofill NOT NULL,
  PRIMARY KEY (`Load_Transaction_ID`),
  UNIQUE KEY `Load_Transaction_ID` (`Load_Transaction_ID`),
  KEY `ID_Number` (`ID_Number`),
  KEY `load_terminal_id` (`load_terminal_id`),
  KEY `load_terminal_id_2` (`load_terminal_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

CREATE TABLE IF NOT EXISTS `shop_terminal` (
  `Shop_Terminal_ID` int(3) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `balance` float unsigned NOT NULL,
  `pin` int(4) unsigned zerofill NOT NULL,
  PRIMARY KEY (`Shop_Terminal_ID`),
  UNIQUE KEY `Shop_Terminal_ID` (`Shop_Terminal_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

CREATE TABLE IF NOT EXISTS `stock` (
  `Shop_Terminal_ID` int(3) unsigned zerofill NOT NULL,
  `Item_ID` int(3) unsigned zerofill NOT NULL,
  `stock_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY `Shop_Terminal_ID` (`Shop_Terminal_ID`),
  KEY `Item_ID` (`Item_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `user` (
  `ID_Number` int(6) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `balance` float unsigned NOT NULL,
  `pin` int(4) unsigned zerofill NOT NULL,
  `first_name` varchar(15) NOT NULL,
  `last_name` varchar(15) NOT NULL,
  `user_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID_Number`),
  UNIQUE KEY `ID_Number` (`ID_Number`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=152218 ;

ALTER TABLE `buy_transaction`
  ADD CONSTRAINT `buy_transaction_ibfk_2` FOREIGN KEY (`shop_terminal_id`) REFERENCES `shop_terminal` (`Shop_Terminal_ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `buy_transaction_ibfk_1` FOREIGN KEY (`ID_Number`) REFERENCES `user` (`ID_Number`) ON DELETE CASCADE;

--
-- Constraints for table `item_order`
--
ALTER TABLE `item_order`
  ADD CONSTRAINT `item_order_ibfk_1` FOREIGN KEY (`Buy_Transaction_ID`) REFERENCES `buy_transaction` (`Buy_Transaction_ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `item_order_ibfk_2` FOREIGN KEY (`Item_ID`) REFERENCES `item` (`Item_ID`) ON DELETE CASCADE;

--
-- Constraints for table `load_transaction`
--
ALTER TABLE `load_transaction`
  ADD CONSTRAINT `load_transaction_ibfk_2` FOREIGN KEY (`load_terminal_id`) REFERENCES `load_terminal` (`Load_Terminal_ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `load_transaction_ibfk_1` FOREIGN KEY (`ID_Number`) REFERENCES `user` (`ID_Number`) ON DELETE CASCADE;

--
-- Constraints for table `stock`
--
ALTER TABLE `stock`
  ADD CONSTRAINT `stock_ibfk_1` FOREIGN KEY (`Shop_Terminal_ID`) REFERENCES `shop_terminal` (`Shop_Terminal_ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `stock_ibfk_2` FOREIGN KEY (`Item_ID`) REFERENCES `item` (`Item_ID`) ON DELETE CASCADE;
