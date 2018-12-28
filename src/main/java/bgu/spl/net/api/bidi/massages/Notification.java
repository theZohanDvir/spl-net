package bgu.spl.net.api.bidi.massages;

import bgu.spl.net.api.bidi.User;

public class Notification implements Massage {
    private String fromUser;
    private String msg;
    private PMorPublic pop;
    public enum PMorPublic{
        PM,PUBLIC;
    }
    public Notification(String user,String msg,PMorPublic pop) {
        this.fromUser = user;
        this.msg = msg;
        this.pop = pop;

    }

}
