package Models;

import CustomExceptions.ItemNotFoundException;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Member implements Serializable {


    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private List<Item> borrowedItems;

    public Member(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        borrowedItems = new ArrayList<>();
    }

    public Member(int id, String firstName, String lastName, LocalDate dateOfBirth){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        borrowedItems = new ArrayList<>();
    }

    //Getters
    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDateOfBirthAsString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dateOfBirth.format(formatter);
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    //Borrow methods
    public void borrowItem(Item item){
        borrowedItems.add(item);
    }

    public void returnBook(Item item) throws ItemNotFoundException {
        if (!borrowedItems.contains(item))
            throw new ItemNotFoundException("This member does not own this item.");
        borrowedItems.remove(item);
    }
}
