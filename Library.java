import java.util.ArrayList;
/**
 * @author JoudS
 * Description: Each library has its own set of resources. Students and professors are allowed to interact with the libraries belonging to their own faculty.
 * It has a name, faculty, admin, resources, books, rules and rooms.
 */
public class Library {
	protected String name;
	protected Faculty faculty;
	protected Admin admin;
	protected ArrayList<ElectronicResources> resources;
	protected ArrayList<Book> books;
	protected ArrayList<MeetingRooms> rooms;
	private Rules rules; // Library-specific rules for borrowing and penalties.
	
	/**
	 * Description: This is the constructor of the Library class.
	 * It initializes a new ArrayList for each of resources, books, and rooms when a new Library object is created.
	 * @param name The name of the library.
	 * @param faculty The faculty to which this library belongs.
	 */
	public Library(String name, Faculty faculty) {
		this.name = name;
		this.faculty = faculty;
		this.resources = new ArrayList<>();
		this.books = new ArrayList<>();
		this.rooms = new ArrayList<>();
		this.admin = null;
		this.rules = null;
	}
	
	/**
	 * Description: A method that allows adding resources to the library.
	 * @param t which is an electronic resource
	 */
	public void addResources(ElectronicResources t) {
		this.resources.add(t);
		System.out.println("Resource added: " + t);
	}
	/**
	 * Description: A method that allows adding books to the library.
	 * @param t which is book
	 */
	public void addBooks(Book t) {
		this.books.add(t);
		System.out.println("Book added: " + t.getTitle());
	}
	/**
	 * Description: A method that allows adding meeting rooms to the library.
	 * @param t which is a MeetingRoom object
	 */
	public void addRooms (MeetingRooms t) {
		this.rooms.add(t);
		System.out.println("Meeting room added: " + t.getLocation());
	}
	/**
	 * Description: A method used to show the resources in the library.
	 * @return The list of electronic resources
	 */
	public ArrayList<ElectronicResources> getResources(){
		return resources;
	}
	/**
	 * Description: A method used to show the books in the library.
	 * @return the list of books
	 */
	public ArrayList<Book> getBooks(){
		return books;
	}
	/**
	 * Description: A method used to show the rooms in the library.
	 * @return the list of meeting rooms
	 */
	public ArrayList<MeetingRooms> getRooms(){
		return rooms;
	}
	
	/**
	 * Description: A method used to set the admin of the library.
	 * @param admin The admin to set
	 */
	public void setAdmin(Admin admin) {
		this.admin = admin;
		System.out.println("Admin assigned to the library: " + admin.getName());
	}
	
	/**
     * Retrieves the faculty to which the library belongs.
     * @return The faculty of the library
     */
    public Faculty getFaculty() {
        return faculty;
    }
	
    @Override
    public String toString() {
        return "Library: " + name +
                "\nFaculty: " + faculty.name +
                "\nAdmin: " + (admin != null ? admin.getName() : "No admin assigned") +
                "\nResources: " + getResources() +
                "\nBooks: " + getBooks() +
                "\nMeeting Rooms: " + getRooms() +
                "\nRules: " + (rules != null ? rules : "Not configured");
    }
    
    /**
     * Sets the rules for the library.
     * @param rules The rules to set.
     */
	public void setRules(Rules rules) {
		this.rules = rules;
        System.out.println("Rules configured for the library: " + name);
	}
	
	 /**
     * Gets the rules of the library.
     * @return The rules of the library.
     */
	public Rules getRules() {
		return this.rules;
	}
}
