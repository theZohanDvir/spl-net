package bgu.spl.net.srv;

import bgu.spl.net.api.bidi.Connections;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionsImpl<T> implements Connections<T>
{
    private ConcurrentHashMap<Integer, ConnectionHandler<T>> id_ch = new ConcurrentHashMap<Integer, ConnectionHandler<T>>();

    public void addConnection ( int connectionId, ConnectionHandler<T> connectionHandler )
    {
        id_ch.put( connectionId, connectionHandler );
    }

    @Override
    public boolean send ( int connectionId, T msg )
    {
        try
        {
            id_ch.get( connectionId ).send( msg );
            return true;
        }catch ( IOException e )
        {
            return false;
        }
    }

    @Override
    public void broadcast ( T msg )
    {
        for ( ConcurrentHashMap.Entry<Integer, ConnectionHandler<T>> entry : id_ch.entrySet() )
        {
            try
            {
                entry.getValue().send( msg );
            } catch ( IOException e )
            {

            }
        }
    }

    @Override
    public void disconnect ( int connectionId )
    {
        id_ch.remove( connectionId );
    }
}
