package chatMessages;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

public abstract class Message implements Serializable {
    private String date;
    private String userName;
    private static final long serialVersionUID = 1l;
    Message(String userName){
        date = new Date().toString();
        this.userName = userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
    public String getDate(){return date.toString();}
   /* private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException{
        date = (String) stream.readObject();
        userName = (String) stream.readObject();
    }
    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException{
        stream.writeObject(userName);
        stream.writeObject(userName);
    }*/
}
