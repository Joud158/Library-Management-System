import java.time.LocalDate;

/**
 * @author JoudS
 * Description: The aim of this project is to help library staff manage book inventory, book lending,
 * library conference rooms, and library-provided electronic resources.
 * The system will also allow users to search for books, borrow or return them, and monitor their borrowing history.
 */
public class LibraryManagementSystem {

    public static void main(String[] args) {

        // Initialize Faculties at AUB
        Faculty MSFEA = new Faculty("Maroun Semaan Faculty of Engineering and Architecture");
        Faculty FAS = new Faculty("Faculty of Arts and Sciences");
        Faculty OSB = new Faculty("Suliman S. Olayan School of Business");

        // Initialize 3 Libraries for each Faculty
        Library msfeaLib1 = new Library("Engineering Library 1", MSFEA);
        Library msfeaLib2 = new Library("Engineering Library 2", MSFEA);
        Library msfeaLib3 = new Library("Engineering Library 3", MSFEA);

        Library fasLib1 = new Library("FAS Library 1", FAS);
        Library fasLib2 = new Library("FAS Library 2", FAS);
        Library fasLib3 = new Library("FAS Library 3", FAS);

        Library osbLib1 = new Library("OSB Library 1", OSB);
        Library osbLib2 = new Library("OSB Library 2", OSB);
        Library osbLib3 = new Library("OSB Library 3", OSB);

        // Associate Libraries with Faculties
        MSFEA.addLibrary(msfeaLib1);
        MSFEA.addLibrary(msfeaLib2);
        MSFEA.addLibrary(msfeaLib3);

        FAS.addLibrary(fasLib1);
        FAS.addLibrary(fasLib2);
        FAS.addLibrary(fasLib3);

        OSB.addLibrary(osbLib1);
        OSB.addLibrary(osbLib2);
        OSB.addLibrary(osbLib3);

        // Create Admins for each Library
        Admin bilal = new Admin("Bilal", "Beirut", "70888888", "bilal@aub.edu.lb", "8am-4pm", msfeaLib1);
        Admin john = new Admin("John", "Hamra", "81676767", "john@aub.edu.lb", "8am-4pm", fasLib1);
        Admin yasmina = new Admin("Yasmina", "Jounieh", "78989898", "yasmina@aub.edu.lb", "8am-3pm", osbLib1);

        msfeaLib1.setAdmin(bilal);
        fasLib1.setAdmin(john);
        osbLib1.setAdmin(yasmina);

        // Configure Rules for Each Library
        Rules msfeaRules = new Rules(3, 7, true, false, 1.5);
        Rules fasRules = new Rules(4, 10, true, true, 2.0);
        Rules osbRules = new Rules(2, 5, false, false, 1.0);

        bilal.configureRules(msfeaRules);
        john.configureRules(fasRules);
        yasmina.configureRules(osbRules);

        // Add Resources to Libraries
        bilal.addBook(new PhysicalBook("Design of Structures", "An engineering classic", "1234567890123", "J. Doe", "2015", "Engineering", true));
        john.addBook(new eBook("Modern Physics", "Quantum Mechanics Simplified", "3234567890123", "N. Einstein", "2020", "Physics", "http://faslibrary.aub.edu/modern-physics"));
        yasmina.addBook(new PhysicalBook("Business Analytics", "Data-driven decision making", "6234567890123", "P. Drucker", "2021", "Business", true));

        bilal.addRooms(new MeetingRooms("MSFEA Room 101", "01344444"));
        john.addRooms(new MeetingRooms("FAS Room 202", "01455555"));
        yasmina.addRooms(new MeetingRooms("OSB Room 303", "01566666"));

        bilal.addResources(new PCs("MSFEA Lab"));
        john.addResources(new PCs("FAS Lab"));
        yasmina.addResources(new Tablet("iPad", "OSB Shelf A"));

        // Create Users (Students and Professors)
        Student mhmd = new Student("Mohamad", "Hamra", "71555555", "mhmd@aub.edu.lb", MSFEA, "202300001");
        Student rita = new Student("Rita", "Hamra", "76111111", "rita@aub.edu.lb", FAS, "202300002");
        Professor drHassan = new Professor("Dr. Hassan", "Hamra", "03333333", "hassan@aub.edu.lb", MSFEA, "Professor");
        Professor drLeila = new Professor("Dr. Leila", "Hamra", "04123456", "leila@aub.edu.lb", OSB, "Lecturer");

        // Example Scenarios
        System.out.println("==== Borrowing Scenario ====");
        mhmd.borrowBook(msfeaLib1.getBooks().get(0)); // Borrow "Design of Structures" from MSFEA library
        rita.borrowBook(fasLib1.getBooks().get(0)); // Borrow "Modern Physics" from FAS library
        drLeila.borrowBook(osbLib1.getBooks().get(0)); // Borrow "Business Analytics" from OSB library
        mhmd.returnBook(msfeaLib1.getBooks().get(0)); // Return "Design of Structures"

        System.out.println("\n==== Room Reservation Scenario ====");
        mhmd.reserveRoom(msfeaLib1.getRooms().get(0)); // Reserve "MSFEA Room 101"
        mhmd.releaseRoom(msfeaLib1.getRooms().get(0)); // Release "MSFEA Room 101"
        drHassan.reserveRoom(msfeaLib1.getRooms().get(0)); // Reserve "MSFEA Room 101" again

        System.out.println("\n==== Electronic Resource Borrowing Scenario ====");
        rita.borrowElectronicResource(fasLib1.getResources().get(0)); // Borrow "FAS Lab PC"
        drLeila.borrowElectronicResource(osbLib1.getResources().get(0)); // Borrow "OSB iPad"

        System.out.println("\n==== Library Statistics ====");
        bilal.viewBorrowLogs();
        bilal.viewPopularResources();
        bilal.viewFrequentUsers();

        System.out.println("\n==== Library Overview ====");
        System.out.println(msfeaLib1);
        System.out.println(fasLib1);
        System.out.println(osbLib1);

        System.out.println("\n==== User History ====");
        System.out.println("Mohamad's Borrowing History: " + mhmd.showHistory());
        System.out.println("Dr. Hassan's Borrowing History: " + drHassan.showHistory());
    }
}

