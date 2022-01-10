package Backend.Models;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class Manager extends User {

    public static ResultSet getAuthors(int ISBN) throws SQLException {
        return BookStore.databaseManager.executeQuery("CALL getAuthors("+ISBN+")");
    }

    public static ResultSet getPublisher(String publisherName) throws SQLException {
        return BookStore.databaseManager.executeQuery("CALL getPublisher('"+publisherName+"')");
    }

    public void promoteUser(String userName) throws SQLException {
        BookStore.databaseManager.executeQuery("UPDATE USER SET privilege = '1' WHERE username = '" + userName +"'");
    }

    public void addNewBook(Book book) throws SQLException {
        BookStore.databaseManager.executeQuery("CALL add_book("+book.getISBN()+",'"+book.getTitle()+
                "','"+book.getPublicationDate()+"',"+book.getPrice()+",'"+book.getCategory()+"',"+book.getNumberOfCopies()
                +","+book.getThreshold()+",'"+book.getPublisher().getName()+"')");
    }


    public boolean modifyBook(Book book) throws SQLException {
        try {
            BookStore.databaseManager.executeQuery("UPDATE BOOK SET Title = '" + book.getTitle() + "',Publisher = '" + book.getPublisher().getName() + "',Publication_year = '" + book.getPublicationDate() + "',Category = '" + book.getCategory() +
                    "',Price = " + book.getPrice() + ",Threashold = " + book.getThreshold() + ",Quantity = " + book.getNumberOfCopies() + " WHERE ISBN = " + book.getISBN()            );

        } catch (SQLException e) {
            if (e.getMessage().equals("Quantity in Stock is less than zero"))
                return false;
        }
        return true;
    }

    /**
     * The total sales for books in the previous month 
     * @throws JRException 
     */

    public void getTotalSales() throws JRException {
    	JasperPrint jasperPrint = JasperFillManager.fillReport("Jasper\\TotalSales.jasper", null, BookStore.databaseManager.getConnection());
        JasperViewer jasperViewer = new JasperViewer(jasperPrint);
        jasperViewer.setVisible(true);
    }
    
    
    public void getTotalBooksSold () throws JRException {
    	JasperPrint jasperPrint = JasperFillManager.fillReport("Jasper\\TotalSalesBooks.jasper", null, BookStore.databaseManager.getConnection());
        JasperViewer jasperViewer = new JasperViewer(jasperPrint);
        jasperViewer.setVisible(true);
    }
    
    /**
     * The top 5 customers who purchase the most purchase amount in descending order for the last three months
     *
     * @return
     * @throws JRException 
     */
    public void getTop5PrevMonth() throws JRException {
    	JasperPrint jasperPrint = JasperFillManager.fillReport("Jasper\\Top5Customer.jasper", null, BookStore.databaseManager.getConnection());
        JasperViewer jasperViewer = new JasperViewer(jasperPrint);
        jasperViewer.setVisible(true);
    }

    /**
     * The top 10 selling books for the last three months
     *
     * @return
     */
    public void bestSellerBooksReport() throws JRException {
        JasperPrint jasperPrint = JasperFillManager.fillReport("Jasper\\Top10Book.jasper", null, BookStore.databaseManager.getConnection());
        JasperViewer jasperViewer = new JasperViewer(jasperPrint);
        jasperViewer.setVisible(true);
    }


    public void placeOrder(int ISBN, String publisher, int quantity) throws SQLException {
        BookStore.databaseManager.executeQuery("CALL placeOrder("+ISBN+",'"+publisher+"',"+quantity+")");
    }
    public List<User> getUsers() throws SQLException {
            ResultSet resultSet=BookStore.databaseManager.executeQuery("SELECT * FROM user");
            List<User> users=new ArrayList<>();
            while(resultSet.next()){
                User user=new User();
                user.setId(resultSet.getInt("UserId"));
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phone"));
                user.setShippingAddress(resultSet.getString("shippingAddress"));
                user.setPrivilege(resultSet.getInt("Privilege"));
                users.add(user);
            }
            return users;

    }
    public ResultSet getOrders() throws SQLException {
        return BookStore.databaseManager.executeQuery("SELECT * FROM Library_Orders");
    }
}
