package gui.bookstore;


import Backend.Models.Book;
import Backend.Models.BookStore;
import Backend.Models.Cart;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StoreCart implements Initializable {


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
    private TableColumn<BookTable, String> Price;
    @FXML
    private TableColumn<BookTable, String> Quantity;
  BookStore store;
    List<Book> books=new ArrayList<>();


    public ObservableList<BookTable> getData() throws SQLException {
        ResultSet set=store.getCurrentManager().getOrders();
        list = FXCollections.observableArrayList();
        books=store.getCurrentUser().viewBooks();

        while(set.next()){
            list.add(new BookTable(String.valueOf(set.getInt("orderId")),String.valueOf(set.getInt("ISBN")),set.getString("Publisher"),String.valueOf(set.getInt("Quantity"))));

        }
        return list;

    }

    public void confirm(ActionEvent event) throws SQLException {
        if (list!=null && list.size()>0) {
//            //confirm the order
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getSelect().isSelected()) {
                    int order=Integer.parseInt(table.getSelectionModel().getTableView().getItems().get(i).getBook());
                    //,sort.getValue().equals("All")?0:1
                    store.getCurrentUser().confirmOrder(order);
                }
            }
            Node node = (Node) event.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
        }
    }




    public void show() {

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

           // ObservableList<BookTable> received = getData();
            Book.setCellValueFactory(new PropertyValueFactory<BookTable, String>("Book"));
            ISPN.setCellValueFactory(new PropertyValueFactory<BookTable, String>("ISPN"));
        Price.setCellValueFactory(new PropertyValueFactory<BookTable, String>("Price"));

        Select.setCellValueFactory(new PropertyValueFactory<BookTable, String>("Select"));
            Quantity.setCellValueFactory(new PropertyValueFactory<BookTable, String>("Quantity"));
          //  table.setItems(received);

    }
    public void loadOrders(BookStore store) throws SQLException {
        this.store=store;
        table.setItems(getData());
    }
}
