package Backend.Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
	public static void main(String[] args) throws SQLException {
		// DatabaseManager db = DatabaseManager.getInstance("root", "26101999");
		// Connection c = db.getConnection();
		// ResultSet r = db.executeQuery("SELECT * FROM BOOK;");
//        while (r.next())
//        {
//            System.out.println(r.getInt("ISBN"));
//            System.out.println(r.getString("title"));
//        }

        //User user = new User();
        UserBasicInfo userr = new UserBasicInfo();
        userr.setUsername("testsignup1");
        userr.setPassword("testsignup1");
        userr.setEmail("testsignup1@gmail.com");
        userr.setFirstName("testsignup1");
        userr.setLastName("testsignup1");
        userr.setPhoneNumber("testsignup1");
        userr.setShippingAddress("testsignup1");
        userr.setPrivilege(0);
        //userr.setId(1);
       //user.editPersonalInfo(userr);

		Book book = new Book();
		Publisher p = new Publisher();
		p.setName("ahmed");
		book.setISBN(3);
		book.setPrice(56.94);
		book.setCategory(Category.Art);
		book.setTitle("hdhks");
		book.setThreshold(5);
		book.setNumberOfCopies(10);
		book.setPublicationDate("2023");
		book.setPublisher(p);

		// Manager manager = new Manager();
		// manager.addNewBook(book);
//        manager.modifyBook(book);
		
		
		User u = new User();
		//u.signIn("hazem", "1111112");
		System.out.println(u.signUp(userr));
	}
}
