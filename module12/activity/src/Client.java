import chatMessages.Action;
import chatMessages.ActionMessage;
import chatMessages.Message;
import chatMessages.TextMessage;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    ObjectOutputStream objectOutputStream;
    Socket client;
    Client(Socket client){
        this.client = client;
        try {
            objectOutputStream = new ObjectOutputStream(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public static void Print(String message){
        System.out.println(message);
    }
    public static void main(String arg[]) {
        try {
            System.out.println("Введите имя пользователя...");
            Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
            Client client = new Client(socket);
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String userName = null;
            userName = in.readLine();
            Thread readThread = new Thread(new PrintMessage(socket));
            readThread.start();
            ObjectOutputStream objectOutputStream = client.getObjectOutputStream();
            if (!userName.equals(null)) {
                Message message = new ActionMessage(userName, Action.USER_JOIN);
                objectOutputStream.writeObject(message);
                String line = "/n";
                while (line != null) {
                    line = in.readLine();
                    if (line.equals("LEAVE")){
                        objectOutputStream.writeObject(new ActionMessage(userName, Action.USER_LEAVE));
                        readThread.interrupt();
                        in.close();
                        objectOutputStream.close();
                        socket.close();
                        return;
                    }
                    Message textMessage = new TextMessage(userName, line);
                    objectOutputStream.writeObject(textMessage);
                }

            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
    }
}
