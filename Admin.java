import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author JoudS
 * Description: An admin is the person responsible for managing the library resources.
 * Fields/Attributes include: name, address, phone number, email address, schedule, the library they manage, logger, and users.
 * This class (Admin) is a subclass of User.
 */
public class Admin extends User {
    protected String schedule;
    protected Library library;
    protected Logger loggers; // Includes borrowing/returning history.
    protected HashSet<User> users; // Users who used services of the library.

    /**
     * Constructor for Admin class.
     * Calls the constructor of its superclass (User).
     * @param name The name of the admin.
     * @param address The address of the admin.
     * @param phoneNumber The phone number of the admin.
     * @param emailAddress The email address of the admin.
     * @param schedule The admin's schedule.
     * @param library The library the admin is managing.
     */
    public Admin(String name, String address, String phoneNumber, String emailAddress, String schedule, Library library) {
        super(name, address, phoneNumber, emailAddress);
        this.schedule = schedule;
        this.library = library;
        this.loggers = new Logger();
        this.users = new HashSet<>();
    }

    /**
     * Adds a book to the library managed by the admin.
     * @param book The book to add.
     */
    public void addBook(Book book) {
        library.getBooks().add(book);
        System.out.println("Admin added a new book: " + book.getTitle());
    }

    /**
     * Removes a book from the library managed by the admin.
     * @param book The book to remove.
     */
    public void removeBook(Book book) {
        library.getBooks().remove(book);
        System.out.println("Admin removed the book: " + book.getTitle());
    }

    /**
     * Displays all books in the library managed by the admin.
     */
    public void viewAllBooks() {
        System.out.println("Books in " + library.name + ":");
        for (Book book : library.getBooks()) {
            System.out.println(book);
        }
    }

    /**
     * Displays the borrowing logs for the library managed by the admin.
     */
    public void viewBorrowLogs() {
        System.out.println("Borrow Logs:");
        for (BorrowingData log : loggers.getLogs()) {
            System.out.println(log);
        }
    }

    /**
     * Displays all users who have used the library's resources.
     */
    public void viewUsers() {
        System.out.println("Users who have used the library:");
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * Configures the rules for the library managed by the admin.
     * @param rules The rules to configure.
     */
    public void configureRules(Rules rules) {
        library.setRules(rules);
        System.out.println("Admin configured new rules for the library.");
    }

    /**
     * Adds a user to the library's user list and logs their borrowing history.
     * @param user The user to add.
     */
    public void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
            System.out.println("User " + user.getName() + " added to the system.");
        }

        // Add unique borrowing logs
        ArrayList<BorrowingData> userLogs = (user instanceof Student) ? ((Student) user).logger.getLogs() : ((Professor) user).logger.getLogs();
        for (BorrowingData data : userLogs) {
            if (!loggers.getLogs().contains(data)) {
                loggers.addLog(data);
            }
        }
    }


    /**
     * Logs borrowing history for the library.
     * @param borrowingDataList The borrowing data to log.
     */
    private void logBorrowingHistory(ArrayList<BorrowingData> borrowingDataList) {
        for (BorrowingData data : borrowingDataList) {
            loggers.addLog(data);
        }
    }

    /**
     * Displays the most popular resources (books and electronic resources) in the library.
     */
    public void viewPopularResources() {
        HashMap<String, Integer> resourceBorrowCount = new HashMap<>();

        for (BorrowingData log : loggers.getLogs()) {
            String resourceTitle = log.getResourceTitle();
            resourceBorrowCount.put(resourceTitle, resourceBorrowCount.getOrDefault(resourceTitle, 0) + 1);
        }

        System.out.println("Popular Resources:");
        resourceBorrowCount.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue())) // Sort by count
                .forEach(entry -> System.out.println(entry.getKey() + " - Borrowed " + entry.getValue() + " times"));
    }

    /**
     * Displays the most frequently borrowing users of the library.
     */
    public void viewFrequentUsers() {
        HashMap<String, Integer> userBorrowCount = new HashMap<>();

        for (BorrowingData log : loggers.getLogs()) {
            String userName = log.getUserName();
            userBorrowCount.put(userName, userBorrowCount.getOrDefault(userName, 0) + 1);
        }

        System.out.println("Frequently Borrowing Users:");
        userBorrowCount.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue())) // Sort by borrow count
                .forEach(entry -> System.out.println(entry.getKey() + " - Borrowed " + entry.getValue() + " times"));
    }

    /**
     * Displays overdue resources (books and electronic resources) in the library.
     */
    public void viewOverdueResources() {
        ArrayList<String> overdueResources = new ArrayList<>();

        for (BorrowingData log : loggers.getLogs()) {
            LocalDate borrowDate = log.getBorrowDate();
            LocalDate returnDate = log.getReturnDate();

            Rules rules = library.getRules();
            if (rules == null) {
                System.out.println("Library rules are not configured. Cannot determine overdue resources.");
                continue;
            }

            // Determine maximum borrowing days based on user type (Professor or Student)
            int maxBorrowDays = log.getUserName().startsWith("Dr.") 
                    ? rules.getProfessorBorrowDays() 
                    : rules.getStudentBorrowDays();

            // Check if the resource is overdue
            if (returnDate == null || returnDate.isAfter(borrowDate.plusDays(maxBorrowDays))) {
                overdueResources.add(log.getResourceTitle());
            }
        }

        System.out.println("Overdue Resources:");
        if (overdueResources.isEmpty()) {
            System.out.println("No overdue resources found.");
        } else {
            overdueResources.forEach(resource -> System.out.println(resource + " is overdue."));
        }
    }


    @Override
    public String toString() {
        return super.toString() + "\nSchedule: " + schedule + "\nLibrary: " + library.name;
    }

	@Override
	public Faculty getFaculty() {
		return null;
	}

	public void addRooms(MeetingRooms meetingRooms) {
		library.getRooms().add(meetingRooms);
	}

	public void addResources(PCs pCs) {
		library.getResources().add(pCs);
	}

	public void addResources(Tablet tablet) {
		library.getResources().add(tablet);
		
	}
}
