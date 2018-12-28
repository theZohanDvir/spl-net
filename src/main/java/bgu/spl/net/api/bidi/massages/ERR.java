package bgu.spl.net.api.bidi.massages;

public class ERR implements Massage
{
    private int reason;

    public ERR ( int reason )
    {
        this.reason = reason;
    }

    public int getReason ()
    {
        return reason;
    }
}
