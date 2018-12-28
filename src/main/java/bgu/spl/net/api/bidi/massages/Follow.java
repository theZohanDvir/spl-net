package bgu.spl.net.api.bidi.massages;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Follow implements Massage {
    private boolean followUnfollow;
    private int numOfUsers;
    private ConcurrentLinkedQueue<String> userNames;
    public Follow(boolean followUnfollow,int numOfUsers,ConcurrentLinkedQueue userNames){
        this.followUnfollow=followUnfollow;
        this .numOfUsers=numOfUsers;
        this.userNames = userNames;
    }

    public boolean isFollowUnfollow() {
        return followUnfollow;
    }

    public int getNumOfUsers() {
        return numOfUsers;
    }

    public ConcurrentLinkedQueue<String> getUserNames() {
        return userNames;
    }
}
