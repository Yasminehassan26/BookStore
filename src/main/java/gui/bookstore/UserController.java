package gui.bookstore;

import Backend.Models.BookStore;
import Backend.Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserController implements Initializable {


    ObservableList<UsersTable> list;
    boolean first = false;
    @FXML
    private Label totalPrice;
    @FXML
    private Label labelDelete;
    @FXML
    private TableView<UsersTable> table;
    @FXML
    private TableColumn<UsersTable, String> ID;
    @FXML
    private TableColumn<UsersTable, String> UserName;
    @FXML
    private TableColumn<UsersTable, String> Select;

    BookStore store;
    List<User> users = new ArrayList<>();

    public ObservableList<UsersTable> getData() throws SQLException {

        list = FXCollections.observableArrayList();
        List<User> Myusers = store.getCurrentManager().getUsers();
        for (int i = 0; i < Myusers.size(); i++) {
            User curr = Myusers.get(i);
            list.add(new UsersTable(String.valueOf(curr.getId()), curr.getUsername()));
            users.add(Myusers.get(i));
        }
        return list;

    }

    public void confirm(ActionEvent event) throws SQLException {
        if (list != null && list.size() > 0) {
            //confirm the order
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getSelect().isSelected()) {
                    int ind = i;
                    store.getCurrentManager().promoteUser(users.get(i).getUsername());
                }
            }
            Node node = (Node) event.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
        }
    }


    public void deleteItems(ActionEvent event) {
        ObservableList<UsersTable> Deleted = FXCollections.observableArrayList();
        List<BookTable> deletedBooks = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSelect().isSelected()) {
                //delete backend
//                int indexOfMail = ((apps.pageNumber*10)-10)+i;
//                Mail m = (Mail) apps.filteredList.get(indexOfMail);
//                deletedBooks.add(m);
                Deleted.add(list.get(i));
            }
        }
        if (deletedBooks.isEmpty()) {
            labelDelete.setText("No items Selected!");
        } else {
            labelDelete.setText("");
            list.removeAll(Deleted);

        }
        table.setItems(list);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ID.setCellValueFactory(new PropertyValueFactory<UsersTable, String>("ID"));
        UserName.setCellValueFactory(new PropertyValueFactory<UsersTable, String>("UserName"));
        Select.setCellValueFactory(new PropertyValueFactory<UsersTable, String>("Select"));

    }

    public void setProps(BookStore store) throws SQLException {
        this.store = store;
        table.setItems(getData());
    }
}