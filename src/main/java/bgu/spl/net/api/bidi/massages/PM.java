package bgu.spl.net.api.bidi.massages;

import bgu.spl.net.api.bidi.User;

public class PM implements Massage {
    private String toUserName;
    private String msg;
    public PM(String toUserName,String msg){
        this.toUserName=toUserName;
        this.msg=msg;

    }



    public String getToUserName() {
        return toUserName;
    }

    public String getMsg() {
        return msg;
    }
}
