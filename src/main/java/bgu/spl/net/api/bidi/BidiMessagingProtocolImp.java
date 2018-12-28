package bgu.spl.net.api.bidi;


import bgu.spl.net.api.bidi.massages.*;

public class BidiMessagingProtocolImp implements BidiMessagingProtocol<Massage> {
    Connections connections;
    int connectionId;
    BGSDtaBase DB;
    User user;
    boolean souldTerminate = false;

    @Override
    public void start(int connectionId, Connections connections) {
        this.connectionId = connectionId;
        this.connections = connections;
    }

    @Override
    public void process(Massage message) {

    }

    public void process(Register message) {
        DB.register(new User(connectionId, message.getUserName(), message.getPassword()));
        connections.send(connectionId, new ACK(1));
    }

    public void process(Login message) {
        User u = DB.getUser(message.getUserName());
        if (u != null) {
            if (u.getPassword().equals(message.getPassword())) {
                u.connect();
                DB.login(u);
                this.user = u;
                connections.send(connectionId, new ACK(2));
                for (Massage msg : user.getWaitingMassages()
                ) {
                    connections.send(connectionId, msg);

                }
            }
            connections.send(connectionId, new ERR(2));

        }

        connections.send(connectionId, new ERR(2));
    }

    public void process(Logout message) {
        if (user.isConnected()) {
            user.disconnect();
            DB.logout(user);
            connections.send(connectionId, new ACK(3));
            souldTerminate = true;
        } else
            connections.send(connectionId, new ERR(3));


    }

    public void process(Follow message) {
        if(user.isConnected()) {
            String s = "";
            for (String name : message.getUserNames()) {
                User u = DB.getUser(name);
                if (u != null) {
                    u.addFollower(user);
                    user.follow();
                }
                s += name + " ";
            }
            if (s == "")
                connections.send(connectionId, new ERR(4));
            else {

                connections.send(connectionId, new ACK(4, s));
            }
        }else {
            connections.send(connectionId, new ERR(4));
        }

    }

    public void process(Post message) {
        if (user.isConnected()) {
            boolean sucsses = false;
            for (String s : message.getTaggedUsers()
            ) {
                connections.send(DB.getUser(s).getCurrentConnectionId(), new Notification(user.getUserName(), message.getMsg(), Notification.PMorPublic.PUBLIC));
                sucsses = true;

            }
            for (User u : user.getFollowers()
            ) {
                if (!message.getTaggedUsers().contains(u.getUserName())) {
                    connections.send(u.getCurrentConnectionId(), new Notification(user.getUserName(), message.getMsg(), Notification.PMorPublic.PUBLIC));
                    sucsses = true;
                }
            }
            if (sucsses) {
                connections.send(connectionId, new ERR(5));
                user.Post();

            }
        } else
            connections.send(connectionId, new ERR(5));
    }


    public void process(PM message) {
        if (user.isConnected()) {
            User u = DB.getUser(message.getToUserName());
            if (u != null) {
                if (u.isConnected()) {
                    connections.send(u.getCurrentConnectionId(), new Notification(user.getUserName(), message.getMsg(), Notification.PMorPublic.PM));
                } else
                    u.addWaitingMassage(new Notification(user.getUserName(), message.getMsg(), Notification.PMorPublic.PM));
            } else
                connections.send(connectionId, new ERR(6));

        }

    }

    public void process(UserStat message) {
        if (user.isConnected()) {
            ACK a = new ACK(7);
            int numOfUsers = DB.getUsers().size();
            String s = "7 " + numOfUsers + " ";
            for (String name : DB.getUsers().keySet()
            ) {
                s += name + " ";

            }
            a.setAck(s);
            connections.send(connectionId, a);
        } else {
            connections.send(connectionId, new ERR(7));
        }

    }

    public void process(STAT message) {
        if (user.isConnected()) {
            User u = DB.getUser(message.getUserName());
            String str = "8 " + u.getNumPost() + " " + u.getFollowers().size() + " " + u.getFollow();
            connections.send(connectionId, new ACK(8, str));
        }
    }

    @Override
    public boolean shouldTerminate() {
        return souldTerminate;
    }
}
