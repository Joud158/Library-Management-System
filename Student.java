import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author JoudS
 * Description: A student must be identified using their name, address, phone number, email address, faculty, and ID.
 * This class (Student) is a subclass of User.
 * This class implements LibraryServices interface.
 */
public class Student extends User implements LibraryServices {
    private Faculty faculty;
    private final String ID;
    protected Logger logger;

    /**
     * Constructor for Student class.
     * Calls the constructor of its superclass (User).
     * @param name The name of the student.
     * @param address The address of the student.
     * @param phoneNumber The phone number of the student.
     * @param emailAddress The email address of the student.
     * @param faculty The faculty this student's major belongs to.
     * @param ID The ID of the student (a 9-digit number starting with the year they joined the university).
     * @throws IllegalArgumentException if the ID is invalid.
     */
    public Student(String name, String address, String phoneNumber, String emailAddress, Faculty faculty, String ID) {
        super(name, address, phoneNumber, emailAddress);
        if (!isValidID(ID)) {
            throw new IllegalArgumentException("Invalid ID format. Must be a 9-digit number starting with the year.");
        }
        this.faculty = faculty;
        this.ID = ID;
        this.logger = new Logger();
    }

    /**
     * Validates the student ID format.
     * @param ID The ID to validate.
     * @return true if the ID is valid, false otherwise.
     */
    private boolean isValidID(String ID) {
        return ID.matches("^(20\\d{2})\\d{5}$"); // Format: 4-digit year + 5 random digits
    }

    /**
     * Getter for ID.
     * @return The student's ID.
     */
    public String getID() {
        return ID;
    }

    /**
     * Getter for Faculty.
     * @return The student's faculty.
     */
    @Override
    public Faculty getFaculty() {
        return faculty;
    }

    /**
     * Searches for the library within the student's faculty that contains the specified book.
     * @param book The book to search for.
     * @return The library containing the book, or null if not found.
     */
    private Library findLibraryWithBook(Book book) {
        for (Library library : faculty.getLibraries()) {
            if (library.getBooks().contains(book)) {
                return library;
            }
        }
        return null;
    }

    /**
     * Searches for the library within the student's faculty that contains the specified electronic resource.
     * @param resource The resource to search for.
     * @return The library containing the resource, or null if not found.
     */
    private Library findLibraryWithResource(ElectronicResources resource) {
        for (Library library : faculty.getLibraries()) {
            if (library.getResources().contains(resource)) {
                return library;
            }
        }
        return null;
    }

    /**
     * Searches for the library within the student's faculty that contains the specified meeting room.
     * @param room The room to search for.
     * @return The library containing the room, or null if not found.
     */
    private Library findLibraryWithRoom(MeetingRooms room) {
        for (Library library : faculty.getLibraries()) {
            if (library.getRooms().contains(room)) {
                return library;
            }
        }
        return null;
    }

    @Override
    public void borrowBook(Book book) {
        Library library = findLibraryWithBook(book);
        if (library == null) {
            System.out.println("This book does not belong to a library in your faculty.");
            return;
        }

        if (book.isAvailable() && book instanceof PhysicalBook && ((PhysicalBook) book).CanBorrow()) {
            book.setAvailable(false);
            BorrowingData borrowingData = new BorrowingData(this.getName(), book.getTitle(), LocalDate.now(), "BOOK");
            logger.addLog(borrowingData);
            library.admin.addUser(this); // Add user to admin logs
            System.out.println("Book '" + book.getTitle() + "' has been borrowed successfully.");
        } else {
            System.out.println("This book is currently unavailable. Would you like to join the waiting list?");
            Scanner keyboard = new Scanner(System.in);
            String answer = keyboard.next();
            if(answer.equalsIgnoreCase("yes")) {
            	Reservation reservation = new Reservation(book);
                reservation.addToWaitingList(this, library);
            }
            keyboard.close();
        }
    }

