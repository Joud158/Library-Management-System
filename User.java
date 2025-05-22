	/**
 * @author JoudS
 * Description: This class is an abstract class. Therefore, you cannot create a User object.
 * It is used to simplify and organize its subclasses (Admin, Student, Professor).
 * Fields/Attributes include: name, address, phone number, and email address. These are the common fields between subclasses.
 */
public abstract class User {
	private String name;
	private String address;
	/*
	 * we make phone number a string since if we made it as an integer example: 01333333 the zero would be ignored
	 * therefore it would be interpreted as 1333333
	 */
	private String phoneNumber; 
	private String emailAddress;
	
	/**
	 * Constructor of the User class:
	 * Note that it is only called by subclasses as a super constructor.
	 * This is because it is an abstract class and a User object cannot be created.
	 * @param name The name of the user
	 * @param address The address of the user
	 * @param phoneNumber The phone number of the user
	 * @param emailAddress The email address of the user
	 * @throws IllegalArgumentException if phone number or email address is invalid
	 */
	public User(String name, String address, String phoneNumber, String emailAddress) {
		if (!isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
		if (!isValidEmail(emailAddress)) {
            throw new IllegalArgumentException("Invalid email address format");
        }
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
	}
	
	@Override
	public String toString() {
		return "Name: "+name+"\nAddress: "+address+"\nPhone Number: "+phoneNumber+"\nEmail Address: "+emailAddress;
	}

	//Getters and Setter are provided below:
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		if (!isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		if (!isValidEmail(emailAddress)) {
            throw new IllegalArgumentException("Invalid email address format");
        }
		this.emailAddress = emailAddress;
	}
	
	/**
     * Utility method to validate a phone number.
     * @param phoneNumber The phone number to validate.
     * @return true if valid, false otherwise.
     */
    protected boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\+?[0-9- ]{7,15}");
        //\\+? means the number can optionally start with a +
        //[0-9- ] specifies that the number can contain digits (0-9), dashes (-), and spaces ( ).
        //{7,15} means the phone number must have at least 7 characters and at most 15 characters.
    }
    
    /**
     * Utility method to validate an email address.
     * @param email The email address to validate.
     * @return true if valid, false otherwise.
     */
    protected boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
        //^[a-zA-Z0-9._%+-]+ ensures that the email starts with letters, numbers, or valid special characters like _, ., %, +, and -.
        //@ ensures the presence of an @ symbol.
        //[a-zA-Z0-9.-]+ allows domain names to have letters, numbers, dots (.), and dashes (-).
        //\\.[a-zA-Z]{2,6}$ ensures a valid top-level domain (e.g., .com, .org) with 2–6 characters.
    }
    
    public abstract Faculty getFaculty();
}