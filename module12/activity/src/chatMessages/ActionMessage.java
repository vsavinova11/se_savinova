package chatMessages;


import java.io.IOException;

public class ActionMessage extends Message {

    private Action action;
    private static final long serialVersionUID = 1l;

    public ActionMessage(String userName, Action action) {
        super(userName);
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
 /*   private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException{
        action = (Action) stream.readObject();
    }
    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException{
        stream.writeObject(action);
    }*/
}

