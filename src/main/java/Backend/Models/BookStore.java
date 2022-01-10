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
	private User currentUser;
	private Manager currentManager;

	public List<Book> viewBooks() throws SQLException {
		List<Book> list = new ArrayList<Book>();
		ResultSet result = databaseManager.executeQuery("select * from book");
		while (result.next()) {
			Book book = new Book(result.getString("ISBN"), result.getString("title"), result.getString("Publisher"),
					result.getString("Publication_year"), result.getString("Category"), result.getString("Price"),
					result.getString("Threashold"), result.getString("Quantity"));
			list.add(book);
		}
		return list;
	}
}
