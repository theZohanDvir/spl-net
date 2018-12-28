package bgu.spl.net.api.bidi;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BGSDtaBase {
    private ConcurrentHashMap<String,User> users=new ConcurrentHashMap<>();
    private ConcurrentHashMap<String,User> connectedUsers = new ConcurrentHashMap<>();
    public  BGSDtaBase(){

    }
    void register(User user){
        if(!users.contains(user)) {
            users.put(user.getUserName(),user);
        }
    }
    public User getUser(String userName){
        if(users.contains(userName))
            return users.get(userName);
        return null;
    }
    public void login(User user){
        connectedUsers.put(user.getUserName(),user);

    }
    public void logout(User user){
        connectedUsers.remove(user);

    }

    public ConcurrentHashMap<String, User> getUsers() {
        return users;
    }
}
