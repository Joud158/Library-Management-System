/**
 * @author JoudS
 * Description: Physical books can either be borrowed by students and faculties or cannot be borrowed (only accessed inside the library).
 * Fields/Attributes: Each physical book has a title, description, ISBN number, availability condition, author, publication year, genre, and canBorrow (a boolean to specify if it is from the borrowable type).
 * It extends Book.
 */
public class PhysicalBook extends Book{
	private boolean canBorrow;
	
	/**
	 * Description: This is the constructor of the PhysicalBook class. It calls its super class constructor (Book).
	 * @param title The title of the physical book
	 * @param description A brief description of the specified physical book
	 * @param ISBN The International Standard Book Number (ISBN) is a 13-digit number that uniquely identifies books and book-like products published internationally.
	 * @param author The author of the book
	 * @param publicationYear The year in which this book was published
	 * @param genre The genre of the book
	 * @param canBorrow This boolean specifies if the book can be borrowed or only used within the library.
	 */
	public PhysicalBook(String title, String description, String ISBN, String author, String publicationYear, String genre, boolean canBorrow) {
		super(title, description, ISBN, author, publicationYear, genre);
		this.canBorrow = canBorrow;
	}
	
	//Getters and Setters
	public boolean CanBorrow() {
		return canBorrow;
	}
	public void setCanBorrow(boolean canBorrow) {
		this.canBorrow = canBorrow;
	}
	
	@Override
	public String toString() {
		String answer;
		if(canBorrow) {
			answer = "Yes";
		}
		else {
			answer = "No";
		}
		return super.toString() + "\nCan be borrowed: "+answer;
	}
}
