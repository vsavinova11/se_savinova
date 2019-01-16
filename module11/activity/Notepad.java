package com.company;


import java.io.*;
import java.util.LinkedList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author valentina.savinova
 */
public class Notepad {
    File editFile;
    List<String> textLines;

    public Notepad(String path){
        editFile = new File(path);
        textLines = new LinkedList<String>();
    }

    public void exec(){
        openFile();
        System.out.println("-----Enter the command \n------WRITE \n------REPLACE \n------DELETE \n------SAVE \n------CLOSE");
        String command = "\n";
        while (!command.equals("CLOSE")){
            command = readLine();
            if (command.equals("WRITE"))
                write();
            if (command.equals("REPLACE"))
                replace();
            if (command.equals("SAVE"))
                save();
            if (command.equals("DELETE"))
                replace();
            System.out.println("-----Enter the command \n------WRITE \n------REPLACE \n------DELETE \n------SAVE \n------CLOSE");
        }
        System.out.println("-----Goodbye");
    }

    private void openFile() {
        try {
            if (!editFile.exists()) {
                System.out.println("-----Creating " + editFile.getCanonicalPath());
                editFile.createNewFile();
            }
            System.out.println("-----Reading contents of " + editFile.getCanonicalPath());
            BufferedReader in = new BufferedReader(new FileReader(editFile));
            int count = 0;
            String line = "\n";
            while (line != null) {
                line = in.readLine();
                System.out.println(Integer.toString(count + 1) + " " + line);
                textLines.add(count, line);
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }
    private void write(){
        System.out.println("-----Enter a string or command 'END' to end writing");
        int count = textLines.size();
        String line = "";
        while(!line.equals("END")){
            line = readLine();
            if (line.equals("END")) {
                System.out.println("-----The end of writing");
            } else {
                System.out.println(Integer.toString(count+1) + " " + line);
                textLines.add(count, line);
                count++;
            }

        }
    }
    private void replace(){
        System.out.println("-----Enter the line number for editing");
        String line = readLine();
        int num = Integer.parseInt(line);
        System.out.println("-----Enter new value for #" + num);
        line = readLine();
        num -=1;
        textLines.remove(num);
        textLines.add(num, line);
    }
    private void save(){
        try {
            File newFile = new File(editFile.getCanonicalPath());
            FileWriter fw = new FileWriter(newFile.getAbsolutePath());

            Writer output = new BufferedWriter(fw);

            for (String line: textLines) {
                output.write(line + '\n');
            }
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("------Your file was saved");
    }
    private void delete(){
        System.out.println("-----Enter the line number for deleting");
        textLines.remove(Integer.parseInt(readLine())-1);
        System.out.println("-----String deleted");
    }
    private String readLine(){
        String line = "";
        BufferedReader inputStream= new BufferedReader(new InputStreamReader(System.in));
        try {
            line = inputStream.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}
