package Models;

import CustomExceptions.IncorrectPasswordException;
import CustomExceptions.ItemNotFoundException;
import CustomExceptions.MemberNotFoundException;
import CustomExceptions.UsernameNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Database {
    //Attributes
    private List<User> users = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private int nextItemId;
    private int nextMemberId;

    //Constructor
    public Database(){
        initialiseUsers();
        initialiseBooks();
        initialiseMembers();
    }

    //Getters
    public List<Item> getItems() {
        return items;
    }
    public List<Member> getMembers() {
        return members;
    }


    private void initialiseUsers(){

        users.add(new User("user1", "password1", "Wim"));
        users.add(new User("user2", "password2", "Mark"));
        users.add(new User("user3", "password3", "Stijn"));
        users.add(new User("user4", "password4", "Joshua"));
    }

    private void initialiseBooks(){

        items.add(new Item(1, "Of Mice and Men", "John Steinbeck"));
        items.add(new Item(2, "The Great Gatsby", "F. Scott Fitzgerald"));
        items.add(new Item(3, "Titans of War", "Wilbur Smith"));
        items.add(new Item(4, "The Final Empire", "Brandon Sanderson"));
        nextItemId = 5;
    }

    private void initialiseMembers(){
        try {
            members.add(new Member(1, "Michael", "Scott", LocalDate.of(1974, 10, 3)));
            members.add(new Member(2, "Pam", "Beesly", LocalDate.of(1990, 6, 15)));
            members.add(new Member(3, "Jim", "Halpert", LocalDate.of(1986, 11, 6)));
            members.add(new Member(4, "Dwight", "Schrute", LocalDate.of(1985, 3, 21)));
            nextMemberId = 5;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }


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
                throw new IncorrectPasswordException();
            }
        }
        //If username didn't match with a user in db
        throw new UsernameNotFoundException();
    }

    public void addItem(Item newItem){
        newItem.setId(nextItemId);
        items.add(newItem);

        nextItemId++;
    }

    public void addMember(Member newMember){
        newMember.setId(nextMemberId);
        members.add(newMember);

        nextMemberId++;
    }

    public Item getItemById(int id) throws Exception {
        for (Item item : items){

            if(id == item.getId())
                return item;
        }
        throw new ItemNotFoundException("No item with the given ID");
    }

    public Member getMemberById(int id) throws Exception {
        for (Member member : members){
             if (id == member.getId())
                return member;
        }
        throw new MemberNotFoundException("No member with the given ID");
    }

}

