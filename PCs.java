/**
 * @author JoudS
 * Description: A PC has a lab room. It extends ElectronicResources.
 */
public class PCs extends ElectronicResources {
	protected String room;
	
	/**
	 * Description: constructor of the PCs class
	 * @param room
	 */
	public PCs(String room) {
		super();
		this.room = room;
	}
	
	@Override
	public void useResource(User user) {
		System.out.println(user.getName() + " is working on the PC in lab " + room);
	}
	
	@Override
    public String toString() {
        return "PC [Room: " + room + ", Available: " + isAvailable() + "]";
    }
}
