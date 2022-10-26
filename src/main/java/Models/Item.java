package Models;

import java.io.Serializable;

public class Item implements Serializable {

    //Attributes
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;

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
    public boolean isAvailable() {
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
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
