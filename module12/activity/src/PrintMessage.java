import chatMessages.Message;
import chatMessages.TextMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class PrintMessage implements Runnable {
    Socket client;
    public PrintMessage(Socket socket) {
        this.client = socket;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        boolean endless = true;
        try {
            objectInputStream = new ObjectInputStream(client.getInputStream());
            while (endless){
                Message message = (Message) objectInputStream.readObject();
                if (message instanceof TextMessage) {
                    TextMessage text = (TextMessage)message;
                    Client.Print(text.getDate()+ "    " + text.getUserName() + ": " + text.getMessage());
                }
                if (Thread.interrupted()){
                    endless = false;
                }
            }
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }

    }
}
