package Backend.Models;


import java.sql.SQLException;
import java.util.List;

public class Manager extends User {

    public void promoteUser(int UserId) throws SQLException {
    	BookStore.databaseManager.executeQuery("UPDATE USER SET privilege = 'true' ");
    }

    public void addNewBook(Book book) throws SQLException {

        BookStore.databaseManager.executeQuery("INSERT INTO BOOK VALUES('" + book.getISBN()
                + "','" + book.getTitle() + "','" + book.getPublisher().getName() + "','" + book.getPublicationDate() + "','" + book.getCategory() +
                "','" + book.getPrice() + "','" + book.getThreshold() + "','" + book.getNumberOfCopies() +
                "')");
    }


    public void modifyBook(Book book) throws SQLException {
        BookStore.databaseManager.executeQuery("UPDATE BOOK SET title = '" + book.getTitle() + "',Publication_Year = '" + book.getPublicationDate() + "',Price = '" + book.getPrice() + "',Category = '" + book.getCategory() +
                "',Quantity = '" + book.getNumberOfCopies() + "',Threashold = '" + book.getThreshold() + "',Publisher = '" + book.getPublisher().getName() + "' WHERE ISBN = " + book.getISBN()
        );
    }


    /**
     * The total sales for books in the previous month 
     */

    public int getPrevMonthSales() {
        return 0;
    }

    /**
     * The top 5 customers who purchase the most purchase amount in descending order for the last three months
     *
     * @return
     */
    public List<User> getTop5PrevMonth() {
        return null;
    }

    /**
     * The top 10 selling books for the last three months
     *
     * @return
     */
    public List<Book> getBestSellerBooks() {
        return null;
    }


}