    @Override
    public void returnBook(Book book) {
        Library library = findLibraryWithBook(book);
        if (library == null) {
            System.out.println("This book does not belong to a library in your faculty.");
            return;
        }

        Rules rules = library.getRules();
        if (rules == null) {
            System.out.println("Library rules are not configured. Please contact the admin.");
            return;
        }

        BorrowingData borrowingData = logger.getLogs().stream()
                .filter(log -> log.getResourceTitle().equals(book.getTitle()) && log.getReturnDate() == null)
                .findFirst().orElse(null);

        if (borrowingData == null) {
            System.out.println("No active borrowing record found for this book.");
            return;
        }

        LocalDate borrowDate = borrowingData.getBorrowDate();
        LocalDate returnDate = LocalDate.now();
        borrowingData.setReturnDate(returnDate);

        LocalDate dueDate = borrowDate.plusDays(rules.getStudentBorrowDays());
        if (returnDate.isAfter(dueDate)) {
            long overdueDays = java.time.temporal.ChronoUnit.DAYS.between(dueDate, returnDate);
            double penalty = overdueDays * rules.getLatePenalty();
            System.out.println("Late return detected. You are charged a penalty of $" + penalty + ".");
        } else {
            System.out.println("Book returned on time. Thank you!");
        }

        book.setAvailable(true);
    }

    @Override
    public void borrowElectronicResource(ElectronicResources resource) {
        ArrayList<Library> libraries = faculty.getLibraries();
        boolean resourceFound = false;

        for (Library library : libraries) {
            if (library.getResources().contains(resource)) {
                resourceFound = true;
                if (resource.isAvailable()) {
                    resource.setAvailable(false);
                    BorrowingData borrowingData = new BorrowingData(this.getName(), resource.toString(), LocalDate.now(), "ElectronicResource");
                    logger.addLog(borrowingData);
                    System.out.println("Electronic Resource '" + resource + "' has been borrowed successfully.");
                    return;
                } else {
                    System.out.println("This electronic resource is currently unavailable.");
                    return;
                }
            }
        }

        if (!resourceFound) {
            System.out.println("This electronic resource belongs to a different faculty. You can only borrow resources from your own faculty's libraries.");
        }
    }


    @Override
    public void returnElectronicResource(ElectronicResources resource) {
        if (resource.isAvailable()) {
            System.out.println("This resource is already marked as available.");
            return;
        }

        resource.setAvailable(true);
        System.out.println("The electronic resource has been successfully returned.");
    }

    @Override
    public void reserveRoom(MeetingRooms room) {
        ArrayList<Library> libraries = faculty.getLibraries();
        boolean roomFound = false;

        for (Library library : libraries) {
            if (library.getRooms().contains(room)) {
                roomFound = true;
                if (!room.Reserved) {
                    room.Reserved = true;
                    BorrowingData borrowingData = new BorrowingData(this.getName(), room.getLocation(), LocalDate.now(), "ROOM");
                    logger.addLog(borrowingData);
                    System.out.println("Meeting room '" + room.getLocation() + "' has been reserved successfully.");
                    return;
                } else {
                    System.out.println("This room is already reserved.");
                    return;
                }
            }
        }

        if (!roomFound) {
            System.out.println("This meeting room is not available in your faculty's libraries.");
        }
    }



    @Override
    public void releaseRoom(MeetingRooms room) {
        if (!room.Reserved) {
            System.out.println("This room is not currently reserved.");
            return;
        }

        room.Reserved = false;
        System.out.println("Meeting room '" + room.getLocation() + "' has been released.");
    }

    @Override
    public void searchBooks() {
        ArrayList<Library> libraries = faculty.getLibraries();
        if (libraries.isEmpty()) {
            System.out.println("No libraries found for this faculty.");
            return;
        }
        for (Library library : libraries) {
            System.out.println("Books in " + library.name + ":");
            for (Book book : library.getBooks()) {
                System.out.println(book);
            }
        }
    }

    @Override
    public void searchByCriteria(String criterion, String value) {
        ArrayList<Library> libraries = faculty.getLibraries();
        for (Library library : libraries) {
            ArrayList<Book> books = library.getBooks();
            for (Book book : books) {
                switch (criterion.toLowerCase()) {
                    case "title":
                        if (book.getTitle().equalsIgnoreCase(value)) {
                            printBookFound(book);
                        }
                        break;
                    case "author":
                        if (book.getAuthor().equalsIgnoreCase(value)) {
                            printBookFound(book);
                        }
                        break;
                    case "isbn":
                        if (book.getISBN().equalsIgnoreCase(value)) {
                            printBookFound(book);
                        }
                        break;
                    case "genre":
                        if (book.getGenre().equalsIgnoreCase(value)) {
                            printBookFound(book);
                        }
                        break;
                    default:
                        System.out.println("Invalid search criterion.");
                }
            }
        }
    }

    @Override
    public ArrayList<BorrowingData> showHistory() {
        return logger.getLogs();
    }

    @Override
    public String toString() {
        return super.toString() + "\nFaculty: " + faculty.name + "\nID: " + ID + "\nHistory: " + logger.getLogs();
    }
}

