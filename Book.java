/**
 * @author JoudS
 * Description: Books can be physical books that can be borrowed by students and faculties and ones that cannot be borrowed (only accessed inside the library).
 * Also, the library provides eBooks that can checked online by the faculty’s students and professors.
 * Fields/Attributes: Each book has a title, description, ISBN number, availability condition, author, publication year, and genre.
 * This class is an abstract class. Therefore, you cannot create a Book object.
 */
public abstract class Book {
	private final String title;
	private final String description;
	private final String ISBN;
	private boolean available;
	private final String author;
	private final String publicationYear;
	private final String genre;
	
	/**
	 * Constructor of the Book class:
	 * Note that it is called by its subclasses only.
	 * This is because it is an abstract class and a Book object cannot be created.
	 * @param title The title of the book
	 * @param description A brief description of the specified book
	 * @param ISBN The International Standard Book Number (ISBN) is a 13-digit number that uniquely identifies books and book-like products published internationally.
	 * @param author The author of the book
	 * @param publicationYear The year in which this book was published
	 * @param genre The genre of the book
	 */
	public Book(String title, String description, String ISBN , String author, String publicationYear, String genre) {
		this.title = title;
		this.description = description;
		this.ISBN = ISBN;
		this.available = true; //if someone is using it or not (available Boolean assigned true initially as the book is available in the library and no one is using it)
		this.author = author;
		this.publicationYear = publicationYear;
		this.genre = genre;
	}
	
	//Getters and Setters provided below: 
	
	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getAuthor() {
		return author;
	}

	public String getPublicationYear() {
		return publicationYear;
	}

	public String getGenre() {
		return genre;
	}

	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getISBN() {
		return ISBN;
	}
	
	@Override
	public String toString() {
		String answer;
		if(available) {
			answer = "Yes";
		}
		else {
			answer = "No";
		}
		return "Book: "+getTitle()+"\nDescription: "+getDescription()+"\nISBN: "+getISBN() +"\nAvailability: "+answer+"\nAuthor: "+author+"\nPublication Year: "+publicationYear+ "\nGenre: "+genre;
	}
}
