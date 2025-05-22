/**
 * @author JoudS
 * Description: A Tablet has a brand and shelf. It extends ElectronicResources.
 * Tablets are used only within the library.
 */
public class Tablet extends ElectronicResources {
	protected String brand;
	protected String shelf;
	
	/**
	 * Description: Constructor for Tablet class
	 * Initializes the brand and shelf location of the tablet.
	 * @param brand The brand of the tablet (e.g., Apple, Samsung)
	 * @param shelf The shelf location of the tablet in the library
	 */
	public Tablet(String brand, String shelf) {
		super();
		this.brand = brand;
		this.shelf = shelf;
	}
	
	/**
     * Description: This method defines how a tablet resource is used by a user.
     * @param user The user (Student or Professor) who is using the tablet.
     */
	@Override
	public void useResource(User user) {
		System.out.println(user.getName()+ " is using Tablet of brand "+brand+" from shelf "+shelf);
	}
	
	//Getters and Setters:
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getShelf() {
		return shelf;
	}

	public void setShelf(String shelf) {
		this.shelf = shelf;
	}

	@Override
	public String toString() {
		return "Tablet [Brand: " + brand + ", Shelf: " + shelf + ", Available: " + (isAvailable() ? "Yes" : "No") + "]";
	}
}
