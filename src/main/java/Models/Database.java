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

    private int nextItemId;
    private int nextMemberId;

    private final String itemFilePath = "src\\main\\data\\Items.txt";
    private final String memberFilePath = "src\\main\\data\\Members.txt";

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

    public Item getItemById(int id) throws ItemNotFoundException {
        for (Item item : items){

            if(id == item.getId())
                return item;
        }
        //Only reachable if the method does not return an item
        throw new ItemNotFoundException("No item with the given ID");
    }

    public Member getMemberById(int id) throws MemberNotFoundException {
        for (Member member : members){
            if (id == member.getId())
                return member;
        }
        //Only reachable if the method does not return a member
        throw new MemberNotFoundException("No member with the given ID");
    }

    //Private Setters for Next IDs, called after loading data
    private void setNextItemId() {
        int highestItemId = 0;

        for (Item item: items) {
            if (item.getId() > highestItemId)
                highestItemId = item.getId();
        }

        this.nextItemId = (highestItemId + 1);
    }

    private void setNextMemberId() {
        int highestMemberId = 0;

        for (Member member: members) {
            if (member.getId() > highestMemberId)
                highestMemberId = member.getId();
        }

        this.nextMemberId = (highestMemberId + 1);
    }

    //Adding / deleting data
    public void addItem(Item newItem){
        newItem.setId(getNextItemId());
        items.add(newItem);
        nextItemId++;
    }

    public void addMember(Member newMember){
        newMember.setId(getNextMemberId());
        members.add(newMember);
        nextMemberId++;
    }

    public void deleteItem(Item item){
        items.remove(item);
    }

    public void deleteMember(Member member){
        members.remove(member);
    }

    //Database methods
    public void loadData() throws DatabaseException {
        try{
            //Hard-coded users
            initialiseUsers();


            File itemFile = new File(itemFilePath);
            File memberFile = new File(memberFilePath);

            //Check if files exist and contain data
            if (itemFile.exists() && memberFile.exists() && itemFile.length() >= 0 && memberFile.length() >= 0){
                items = readFile(itemFilePath);
                members = readFile(memberFilePath);
            }
            else {
                initialiseItems();
                initialiseMembers();

                throw new DatabaseException();
            }
        }
        catch (DatabaseException | IOException exception){
            throw new DatabaseException();
        }
        finally{
            setNextItemId();
            setNextMemberId();
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



    public void serialiseEverything(){

        File itemFile = new File(itemFilePath);
        File memberFile = new File(memberFilePath);

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

    public <T>List<T> readFile(String fileName) throws DatabaseException, IOException {
        FileInputStream inputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            inputStream = new FileInputStream(fileName);
            objectInputStream = new ObjectInputStream(inputStream);

            return (List<T>) objectInputStream.readObject();
        }
        catch (Exception e) {
            throw new DatabaseException();
        }
        finally {
            inputStream.close();
            objectInputStream.close();
        }
    }
}

