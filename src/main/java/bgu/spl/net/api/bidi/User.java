package bgu.spl.net.api.bidi;

import bgu.spl.net.api.bidi.massages.Massage;

import java.util.concurrent.ConcurrentLinkedQueue;

public class User {
    private boolean isConnected=false;
    private int currentConnectionId;
    private String userName;
    private String password;
    private ConcurrentLinkedQueue<User> followers;
    private ConcurrentLinkedQueue<Massage> waitingMassages;
    private int numPost=0;
    private int follow=0;
    public User(int currentConnectionId, String userName, String password) {
        this.currentConnectionId = currentConnectionId;
        this.password = password;
        this.userName = userName;
        this.followers = new ConcurrentLinkedQueue<>();

        waitingMassages= new ConcurrentLinkedQueue<>();
    }

    public int getCurrentConnectionId() {
        return currentConnectionId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean addFollower(User user) {
        if (!followers.contains(user))
            return followers.add(user);
        else
            return false;
    }
    void connect(){
        isConnected=true;
    }
    void disconnect(){
        isConnected=false;
    }
    boolean isConnected(){
        return isConnected;
    }
    void addWaitingMassage(Massage msg){
        waitingMassages.add(msg);
    }

    public ConcurrentLinkedQueue<Massage> getWaitingMassages() {
        return waitingMassages;
    }

    public ConcurrentLinkedQueue<User> getFollowers() {
        return followers;
    }
    public void Post(){
        numPost++;
    }
    public void follow(){
        follow++;
    }

    public int getNumPost() {
        return numPost;
    }

    public int getFollow() {
        return follow;
    }
}
