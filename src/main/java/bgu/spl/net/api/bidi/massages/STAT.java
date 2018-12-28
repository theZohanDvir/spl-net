package bgu.spl.net.api.bidi.massages;

public class STAT implements Massage {
    private String userName;
    public STAT(String userName){
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
