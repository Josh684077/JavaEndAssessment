package Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Item implements Serializable {

    //Attributes
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;
    private LocalDate lendDate;

    //private Member currentBorrower; (not implemented yet)

    //Constructors
    public Item(String title, String author) {
        this.title = title;
        this.author = author;

        //Default values
        this.id = 0;
        this.isAvailable = true;
    }

    public Item(int id, String title, String author){
        this.id = id;
        this.title = title;
        this.author = author;

        //Available by default
        isAvailable = true;
    }

    //Getters
    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public boolean getIsAvailable() {
        return isAvailable;
    }
    public String getAvailableAsString(){
        if (isAvailable)
            return "Yes";
        else
            return "No";
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setIsAvailable(boolean available) {

        //Add lend date if item is marked unavailable
        if (available == false)
            lendDate = LocalDate.now();
        else
            lendDate = null;

        isAvailable = available;
    }

    public int getLateDays(){
        int lendDays = (int)ChronoUnit.DAYS.between(lendDate, LocalDate.now());
        int lateDays = lendDays - 21;
        return lateDays;
    }

}
