import java.time.LocalDate;
/**
 * @author JoudS
 * Description: This class aims to show the borrowing data including the time a book was borrowed and the time it was returned.
 * Fields include: userName, resourceTitle, borrowDate, returnDate, and resourceType.
 */
public class BorrowingData {
	
	private String userName;
    private String resourceTitle;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private String resourceType;
    
    /**
     * Description: This is the constructor of BorrowingData Class
     * @param userName the one who is borrowing the book (student/professor)
     * @param bookTitle the title of the borrowed book
     * @param borrowDate when the book was borrowed
     * @param resourceType BOOK/OTHER
     */
    public BorrowingData(String userName, String bookTitle, LocalDate borrowDate, String resourceType) {
        this.userName = userName;
        this.resourceTitle = bookTitle;
        this.borrowDate = borrowDate;
        this.resourceType = resourceType;
    }
    
    /**
     * Description: This method assigns the date the book was returned at.
     * @param returnDate
     */
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    
    //getters and setters
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getResourceTitle() {
        return resourceTitle;
    }

    public void setResourceTitle(String resourceTitle) {
        this.resourceTitle = resourceTitle;
    }

	public LocalDate getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(LocalDate borrowDate) {
		this.borrowDate = borrowDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}
	
	public String getResourceType() {
        return resourceType;
    }

	@Override
    public String toString() {
        return "Borrowing Data {" +
                "userName='" + userName + '\'' +
                ", resourceTitle='" + resourceTitle + '\'' +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", resourceType=" + resourceType +
                '}';
    }
}
