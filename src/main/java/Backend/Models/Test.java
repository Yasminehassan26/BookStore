package Backend.Models;

import net.sf.jasperreports.engine.JRException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws SQLException, JRException {


        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        Manager manager = new Manager();

        user1.setId(1);
        user2.setId(2);
        user3.setId(3);

        // user 1 orders
        List<Book> b1 = user1.searchByTitle("testbook1");
        user1.addToCart(b1.get(0), 3);
        user1.placeOrder();

        // user 2 orders
        List<Book> b2 = user2.searchByTitle("testbook2");
        user2.addToCart(b2.get(0), 5);
        user2.placeOrder();

        // user 3 orders
        List<Book> b3 = user3.searchByTitle("testbook3");
        user3.addToCart(b3.get(0), 7);
        user3.placeOrder();

        // manager view reports
        manager.bestSellerBooksReport();
        manager.getTop5PrevMonth();
        manager.getTotalSales();
        manager.getTotalBooksSold();
    }
}
