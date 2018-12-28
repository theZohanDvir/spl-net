package bgu.spl.net.api.bidi.massages;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Post implements Massage {
    String msg;
    private ConcurrentLinkedQueue<String> taggedUsers = new ConcurrentLinkedQueue<>();
    public Post(String msg,ConcurrentLinkedQueue taggedUsers){
        this.msg=msg;
        this.taggedUsers =taggedUsers;
    }
    public String getMsg(){
        return msg;
    }

    public ConcurrentLinkedQueue<String> getTaggedUsers() {
        return taggedUsers;
    }
}
