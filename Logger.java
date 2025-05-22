import java.util.ArrayList;
/**
 * @author JoudS
 * Description: An instance of this class is created for each admin in which we add the borrowing logs.
 * It has a list of BorrowingData objects.
 */
public class Logger {
    private ArrayList<BorrowingData> logs;
    
    //Default constructor
    public Logger() {
        logs = new ArrayList<>();
    }
    
    //Add to the logger
    public void addLog(BorrowingData history) {
        logs.add(history);
    }
    //view logger
    public ArrayList<BorrowingData> getLogs() {
        return logs;
    }
}
