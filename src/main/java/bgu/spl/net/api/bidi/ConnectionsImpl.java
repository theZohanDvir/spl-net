package bgu.spl.net.api.bidi;

import bgu.spl.net.api.bidi.massages.Massage;
import bgu.spl.net.srv.BlockingConnectionHandler;
import bgu.spl.net.srv.NonBlockingConnectionHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionsImpl<T> implements Connections<T> {
    ConcurrentHashMap<Integer, NonBlockingConnectionHandler> handlerConcurrentHashMap;
    int id = 0;

    public int addHandler(NonBlockingConnectionHandler hndl) {
        while (handlerConcurrentHashMap.containsKey(id)) {
            id++;
        }
        handlerConcurrentHashMap.put(id, hndl);
        return id;
    }
    public NonBlockingConnectionHandler getHandler(int id){
        return handlerConcurrentHashMap.get(id);
    }
    @Override
    public boolean send(int connectionId, T msg) {
        try {
            handlerConcurrentHashMap.get(connectionId).send(msg);
        } catch (IOException e) {

            return false;
        }
        return true;
    }

    @Override
    public void broadcast(T msg) {

    }

    @Override
    public void disconnect(int connectionId) {

    }
}
