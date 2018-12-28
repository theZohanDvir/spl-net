package bgu.spl.net.api;

import bgu.spl.net.api.bidi.massages.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageEncoderDecoderImp implements MessageEncoderDecoder
{
    private String message = "";
    private int flag = 0;

    @Override
    public Object decodeNextByte ( byte nextByte )
    {
        message += (char) nextByte;
        if ( message.length() == 2 )
        {
            switch ( message )
            {
                case "01":
                    flag = 1;
                    break;
                case "02":
                    flag = 2;
                    break;
                case "03":
                    flag = 3;
                    break;
                case "04":
                    flag = 4;
                    break;
                case "05":
                    flag = 5;
                    break;
                case "06":
                    flag = 6;
                    break;
                case "07":
                    flag = 7;
                    break;
                case "08":
                    flag = 8;
                    break;
                case "09":
                    flag = 9;
                    break;
                case "10":
                    flag = 10;
                    break;
                case "11":
                    flag = 11;
                    break;
            }
        }
        else if ( flag != 0 )
        {
            switch ( flag )
            {
                case 1:
                    return REGISTER();
                case 2:
                    return LOGIN();
                case 3:
                    return LOGOUT();
                case 4:
                    return FOLLOW();
                case 5:
                    return POST();
                case 6:
                    return PM();
                case 7:
                    return USERLIST();
                case 8:
                    return STAT();
            }
        }
        return null;
    }

    @Override
    public byte[] encode ( Object message )
    {
        return new byte[0];
    }

    private Object REGISTER ()
    {
        if ( message.length() >= 5 )
        {
            String[] str = new String[2];
            int j = 0;
            for ( int i = 2 ; i < message.length() ; i++ )
            {
                if ( message.charAt( i ) == 0 )
                    j++;
                else
                    str[j] += message.charAt( i );
            }
            if ( j != 2 )
                return null;
            else
                return new Register( str[0], str[1] );
        }
        return null;
    }

    private Object LOGIN ()
    {
        if ( message.length() >= 5 )
        {
            String[] str = new String[2];
            int j = 0;
            for ( int i = 2 ; i < message.length() ; i++ )
            {
                if ( message.charAt( i ) == 0 )
                {
                    j++;
                    i++;
                }
                str[j] += message.charAt( i );
            }
            if ( j != 2 )
                return null;
            else
                return new Login( str[0], str[1] );
        }
        return null;
    }

    private Object LOGOUT ()
    {
        return new Logout();
    }

    private Object FOLLOW ()
    {
        if ( message.length() >= 6 )
        {
            ConcurrentLinkedQueue<String> stringConcurrentLinkedQueue = new ConcurrentLinkedQueue<>();
            Boolean followUnfollow;
            int num;
            if ( message.charAt( 2 ) == 0 )
                followUnfollow = false;
            else
                followUnfollow = true;
            num = (int) ( message.charAt( 3 ) ) * 10 + (int) ( message.charAt( 4 ) );
            String str = "";
            for ( int i = 6 ; i < message.length() ; i++ )
            {
                if ( message.charAt( i ) != ' ' )
                    str += message.charAt( i );
                else
                {
                    stringConcurrentLinkedQueue.add( str );
                    str = "";
                }
            }
            return new Follow( followUnfollow,num,stringConcurrentLinkedQueue );
        }
        return null;
    }

    private Object POST ()
    {
        String str = "";
        Boolean flag = false;
        if ( message.charAt( message.length() - 1 ) == '0' && message.length() >= 3 )
        {
            ConcurrentLinkedQueue<String> tagedUsers = new ConcurrentLinkedQueue<String>();
            for ( int i = 2 ; i < message.length() - 1 ; i++ )
            {
                if ( message.charAt( i ) == '@' )
                {
                    i++;
                    String tag = "";
                    for ( int j = i ; j < message.length() - 1 ; j++ )
                    {
                        if ( message.charAt( j ) != ' ' )
                            tag += message.charAt( j );
                        else
                        {
                            tagedUsers.add( tag );
                            i = j;
                        }
                    }
                }
                else
                    str += message.charAt( i );

            }
            return new Post( str, tagedUsers );
        }
        return null;

    }

    private Object PM ()
    {
        if ( message.length() >= 5 )
        {
            String[] str = new String[2];
            int j = 0;
            for ( int i = 2 ; i < message.length() ; i++ )
            {
                if ( message.charAt( i ) == 0 )
                    j++;
                else
                    str[j] += message.charAt( i );
            }
            if ( j != 2 )
                return null;
            else
                return new PM( str[0], str[1] );
        }
        return null;
    }

    private Object USERLIST ()
    {
        return null;
    }

    private Object STAT ()
    {
        if ( message.length() >= 4 && message.charAt( message.length() - 1 ) == '0' )
        {
            String str = "";
            for ( int i = 2 ; i < message.length() - 1 ; i++ )
            {
                str += message.charAt( i );
            }
            return new STAT( str );
        }
        return null;
    }


}
