/**
 * @author JoudS
 * Description: Each library has a set of meeting rooms that can be reserved by students and professors. 
 * Fields/Attributes: A meeting room has a location, phone number, and a boolean Reserved if the room is available or not.
 */
public class MeetingRooms {
	protected String location;
	protected String phoneNumber;
	protected boolean Reserved;
	
	/**
	 * Description: This is the constructor of MeetingRooms.
	 * @param location specifies the location of the Meeting Room.
	 * @param phoneNumber specifies the phone number we need to call to reserve the room.
	 * Reserved a boolean that specifies if the room is available (false) or not (true) initially set to false
	 */
	public MeetingRooms(String location, String phoneNumber) {
		this.location = location;
		this.phoneNumber = phoneNumber;
		this.Reserved = false;
	}
	
	/**
	 * Description: shows the phone number we need to call to reserve the room.
	 * @return phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * Description: shows the location of the meeting room
	 * @return location
	 */
	public String getLocation() {
		return location;
	}
	
	@Override
	public String toString() {
		String answer;
		if(!Reserved) {
			answer = "Yes";
		}
		else {
			answer = "No";
		}
		return "Meeting room: "+"\nLocation: "+location+"\nPhone Number: "+phoneNumber+"\nAvailablity: "+answer;
	}
}
