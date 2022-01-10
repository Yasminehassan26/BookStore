package gui.bookstore;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class BookTable {
    private final SimpleStringProperty Book;
    private final SimpleStringProperty ISPN;
    private final SimpleStringProperty Price;
    private CheckBox Select;


    private TextField Quantity;

    public BookTable(String book, String ispn, String price,String q) {
        this.Book = new SimpleStringProperty(book);
        this.ISPN = new SimpleStringProperty(ispn);
        this.Price = new SimpleStringProperty(price);
        this.Select = new CheckBox();
        this.Quantity = new TextField(q);
    }

    public TextField getQuantity() {
        return Quantity;
    }

    public void setQuantity(TextField quantity) {
        Quantity = quantity;
    }

    public String getBook() {
        return Book.get();
    }

    public void setBook(String name) {
        Book.set(name);
    }

    public String getISPN() {
        return ISPN.get();
    }

    public void setISPN(String email) {
        ISPN.set(email);
    }

    public String getPrice() {
        return Price.get();
    }

    public void setPrice(String price) {
        Price.set(price);
    }

    public CheckBox getSelect() {
        return Select;
    }

    public void setSelect(CheckBox selection) {
        Select = selection;
    }


}
