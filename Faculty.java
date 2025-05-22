import java.util.ArrayList;

/**
 * @author JoudS
 * Description: A university can have multiple faculties. Each faculty has multiple libraries.
 * It has a name and a list of libraries.
 */
public class Faculty {
	protected String name;
	protected ArrayList<Library> libraries;
	
	/**
	 * Description: This is the constructor of the Faculty class.
	 * A new ArrayList is created when a Faculty object is created.
	 * @param name
	 */
	public Faculty(String name) {
		this.name = name;
		this.libraries = new ArrayList<>();
	}
	
	/**
	 * Description: This is a default constructor.
	 * It initializes the name to "Unknown Faculty" and creates a new ArrayList for libraries.
	 */
	public Faculty() {
	    this.name = "Unknown Faculty";
	    this.libraries = new ArrayList<>();
	}
	
	/**
	 * Description: This method is used to add libraries belonging to the faculty.
	 * @param library
	 * @throws IllegalArgumentException if library is null
	 */
	public void addLibrary(Library library) {
		if (library == null) {
	        throw new IllegalArgumentException("Library cannot be null.");
	    }
		this.libraries.add(library);
	}
	
	/**
	 * Description: This method shows the list of libraries belonging to this faculty.
	 * @return libraries
	 */
	public ArrayList<Library> getLibraries(){
		if (this.libraries.isEmpty()) {
	        System.out.println("No libraries available for this faculty.");
	    }
		return this.libraries;
	}
	
	/**
	 * Description: This method is used to print the libraries of this faculty.
	 */
	@Override
	public String toString() {
		return name+" Libraries: "+this.getLibraries();
	}
}
