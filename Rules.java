import java.time.LocalDate;

/**
 * @author JoudS
 * Description: An admin creates a new Rules object to define library rules for borrowing, renewing, and penalties. 
 */
public class Rules {
	private int studentBorrowDays; //A physical book can only be borrowed for 3 days by a student with no renewal option.
    private int professorBorrowDays; //Whereas it can be borrowed for one week by a professor with the option to renew only once.
    private boolean renewalAllowedProfessor;
    private boolean renewalAllowedStudent;
    private double latePenalty; // A late penalty can be applied to prevent a user from borrowing a book for a specific period of time if he/she returned a book late.
    

    public Rules(int studentBorrowDays, int professorBorrowDays, boolean renewalAllowedProfessor, boolean renewalAllowedStudent, double latePenalty) {
        this.studentBorrowDays = studentBorrowDays;
        this.professorBorrowDays = professorBorrowDays;
        this.renewalAllowedProfessor = renewalAllowedProfessor;
        this.renewalAllowedStudent = renewalAllowedStudent;
        this.latePenalty = latePenalty;
    }


	public int getStudentBorrowDays() {
		return studentBorrowDays;
	}


	public void setStudentBorrowDays(int studentBorrowDays) {
		this.studentBorrowDays = studentBorrowDays;
	}


	public int getProfessorBorrowDays() {
		return professorBorrowDays;
	}


	public void setProfessorBorrowDays(int professorBorrowDays) {
		this.professorBorrowDays = professorBorrowDays;
	}


	public boolean isRenewalAllowedProfessor() {
		return renewalAllowedProfessor;
	}


	public void setRenewalAllowedProfessor(boolean renewalAllowedProfessor) {
		this.renewalAllowedProfessor = renewalAllowedProfessor;
	}


	public boolean isRenewalAllowedStudent() {
		return renewalAllowedStudent;
	}


	public void setRenewalAllowedStudent(boolean renewalAllowedStudent) {
		this.renewalAllowedStudent = renewalAllowedStudent;
	}


	public double getLatePenalty() {
		return latePenalty;
	}


	public void setLatePenalty(double latePenalty) {
		this.latePenalty = latePenalty;
	}
	
	/**
     * Checks if a book borrowing duration is valid for a student based on the rules.
     * @param borrowDate The date the book was borrowed.
     * @param returnDate The date the book is returned.
     * @return true if the borrowing duration is within allowed rules, false otherwise.
     */
    public boolean isBorrowingValidForStudent(LocalDate borrowDate, LocalDate returnDate) {
        return !returnDate.isAfter(borrowDate.plusDays(studentBorrowDays));
    }

    /**
     * Calculates the penalty for a late return.
     * @param borrowDate The date the book was borrowed.
     * @param returnDate The date the book is returned.
     * @param maxBorrowDays The maximum number of days the book can be borrowed.
     * @return The calculated penalty.
     */
    public double calculateLatePenalty(LocalDate borrowDate, LocalDate returnDate, int maxBorrowDays) {
        if (returnDate.isAfter(borrowDate.plusDays(maxBorrowDays))) {
            long overdueDays = java.time.temporal.ChronoUnit.DAYS.between(borrowDate.plusDays(maxBorrowDays), returnDate);
            return overdueDays * latePenalty;
        }
        return 0.0;
    }
    
    /**
     * Checks if a book borrowing duration is valid for a professor, considering renewals.
     * @param borrowDate The date the book was borrowed.
     * @param returnDate The date the book is returned.
     * @param renewalsUsed The number of times the professor has already renewed the book.
     * @return true if the borrowing duration is within allowed rules, false otherwise.
     */
    public boolean isBorrowingValidForProfessor(LocalDate borrowDate, LocalDate returnDate, int renewalsUsed) {
        int maxBorrowDays = professorBorrowDays + (renewalAllowedProfessor ? professorBorrowDays * renewalsUsed : 0);
        return !returnDate.isAfter(borrowDate.plusDays(maxBorrowDays));
    }
    
    @Override
    public String toString() {
        return "Rules {" +
               "Student Borrow Days: " + studentBorrowDays +
               ", Professor Borrow Days: " + professorBorrowDays +
               ", Renewal Allowed (Professor): " + renewalAllowedProfessor +
               ", Renewal Allowed (Student): " + renewalAllowedStudent +
               ", Late Penalty: $" + latePenalty +
               '}';
    }
}

