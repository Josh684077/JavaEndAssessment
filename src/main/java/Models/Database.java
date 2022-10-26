package Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Database {
    private List<User> users = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();

    private int bookId;
    private int memberId;

    public Database(){
        initialiseUsers();
        initialiseBooks();
        initialiseMembers();
    }

    private void initialiseUsers(){

        users.add(new User("user1", "password1", "Wim"));
        users.add(new User("user2", "password2", "Mark"));
        users.add(new User("user3", "password3", "Stijn"));
        users.add(new User("user4", "password4", "Joshua"));
    }

    private void initialiseBooks(){

        books.add(new Book(1, "Of Mice and Men", "John Steinbeck"));
        books.add(new Book(2, "The Great Gatsby", "F. Scott Fitzgerald"));
        books.add(new Book(3, "Titans of War", "Wilbur Smith"));
        books.add(new Book(4, "The Final Empire", "Brandon Sanderson"));

        bookId = 5;
    }

    private void initialiseMembers(){
        try {
            members.add(new Member(1, "Michael", "Scott", new SimpleDateFormat("dd-MM-yyyy").parse("03-10-1974")));
            members.add(new Member(2, "Pam", "Beesly", new SimpleDateFormat("dd-MM-yyyy").parse("03-06-1990")));
            members.add(new Member(3, "Jim", "Halpert", new SimpleDateFormat("dd-MM-yyyy").parse("03-11-1986")));
            members.add(new Member(4, "Dwight", "Schrute", new SimpleDateFormat("dd-MM-yyyy").parse("03-03-1985")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        memberId = 5;
    }

    public User loginUser(String username, String password) throws Exception {
        for (User user: users)
        {
            if (username.equalsIgnoreCase(user.getUsername()))
            {
                if (password.equals(user.getPassword()))
                {
                    //If both username & password match a user in db
                    return user;
                }
                //If username matched a user in db but password did not
                throw new Exception("Incorrect password.");
            }
        }
        //If username didn't match with a user in db
        throw new Exception("Username is not registered in our database.");
    }

    public List<Member> getAllMembers(){
        return members;
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public void addMember(String firstName, String lastName, Date dateOfBirth){
        int newMemberId = (memberId+1);
        members.add(new Member(newMemberId, firstName, lastName, dateOfBirth));

        memberId++;
    }

    public void addBook(String title, String author){
        int newBookId = (bookId+1);
        books.add(new Book(newBookId, title, author));

        bookId++;
    }

    public Book getBookById(int id){
        for (Book book: books)
        {
            if(id == book.getId())
                return book;
        }
        throw new NullPointerException("No book with the given ID");
    }

    public Member getMemberById(int id){
        return members.get(1);
    }
}

