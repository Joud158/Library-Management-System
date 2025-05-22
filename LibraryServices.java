import java.util.ArrayList;
/**
 * @author JoudS
 * Description: This is an interface that is implemented in both Student and Professor to organize the common services given to them.
 */
public interface LibraryServices {
	public ArrayList<BorrowingData> showHistory();
	public void borrowBook(Book book);
	public void searchBooks();
	public void returnBook(Book book);
	public void searchByCriteria(String criterion, String value);
	default public void printBookFound(Book book) {
        System.out.println("Book found!");
        System.out.println("Details: " + book);
    }
	public void borrowElectronicResource(ElectronicResources resource);
	public void returnElectronicResource(ElectronicResources resource);
	public void reserveRoom(MeetingRooms room);
	public void releaseRoom(MeetingRooms room);
}
