import chatMessages.TextMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

class User{
    private String userName;
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    User(String userName, Socket socket, ObjectOutputStream objectOutputStream){
        this.userName = userName;
        this.socket = socket;
        this.objectOutputStream = objectOutputStream;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public String getUserName() {
        return userName;
    }

    public Socket getSocket() {
        return socket;
    }
}
public class Server {

    private  static  List<User> users = new LinkedList<User>();

    public static void addUser(String userName, Socket socket){
        try {
            User user = new User(userName, socket, new ObjectOutputStream(socket.getOutputStream()));
            users.add(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        broadcastMessage(new TextMessage(userName, "---присоединился к чату---"));
        System.out.println("Connection " + userName);
    }
    public static boolean hasUser(String userName){
        for (User user: users){
            if(user.getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }
    public static void deleteUser(String userName, Socket socket){
        for (User user: users){
            if(user.getUserName().equals(userName)){
                users.remove(user);
            }
        }
        broadcastMessage(new TextMessage(userName, "---покинул чат---"));
        System.out.println("Disconnection " + userName);

    }
    public static void broadcastMessage(TextMessage message){
        for (User user: users){
            try {
                ObjectOutputStream objectOutputStream = user.getObjectOutputStream();
                objectOutputStream.writeObject(message);
                System.out.println("Message from " + message.getUserName());
            } catch (IOException e) {
                deleteUser(user.getUserName(), user.getSocket());
                e.printStackTrace();
            }
        }
    }

    public static void main(String arg[]) {
        Server serverClass= new Server();
        ServerSocket server = null;
        Socket client = null;
        boolean endless = true;
        int port = 9999;
        Thread connectionProcessing = null;
        try {
            server = new ServerSocket(port, 50, InetAddress.getLocalHost());
            System.out.println("ServerSocket created at " + server.getInetAddress().getHostAddress());
            System.out.println("Waiting for connection");
            while (endless) {
                client = server.accept();
                connectionProcessing = new Thread(new ConnectionProcessing(client));
                connectionProcessing.start();
            }

        } catch (IOException e) {
            connectionProcessing.interrupt();
            e.printStackTrace();
        }
    }
}
