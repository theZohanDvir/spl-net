package bgu.spl.net.srv;

import bgu.spl.net.api.bidi.Connections;

public class ConnectionsImpl<T> implements Connections<T>
{
    private int connectionId;
    private T msg;

    @Override
    public boolean send ( int connectionId, T msg )
    {
        
        return false;
    }

    @Override
    public void broadcast ( T msg )
    {
        this.msg = msg;
    }

    @Override
    public void disconnect ( int connectionId )
    {
        this.connectionId = connectionId;
    }
}
