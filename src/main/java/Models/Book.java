package Models;

import java.io.Serializable;

public class Book implements Serializable {

    private int id;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(int id, String title, String author){
        this.id = id;
        this.title = title;
        this.author = author;

        //Available by default
        isAvailable = true;
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }
}
