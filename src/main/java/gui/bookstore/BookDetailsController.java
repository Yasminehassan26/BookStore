package gui.bookstore;


import Backend.Models.Author;
import Backend.Models.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.List;


public class BookDetailsController {

    @FXML
    private Label title;
    @FXML
    private Label isbn;
    @FXML
    private Label date;
    @FXML
    private Label publisher;
    @FXML
    private Label price;
    @FXML
    private Label category;
    @FXML
    private ListView<String> list;

    public void loadBook(Book book) {
        title.setText(book.getTitle());
        isbn.setText(String.valueOf(book.getISBN()));
        price.setText(String.valueOf(book.getPrice()));
        date.setText(book.getPublicationDate());
        publisher.setText(book.getPublisher().getName());
        category.setText(book.getCategory().name());
        List<String> authors = book.getAuthors();
        if(authors!=null) {
            for(int i =0;i<authors.size();i++) {
                list.getItems().add(authors.get(i));
            }
        }
    }


}