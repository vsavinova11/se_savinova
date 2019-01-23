import chatMessages.Action;
import chatMessages.ActionMessage;
import chatMessages.Message;
import chatMessages.TextMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ConnectionProcessing implements Runnable{
    Socket client;
    ConnectionProcessing(Socket client){
        this.client = client;
    }
    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        String userName = null;
        boolean endless = true;
        try {

            objectInputStream = new ObjectInputStream(client.getInputStream());
            while (endless){
                Message message = (Message) objectInputStream.readObject();
                if (message instanceof ActionMessage){
                    chatMessages.Action action = ((ActionMessage) message).getAction();
                    if (action.equals(Action.USER_JOIN)){
                        if (!Server.hasUser(message.getUserName())){
                            Server.addUser(message.getUserName(), client);
                            userName = message.getUserName();
                        }
                    }
                    if (action.equals(Action.USER_LEAVE)){
                        Server.deleteUser(message.getUserName(), client);
                        endless = false;
                    }
                }
                if (message instanceof TextMessage) {
                    Server.broadcastMessage((TextMessage) message);
                }

            }
        } catch (IOException e) {
            Server.deleteUser(userName, client);
        } catch (ClassNotFoundException e) {
            Server.deleteUser(userName, client);
        }
    }
}
