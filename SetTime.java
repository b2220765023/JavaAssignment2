package Assignment2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**

 The SetTime class represents a date and time, which can be set using a formatted string input.
 */
public class SetTime {
    private LocalDateTime dateTime;

    /**

     Constructs a SetTime object with the specified formatted input.
     The input must be in the "yyyy-M-d_H:m:s" format.
     @param input a string representation of the date and time to be set.
     */
    public SetTime(String input) {
        DateTimeFormatter df = new DateTimeFormatterBuilder().appendPattern("yyyy-M-d_H:m:s").toFormatter();
        setDateTime(LocalDateTime.parse(input, df));
    }

    /**

     Returns the date and time stored in this SetTime object.

     The date and time are formatted as a string in the "yyyy-MM-dd_HH:mm:ss" format.

     @return the date and time stored in this SetTime object.
     */
    public LocalDateTime getDateTime() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");

        //dateTime.format(df);
        return dateTime;
    }

    /**

     Sets the date and time of this SetTime object to the specified LocalDateTime.
     @param dateTime the LocalDateTime to set as the date and time of this SetTime object.
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
