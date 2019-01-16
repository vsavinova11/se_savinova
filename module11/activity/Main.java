import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        try {
            System.out.println("-----Enter the file path to edit");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String path = in.readLine();
            Notepad notepad = new Notepad(path);
            notepad.exec();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
