package Backend.Models;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Book {

	public Book(String view_ISBN, String view_title, String view_publisher, String view_publicationDate,
			String view_category, String view_price, String view_threshold, String view_numberOfCopies) {
		this.view_ISBN = view_ISBN;
		this.view_title = view_title;
		this.view_publisher = view_publisher;
		this.view_publicationDate = view_publicationDate;
		this.view_category = view_category;
		this.view_price = view_price;
		this.view_threshold = view_threshold;
		this.view_numberOfCopies = view_numberOfCopies;
	}

	public Book() {
	}

	private int ISBN;
	private String title;
	private Double price;
	private int threshold;
	private int numberOfCopies;
	private Publisher publisher;
	private Category category;
	private List<String> authors;
	private String publicationDate;

	// used for viewing
	private String view_ISBN;
	private String view_title;
	private String view_publisher;
	private String view_publicationDate;
	private String view_category;
	private String view_price;
	private String view_threshold;
	private String view_numberOfCopies;

}
