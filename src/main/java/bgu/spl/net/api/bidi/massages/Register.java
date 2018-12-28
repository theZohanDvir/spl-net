package bgu.spl.net.api.bidi.massages;

public class Register implements Massage
{
    private String userName;
    private String password;

    public Register ( String userName, String password )
    {
        this.password = password;
        this.userName = userName;
    }

    public String getUserName ()
    {
        return userName;
    }

    public String getPassword ()
    {
        return password;
    }
}
