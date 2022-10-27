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
    public int getNextItemId() {
        return nextItemId;
    }
    public int getNextMemberId() {
        return nextMemberId;
    }

    public Item getItemById(int id) throws Exception {
        for (Item item : items){

            if(id == item.getId())
                return item;
        }
        //Only reachable if the method does not return
        throw new ItemNotFoundException("No item with the given ID");
    }

    public Member getMemberById(int id) throws Exception {
        for (Member member : members){
            if (id == member.getId())
                return member;
        }
        //Only reachable if the method does not return
        throw new MemberNotFoundException("No member with the given ID");
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
            members.add(new Member(1, "Michael", "Scott", LocalDate.of(1965, 3, 15)));
            members.add(new Member(2, "Pam", "Beesly", LocalDate.of(1979, 3, 25)));
            members.add(new Member(3, "Jim", "Halpert", LocalDate.of(1978, 10, 1)));
            members.add(new Member(4, "Dwight", "Schrute", LocalDate.of(1970, 1, 20)));
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




    public void updateItem(Item updatedItem){

        for (Item item: items){
            //Look for matching unique IDs
            if (item.getId() == updatedItem.getId()){
                //Overwrite all other attributes
                item.setTitle(updatedItem.getTitle());
                item.setAuthor(updatedItem.getAuthor());
                item.setIsAvailable(updatedItem.getIsAvailable());
            }
        }
    }

    public void updateMember(Member updatedMember){

        for (Member member: members){
            //Look for matching unique IDs
            if (member.getId() == updatedMember.getId()){
                //Overwrite all other attributes
                member.setFirstName(updatedMember.getFirstName());
                member.setLastName(updatedMember.getLastName());
                member.setDateOfBirth(updatedMember.getDateOfBirth());
            }
        }
    }

    public void serialiseEverything(){

    }
    public void deserialiseEverything(){

    }
}

