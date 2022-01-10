package Backend.Models;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class Cart {

    private HashMap<Book, Integer> books = new HashMap<>();
    private Double totalPrice = 0.0;

    public HashMap<Book, Integer> viewCart()
    {
        return this.books;
    }

//    public Book getBookById(Integer id)
//    {
//        return books.get(id);
//    }

    public void addBook(Book book){
        books.put(book, books.getOrDefault(book, 0)+1);
        totalPrice += book.getPrice();
    }

    public void removeBook(Book book)
    {
        books.put(book, books.getOrDefault(book, 0)-1);
        if (books.get(book)==0) books.remove(book);
        totalPrice -= book.getPrice();
    }
}
