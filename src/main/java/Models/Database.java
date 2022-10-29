package Models;

import CustomExceptions.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Database {

    //Attributes
    private List<User> users = new ArrayList<>();
    private List<Item> items;
    private List<Member> members;

    //Constructor
    public Database(){

    }

    //Getters
    public List<Item> getItems() {
        return items;
    }
    public List<Member> getMembers() {
        return members;
    }
    public int getNextItemId() {
        int highestItemId = 0;

        for (Item item: items) {
            if (item.getId() > highestItemId)
                highestItemId = item.getId();
        }

        return (highestItemId+1);
    }
    public int getNextMemberId() {
        int highestMemberId = 0;

        for (Member member: members) {
            if (member.getId() > highestMemberId)
                highestMemberId = member.getId();
        }

        return (highestMemberId+1);
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

    public void loadData() throws DatabaseException {
        try{
            //Hard-coded users
            initialiseUsers();

            //Check for files for items and members
            File itemFile = new File("Items.txt");
            File memberFile = new File("Members.txt");

            if (itemFile.exists() && memberFile.exists()){
                items = readFile("Items.txt");
                members = readFile("Members.txt");

            }
            else {
                initialiseItems();
                initialiseMembers();

                throw new DatabaseException();
            }

        }
        catch (DatabaseException exception){
            throw new DatabaseException();
        }
    }
    private void initialiseUsers(){
        users = new ArrayList<>();
        users.add(new User("user1", "password1", "Wim"));
        users.add(new User("user2", "password2", "Mark"));
        users.add(new User("user3", "password3", "Stijn"));
        users.add(new User("user4", "password4", "Joshua"));
    }

    private void initialiseItems(){
        items = new ArrayList<>();
        items.add(new Item(1, "Of Mice and Men", "John Steinbeck"));
        items.add(new Item(2, "The Great Gatsby", "F. Scott Fitzgerald"));
        items.add(new Item(3, "Titans of War", "Wilbur Smith"));
        items.add(new Item(4, "The Final Empire", "Brandon Sanderson"));
    }

    private void initialiseMembers(){
        members = new ArrayList<>();
        members.add(new Member(1, "Michael", "Scott", LocalDate.of(1965, 3, 15)));
        members.add(new Member(2, "Pam", "Beesly", LocalDate.of(1979, 3, 25)));
        members.add(new Member(3, "Jim", "Halpert", LocalDate.of(1978, 10, 1)));
        members.add(new Member(4, "Dwight", "Schrute", LocalDate.of(1970, 1, 20)));
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
        newItem.setId(getNextItemId());
        items.add(newItem);
    }

    public void addMember(Member newMember){
        newMember.setId(getNextMemberId());
        members.add(newMember);
    }

    public void deleteItem(Item item){
     items.remove(item);
    }

    public void deleteMember(Member member){
        members.remove(member);
    }

    public void serialiseEverything(){

        File itemFile = new File("Items.txt");
        File memberFile = new File("Members.txt");

        try {
            serialise(itemFile, (List<Serializable>)(List<?>)this.items);
            serialise(memberFile, (List<Serializable>)(List<?>)this.members);
        }
        catch (Exception e){
            System.err.println("Changes were not saved to the database.");
        }
    }
    public void serialise(File file, List<Serializable> list) throws IOException {

        OutputStream fileStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileStream);

        objectOutputStream.writeObject(list);

        objectOutputStream.close();
        fileStream.close();
    }

    public <T>ArrayList<T> readFile(String fileName) throws DatabaseException {
        try {
            FileInputStream inputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            return (ArrayList<T>)objectInputStream.readObject();
        }
        catch (Exception e){
            throw new DatabaseException();
        }
    }
}

