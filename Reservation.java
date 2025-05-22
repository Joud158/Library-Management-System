import java.util.LinkedList;
import java.util.Queue;
/**
 * @author JoudS
 * Description: This class manages the reservation process in a queue so that new requests are added to the queue and the first one reserved is the first one to get the book.
 */
public class Reservation {
    private Book book;
    private Queue<User> waitingList;
    
    /**
     * Description: This is the constructor of Reservation class.
     * A new waiting list is created when a new Reservation object is created.
     * @param book The book for which reservations are being managed.
     */
    public Reservation(Book book) {
        this.book = book;
        this.waitingList = new LinkedList<>();
    }
    
    /**
     * Adds a user to the waiting list for the book.
     * @param user The user (Student or Professor) to add.
     * @param library The library managing this reservation.
     */
    public void addToWaitingList(User user, Library library) {
        if (!library.getFaculty().equals(user.getFaculty())) {
            System.out.println("You cannot reserve this book. It does not belong to your faculty's libraries.");
            return;
        }
        waitingList.add(user);
        System.out.println(user.getName() + " has been added to the waiting list for the book: " + book.getTitle());
    }
    
    /**
     * Processes the next reservation in the queue if the book becomes available.
     * Notifies the next user in line.
     */
    public void processNextReservation() {
        if (!waitingList.isEmpty() && book instanceof PhysicalBook && ((PhysicalBook) book).CanBorrow()) {
            User nextUser = waitingList.poll();
            System.out.println("Notifying " + nextUser.getName() + " to borrow the book: " + book.getTitle());
        } else {
            System.out.println("No reservations or the book is not available for borrowing.");
        }
    }
    
    /**
     * Checks if a user is already in the waiting list for the book.
     * @param user The user to check.
     * @return true if the user is in the waiting list, false otherwise.
     */
    public boolean isUserInWaitingList(User user) {
        return waitingList.contains(user);
    }
    
    @Override
    public String toString() {
        return "Reservation for Book: " + book.getTitle() + "\nWaiting List: " + waitingList;
    }
}

