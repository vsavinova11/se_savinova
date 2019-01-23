package chatMessages;

import java.io.IOException;

public class TextMessage extends Message {
    private String message;
    private static final long serialVersionUID = 1l;
    public TextMessage(String userName, String message){
        super(userName);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /*private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException{
        message = (String) stream.readObject();
    }
    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException{
            stream.writeObject(message);
    }*/
}

