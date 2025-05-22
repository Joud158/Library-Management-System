/**
 * @author JoudS
 * Description: PCs and Tablets that can be used inside the library.
 * This is an abstract class. Thus, an instance of this class cannot be created.
 */
public abstract class ElectronicResources {
	protected boolean available;
	public ElectronicResources() {
		this.available = true;
	}
	/**
	 * Description: This method is an abstract method. It must be overridden in the classes extending this class.
	 * @param user
	 */
	public abstract void useResource(User user);
	
	//getters and setters
	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
}
