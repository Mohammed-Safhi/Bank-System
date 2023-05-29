    package Bank_System;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class AccountDate {
    private Calendar cal = Calendar.getInstance();
    private SimpleDateFormat d = new SimpleDateFormat();

    private String date = "";

    AccountDate() {
        date = d.format(cal.getTime());
    }

    public String getCurrentDate() {
        return d.format(cal.getTime());
    }

}

    
