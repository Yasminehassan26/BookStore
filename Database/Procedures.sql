DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_Book`(ISBN INT, Title varchar(45),Publication_Year Year,Selling_Price INT,Category ENUM('Science', 'Art', 'Religion', 'History', 'Geography'),Quantity INT,Threshold INT,Publisher_Name varchar(45))
BEGIN
	insert into Book values (ISBN,Title,Publisher_Name,Publication_Year,Category,Selling_Price,Threshold,Quantity);
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `search_By_Title`(IN Title varchar(45))
BEGIN	
	SELECT * FROM Book Where Book.Title like  concat('%',Title,'%');
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `search_By_Publisher`(IN Publisher_Name varchar(45))
BEGIN
	SELECT * FROM Book Where Book.Publisher=Publisher_Name;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `search_By_ISBN`(IN ISBN int)
BEGIN
	SELECT * FROM Book Where Book.ISBN = ISBN;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `search_By_Category`(IN Category ENUM('Science', 'Art', 'Religion', 'History', 'Geography') )
BEGIN
	SELECT * FROM Book Where Book.Category = Category;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `search_By_Author`(IN Author_Name varchar(45))
BEGIN
	SELECT * FROM Book Where Book.ISBN IN (SELECT ISBN FROM Authors where Authors.Author_Name=Author_Name);
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `search_By_year`(IN publication_year VARCHAR(4))
BEGIN
	SELECT * FROM Book Where Book.Publication_year=publication_year;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getPublisher`(IN publisher_name VARCHAR(50))
BEGIN
	SELECT * FROM Publisher Where Publisher.Name=publisher_name;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAuthors`(IN ISBN INT)
BEGIN
	SELECT * FROM Authors Where Authors.ISBN=ISBN;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addCustomerOrder`(IN Id INT, date DATE,price INT)
BEGIN
	Insert INTO customer_order (UserId,Date,Price) VALUES(Id,date,price);
    Select LAST_INSERT_ID();
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `confirmOrder`(IN Id INT)
BEGIN
	DELETE FROM Library_order where Library_order.orderId=Id;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `confirmOrderItems`(IN Id INT,ISBN INT,Quantity INT)
BEGIN
	UPDATE Book SET Book.Quantity=Book.Quantity-Quantity where Book.ISBN=ISBN;
    INSERT INTO Order_Items VALUES(Id,ISBN,Quantity);
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteOrder`(IN Id INT)
BEGIN
	DELETE FROM Order_Items where Order_Items.orderID=Id;
    DELETE FROM Customer_Order where Customer_Order.orderId=Id;
END$$
DELIMITER ;