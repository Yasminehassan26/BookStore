package gui.bookstore;

import Backend.Models.Book;
import Backend.Models.BookStore;
import Backend.Models.Cart;
import Backend.Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
public class HomepageController implements Initializable {


    ObservableList<BookTable> list;
    /**
     * function that delete selected mails from the table
     */
    ObservableList<BookTable> sent;
    @FXML
    private TableView<BookTable> table;
    @FXML
    private TextField search;

    @FXML
    private TableColumn<BookTable, String> Book;
    @FXML
    private TableColumn<BookTable, String> ISPN;
    @FXML
    private TableColumn<BookTable, String> Price;
    @FXML
    private TableColumn<BookTable, String> Select;
    @FXML
    private TableColumn<BookTable, String> Quantity;
    @FXML
    private ComboBox<String> sort;
    @FXML
    private ComboBox<String> Filter;
    @FXML
    private Label page;
    @FXML
    private Label labelBooks;
    BookStore store;
    public   List<Book> books = new ArrayList<>();
//////////////////////////////////////////////////////////////////////////////////////

    public void receive(BookStore store) throws SQLException {
         this.store=store;
        System.out.println("entered");
        labelBooks.setVisible(false);
        table.setItems(getData());

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        labelBooks.setVisible(false);

        Book.setCellValueFactory(new PropertyValueFactory<BookTable, String>("Book"));
        ISPN.setCellValueFactory(new PropertyValueFactory<BookTable, String>("ISPN"));
        Price.setCellValueFactory(new PropertyValueFactory<BookTable, String>("Price"));
        Select.setCellValueFactory(new PropertyValueFactory<BookTable, String>("Select"));
        Quantity.setCellValueFactory(new PropertyValueFactory<BookTable, String>("Quantity"));
        table.setOnMouseClicked(e -> {
            if (!table.getItems().isEmpty()) {
                show();
            }
        });


    }


    public ObservableList<BookTable> getData() throws SQLException {
        list = FXCollections.observableArrayList();
        String searchType=sort.getValue();
        System.out.println(searchType);

        String inputSearch=search.getText();
        System.out.println(searchType);
        if(searchType.equals("All")){

             books=store.getCurrentUser().viewBooks();
        }
        else if(searchType.equals("ISBN")){
           books=store.getCurrentUser().searchByISBN(Integer.parseInt(inputSearch));
        }   else if(searchType.equals("Title")){
            System.out.println("entered");
            books=store.getCurrentUser().searchByTitle(inputSearch);

        }else if(searchType.equals("Author")){
            books=store.getCurrentUser().searchByAuthor(inputSearch);

        }else if(searchType.equals("Publisher")){
            books=store.getCurrentUser().searchByPublisher(inputSearch);

        }else if(searchType.equals("Publication Year")){
            books=store.getCurrentUser().searchByYear(inputSearch);

        }else if(searchType.equals("Category")){
            books=store.getCurrentUser().searchByCategory(inputSearch);
        }
            for (int i = 0; i < books.size(); i++) {
                System.out.println("e");
                Book book = books.get(i);
                System.out.println(books.get(i).getTitle());
                list.add(new BookTable(book.getTitle(), String.valueOf(book.getISBN()),String.valueOf(book.getPrice()),"1"));

            }


        return list;
    }
    public void okay(ActionEvent event) throws SQLException {
        table.setItems(getData());
    }
    @FXML
    public void addSelected(ActionEvent event) throws SQLException {
        ArrayList<Book> selectedItems = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSelect().isSelected()) {
                Book book=books.get(i);
                int quantity=Integer.parseInt(table.getSelectionModel().getTableView().getItems().get(i).getQuantity().getText());
                //,sort.getValue().equals("All")?0:1
                System.out.println("q "+quantity);
                store.getCurrentUser().addToCart(book,quantity);
                selectedItems.add(book);
            }
        }

        if (selectedItems.isEmpty()) {
            labelBooks.setVisible(true);
            labelBooks.setText("No books Selected!");
        }
    }



    public void show() {

        try {
            Parent root =FXMLLoader.load(getClass().getResource("showBook.fxml"));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("showBook.fxml"));
            int number = table.getSelectionModel().getSelectedIndex();
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

    public void backMain(ActionEvent event) {
        ScenesHandler test = new ScenesHandler();
        test.backMain(event);
    }


    public void profile(ActionEvent event) {
        try {
            Parent root =FXMLLoader.load(getClass().getResource("Profile.fxml"));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
            root = loader.load();
            ProfileController c = loader.getController();
            User user = store.getCurrentUser();
            c.loadProfile(store,user);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("My Profile");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Cart(ActionEvent event) {
        try {
            Parent root =FXMLLoader.load(getClass().getResource("UserCart.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserCart.fxml"));
            root = loader.load();
            UserCartController c = loader.getController();
            c.loadCart(this.store);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("My cart");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}