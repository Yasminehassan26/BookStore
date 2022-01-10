package gui.bookstore;


import Backend.Models.Book;
import Backend.Models.BookStore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class PlaceOrder implements Initializable {


    ObservableList<BookTable> list;
    boolean first = false;
    @FXML
    private Label totalPrice;
    @FXML
    private Label labelDelete;
    @FXML
    private TableView<BookTable> table;
    @FXML
    private TableColumn<BookTable, String> Book;
    @FXML
    private TableColumn<BookTable, String> ISPN;
    @FXML
    private TableColumn<BookTable, String> Select;
    @FXML
    private TableColumn<BookTable, String> Quantity;

    BookStore store;
    List<Book> books=new ArrayList<>();
    public ObservableList<BookTable> getData() throws SQLException {
        list = FXCollections.observableArrayList();
        books=store.getCurrentUser().viewBooks();
        for (int i = 0; i < books.size(); i++) {
            System.out.println("e");
            Backend.Models.Book book = books.get(i);
            System.out.println(books.get(i).getTitle());
            list.add(new BookTable(book.getTitle(), String.valueOf(book.getISBN()),String.valueOf(book.getPrice()),"1"));
        }
        return list;

    }

    public void confirm(ActionEvent event) throws SQLException {
       if (list!=null && list.size()>0) {
//            //confirm the order
           for (int i = 0; i < list.size(); i++) {
               if (list.get(i).getSelect().isSelected()) {
                   Book book=books.get(i);
                   int quantity=Integer.parseInt(table.getSelectionModel().getTableView().getItems().get(i).getQuantity().getText());
                   //,sort.getValue().equals("All")?0:1
                   System.out.println("q "+quantity);
                   store.getCurrentManager().placeOrder(book.getISBN(),book.getPublisher().getName(),quantity);
               }
           }
        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();
       }
    }


    public void show() {


        try {
            Parent root = FXMLLoader.load(getClass().getResource("showBook.fxml"));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("showBook.fxml"));
            int number = table.getSelectionModel().getSelectedIndex();
        //    System.out.println(table.getSelectionModel().getTableView().getItems().get(0).getQuantity().getText());
            //get specified book details
            Book book = books.get(number);
            root = loader.load();
            BookDetailsController c = loader.getController();
            c.loadBook(book);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Book details");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Book.setCellValueFactory(new PropertyValueFactory<BookTable, String>("Book"));
        ISPN.setCellValueFactory(new PropertyValueFactory<BookTable, String>("ISPN"));
        Select.setCellValueFactory(new PropertyValueFactory<BookTable, String>("Select"));
        Quantity.setCellValueFactory(new PropertyValueFactory<BookTable, String>("Quantity"));

    }

    public void loadOrders(BookStore store) throws SQLException {
        this.store=store;
        table.setItems(getData());
    }
}
