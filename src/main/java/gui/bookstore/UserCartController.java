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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserCartController implements Initializable {



    @FXML
    private Label totalPrice;
    @FXML
    private Label labelDelete;
    @FXML
    private TableView<BookTable> table;
    @FXML
    private TextField credit;
    @FXML
    private TextField expireDate;

    @FXML
    private TableColumn<BookTable, String> Book;
    @FXML
    private TableColumn<BookTable, String> ISPN;

    @FXML
    private TableColumn<BookTable, String> Select;
    @FXML
    private TableColumn<BookTable, String> Quantity;
    public ObservableList<BookTable> list;
    public BookStore store;
    boolean first = false;
    List<Book> books = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Book.setCellValueFactory(new PropertyValueFactory<BookTable, String>("Book"));
        ISPN.setCellValueFactory(new PropertyValueFactory<BookTable, String>("ISPN"));
        Select.setCellValueFactory(new PropertyValueFactory<BookTable, String>("Select"));
        Quantity.setCellValueFactory(new PropertyValueFactory<BookTable, String>("Quantity"));
        // table.setItems(received);

    }

    public void loadCart(BookStore store) throws SQLException {
        this.store = store;
        System.out.println("entered");
        table.setItems(getData());
        totalPrice.setText(String.valueOf(store.getCurrentUser().getCart().getTotalPrice()));

    }

    public ObservableList<BookTable> getData() {
        list = FXCollections.observableArrayList();
        HashMap<Book, Integer> map = store.getCurrentUser().getCart().viewCart();
        for (Map.Entry<Book, Integer> set : map.entrySet()) {
            list.add(new BookTable(set.getKey().getTitle(), String.valueOf(set.getKey().getISBN()), String.valueOf(set.getKey().getPrice()), String.valueOf(set.getValue())));
            books.add(set.getKey());
        }
        return list;
    }

    public void okCredit(ActionEvent event) throws SQLException, ParseException {
        if (list != null && list.size() > 0) {

               if(credit.getText()!=null && expireDate.getText()!=null){
                store.getCurrentUser().getCart().setBooks(new HashMap<>());
                store.getCurrentUser().getCart().setTotalPrice(0.0);
                for (int i = 0; i < list.size(); i++) {
                    Book book = books.get(i);
                    int quantity = Integer.parseInt(table.getSelectionModel().getTableView().getItems().get(i).getQuantity().getText());
                    //,sort.getValue().equals("All")?0:1
                    store.getCurrentUser().addToCart(book, quantity);

                }
                store.getCurrentUser().placeOrder();
                Node node = (Node) event.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.close();
              }
            }

    }
    public void refreshPrice(ActionEvent event) {
        if (list != null && list.size() > 0) {
            //confirm the order
            int quantity=0;
            for (int i = 0; i < list.size(); i++) {
                 quantity+= Integer.parseInt(table.getSelectionModel().getTableView().getItems().get(i).getQuantity().getText());
            }
         totalPrice.setText(String.valueOf(quantity*50.0));
        }
    }

    public void deleteItems(ActionEvent event ) {
        ObservableList<BookTable>	 Deleted =  FXCollections.observableArrayList();
        List<BookTable> deletedBooks = new ArrayList<>();
        for(int i=0;i<list.size();i++) {
            if(list.get(i).getSelect().isSelected()) {
                //delete backend
                Book book = books.get(i);
                store.getCurrentUser().getCart().removeBook(book);
                Deleted.add(list.get(i));
            }
        }
        if (Deleted.size() <= 0) {
            labelDelete.setText("No items Selected!");
        } else {
            labelDelete.setText("");
            list.removeAll(Deleted);
            //remove backend
            //  apps.deleteEmails(deletedBooks);
        }
        table.setItems(list);
        totalPrice.setText(String.valueOf(store.getCurrentUser().getCart().getTotalPrice()));

    }
    public void show() {

        Parent root;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("showBook.fxml"));
            int number = table.getSelectionModel().getSelectedIndex();
           // System.out.println(table.getSelectionModel().getTableView().getItems().get(0).getQuantity().getText());
            //get specified book details
            root = loader.load();
            BookDetailsController c = loader.getController();
            c.loadBook(books.get(number));
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


}