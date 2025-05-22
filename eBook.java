/**
 * @author JoudS
 * Description: eBooks can be checked online by the faculty’s students and professors. 
 * Fields/Attributes: Each e_book has a title, description, ISBN number, availability condition, author, publication year, genre, and url.
 * It extends Book.
 */
public class eBook extends Book {
	
	private String url;
	/**
	 * Description: eBook's constructor that calls the super class constructor.
	 * @param title The title of the e_book
	 * @param description A brief description of the specified e_book
	 * @param ISBN The International Standard Book Number (ISBN) is a 13-digit number that uniquely identifies books and book-like products published internationally.
	 * @param author The author of the book
	 * @param publicationYear The year in which this book was published
	 * @param genre The genre of the book
	 * @param url The URL where this eBook can be found
	 */
	public eBook(String title, String description, String ISBN, String author, String publicationYear, String genre, String url) {
		super(title, description, ISBN, author, publicationYear, genre);
		this.url = url;
	}
	
	//Getters and Setters
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * Description: This method provides the user with the url to access the eBook
	 */
	public void accessEBook() {
		System.out.println("You can access this eBook at this URL: "+url);
		System.out.println("Happy Reading!");
	}
	

	@Override
	public String toString() {
		return "eBook: "+super.toString()+"\nURL: "+url;
	}
}
