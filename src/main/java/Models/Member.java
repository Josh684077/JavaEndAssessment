package Models;

import java.util.Date;

public class Member {
    private int id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    public Member(int id, String firstName, String lastName, Date dateOfBirth){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
}
