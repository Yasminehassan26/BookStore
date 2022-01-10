
package gui.bookstore;

        import Backend.Models.Book;
        import Backend.Models.BookStore;
        import Backend.Models.Category;
        import Backend.Models.Publisher;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.Node;
        import javafx.scene.control.Label;
        import javafx.scene.control.ListView;
        import javafx.scene.control.TextField;
        import javafx.stage.Stage;

        import java.sql.SQLException;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.List;

public class ModifyBook {

    @FXML
    private TextField title;
    @FXML
    private TextField isbn;
    @FXML
    private TextField date;
    @FXML
    private TextField publisher;
    @FXML
    private TextField price;
    @FXML
    private TextField category;
    @FXML
    private TextField input;
    @FXML
    private TextField threshold;
    @FXML
    private TextField copies;
    @FXML
    private ListView<String> list;
    @FXML
    private Label warning;
    public BookStore store;

    public void modifyBook(BookStore store,Book book) {
        this.store = store;
        title.setText(book.getTitle());
        isbn.setText(String.valueOf(book.getISBN()));
        price.setText(String.valueOf(book.getPrice()));
        date.setText(book.getPublicationDate());
        publisher.setText(book.getPublisher().getName());
        category.setText(book.getCategory().name());
        copies.setText(String.valueOf(book.getNumberOfCopies()));
        threshold.setText(String.valueOf(book.getThreshold()));
        List<String> authors = book.getAuthors();
        if(authors!=null) {
            for(int i =0;i<authors.size();i++) {
                list.getItems().add(authors.get(i));
            }
        }
    }

    public void loadBook(ActionEvent event) throws SQLException {
        Book book = new Book();
        book.setCategory(Category.Art);
        book.setPrice(Double.parseDouble(price.getText()));
        book.setISBN(Integer.parseInt(isbn.getText()));
        book.setTitle(title.getText());
        book.setNumberOfCopies(Integer.parseInt(copies.getText()));
        book.setThreshold(Integer.parseInt(threshold.getText()));
        book.setPublicationDate(date.getText());
        Publisher p = new Publisher();
        p.setName(publisher.getText());
        book.setPublisher(p);

        List<String> authors = new ArrayList<>();
        if (list != null) {
            for (int i = 0; i < authors.size(); i++) {
                //list.getItems().add(authors.get(i));
                authors.add(list.getItems().get(i));
            }
        }
        book.setAuthors(authors);
        store.getCurrentManager().modifyBook(book);
        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();

    }


    public void addAuthor(ActionEvent event) {
        warning.setText("");
        if (!(input.getText() == null)) {
            warning.setText("");
            list.getItems().add(input.getText());
            input.clear();
        }
    }

    public void deleteAuthor(ActionEvent event) {
        list.getItems().removeAll(list.getSelectionModel().getSelectedItem());

    }



}