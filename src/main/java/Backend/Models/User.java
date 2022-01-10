package Backend.Models;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@Data
public class User {

	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String shippingAddress;
    private Cart cart = new Cart();

	public void editPersonalInfo(UserBasicInfo user) throws SQLException {
        BookStore.databaseManager.executeQuery("UPDATE USER SET UserName = '" + user.getUsername()
                + "',Password = '" + user.getPassword() + "',First_Name = '" + user.getFirstName() + "',Last_Name = '" + user.getLastName() + "',Email = '" + user.getEmail() +
                "',Phone_Number = '" + user.getPhoneNumber() + "',Shipping_Address = '" + user.getShippingAddress() + "',privilege = " + user.getPrivilege() +
                " WHERE User_id = " + user.getId());
	}

	private Book mapToBook(ResultSet result) throws SQLException {
		Book add = new Book();
		int ISBN = result.getInt("ISBN");
		add.setISBN(ISBN);
		add.setCategory(Category.valueOf(result.getNString("Category")));
		add.setNumberOfCopies(result.getInt("Quantity"));
		add.setPrice(Double.valueOf(result.getInt("Price")));
		add.setPublicationDate(result.getString("Publication_year"));
		add.setThreshold(result.getInt("Threashold"));
		add.setTitle(result.getNString("Title"));
		List<String> authors = new ArrayList<>();
		ResultSet Auth = Manager.getAuthors(ISBN);
		while (Auth.next()) {
			authors.add(Auth.getNString("Author_name"));
		}
		add.setAuthors(authors);
		ResultSet publishers = Manager.getPublisher(result.getNString("Publisher"));
		publishers.next();
		Publisher publisher = new Publisher();
		publisher.setName(result.getString("Publisher"));
		publisher.setAddress(publishers.getString("Address"));
		publisher.setTelephoneNumbers(publishers.getString("Number"));
		add.setPublisher(publisher);
		return add;
	}

	public List<Book> searchByISBN(int ISBN) throws SQLException {
		List<Book> books = new ArrayList<>();
		ResultSet result = BookStore.databaseManager.executeQuery("CALL search_By_ISBN(" + ISBN + ")");
		while (result.next()) {
			books.add(mapToBook(result));
		}
		return books;
	}

	public List<Book> searchByTitle(String Title) throws SQLException {
		List<Book> books = new ArrayList<>();
		ResultSet result = BookStore.databaseManager.executeQuery("CALL search_By_Title('" + Title + "')");
		while (result.next()) {
			books.add(mapToBook(result));
		}
		return books;
	}

	public List<Book> searchByPublisher(String publisher) throws SQLException {
		List<Book> books = new ArrayList<>();
		ResultSet result = BookStore.databaseManager.executeQuery("CALL search_By_Publisher('" + publisher + "')");
		while (result.next()) {
			books.add(mapToBook(result));
		}
		return books;
	}

	public List<Book> searchByCategory(String Category) throws SQLException {
		List<Book> books = new ArrayList<>();
		ResultSet result = BookStore.databaseManager.executeQuery("CALL search_By_Category('" + Category + "')");
		while (result.next()) {
			books.add(mapToBook(result));
		}
		return books;
	}

	public List<Book> searchByAuthor(String author) throws SQLException {
		List<Book> books = new ArrayList<>();
		ResultSet result = BookStore.databaseManager.executeQuery("CALL search_By_Author('" + author + "')");
		while (result.next()) {
			books.add(mapToBook(result));
		}
		return books;
	}

	public List<Book> searchByYear(String year) throws SQLException {
		List<Book> books = new ArrayList<>();
		ResultSet result = BookStore.databaseManager.executeQuery("CALL search_By_year('" + year + "')");
		while (result.next()) {
			books.add(mapToBook(result));
		}
		return books;
	}

    public void addToCart (Book book){
        cart.addBook(book);
    }

    public void removeFromCart(Book book){
        cart.removeBook(book);
    }

    public HashMap<Book, Integer> listInCart(){
        return cart.viewCart();
    }

    public double totalCartCost(){
        return cart.getTotalPrice();
    }

    public boolean placeOrder () throws SQLException {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        ResultSet resultSet = BookStore.databaseManager.executeQuery("CALL addCustomerOrder( "+ id + ",'" + formatter.format(date) + "'," + cart.getTotalPrice() + " )");
        resultSet.next();
        for (Map.Entry<Book, Integer> entry:
             cart.getBooks().entrySet()) {
            BookStore.databaseManager.executeQuery("INSERT INTO order_items values (" + resultSet.getInt("LAST_INSERT_ID()") + ","
                    + entry.getKey().getISBN() + "," + entry.getValue() + ")");
        }
				return true;
	}
}
