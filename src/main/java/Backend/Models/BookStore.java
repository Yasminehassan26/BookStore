package Backend.Models;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
public class BookStore {

	public static final DatabaseManager databaseManager = DatabaseManager.getInstance("root", "Ahmedhazem2");

	private List<Manager> managers;
	private List<User> users;
	private List<Book> books;
	private List<UserOrder> userOrders;
	private User currentUser=new User();
	private Manager currentManager;


}
