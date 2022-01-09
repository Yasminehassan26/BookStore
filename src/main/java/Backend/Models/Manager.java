package Backend.Models;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Manager extends User {

    public static ResultSet getAuthors(int ISBN) throws SQLException {
        return BookStore.databaseManager.executeQuery("CALL getAuthors("+ISBN+")");
    }

    public static ResultSet getPublisher(String publisherName) throws SQLException {
        return BookStore.databaseManager.executeQuery("CALL getPublisher("+publisherName+")");
    }

    public void promoteUser(int UserId) throws SQLException {
        BookStore.databaseManager.executeQuery("UPDATE USER SET privilege = 'true' ");
    }

    public void addNewBook(Book book) throws SQLException {
        BookStore.databaseManager.executeQuery("CALL add_book("+book.getISBN()+",'"+book.getTitle()+
                "','"+book.getPublicationDate()+"',"+book.getPrice()+",'"+book.getCategory()+"',"+book.getNumberOfCopies()
                +","+book.getThreshold()+",'"+book.getPublisher()+"')");
    }


    public void modifyBook(Book book) throws SQLException {
        BookStore.databaseManager.executeQuery("UPDATE BOOK SET title = '" + book.getTitle() + "',Publication_Year = '" + book.getPublicationDate() + "',Price = '" + book.getPrice() + "',Category = '" + book.getCategory() +
                "',Quantity = '" + book.getNumberOfCopies() + "',Threashold = '" + book.getThreshold() + "',Publisher = '" + book.getPublisher() + "' WHERE ISBN = " + book.getISBN()
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
