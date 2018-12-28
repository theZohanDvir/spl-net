package bgu.spl.net.api.bidi.massages;

public class ACK implements Massage
{
    private int msg;
    private String ack;

    public ACK ( int msg )
    {
        this.msg = msg;
    }

    public ACK ( int msg, String ack )
    {
        this.msg = msg;
        this.ack = ack;
    }

    public int getMsg ()
    {
        return msg;
    }

    public void setAck ( String ack )
    {
        this.ack = ack;
    }
}
