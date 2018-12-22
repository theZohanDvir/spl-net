package bgu.spl.net.srv;

import bgu.spl.net.api.bidi.Connections;

import java.util.concurrent.ConcurrentHashMap;

public class ConnectionsImpl<T> implements Connections<T>
{
    private ConcurrentHashMap<Integer, ConnectionHandler<T>> id_Msg = new ConcurrentHashMap<Integer, ConnectionHandler<T>>();

    public void addConnection ( int connectionId, ConnectionHandler<T> connectionHandler )
    {
        id_Msg.put( connectionId, connectionHandler );
    }

    @Override
    public boolean send ( int connectionId, T msg )
    {
        return false;
    }

    @Override
    public void broadcast ( T msg )
    {
        for ( ConcurrentHashMap.Entry<Integer, ConnectionHandler<T>> entry : id_Msg.entrySet() )
        {
            entry.getValue().send( msg );
        }
    }

    @Override
    public void disconnect ( int connectionId )
    {
        id_Msg.remove( connectionId );
    }
}
