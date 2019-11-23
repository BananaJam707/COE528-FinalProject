package systemPack;

import scenePack.AlertBox;
import scenePack.ConfirmBox;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;



public class Manager {

    public static boolean isValidUser(String username){

        File file = new File("C:\\Users\\ih9wi\\Desktop\\FinalProject\\projectData\\"+username+".txt");

        if(file.exists()){
            return false;
        }
        return true;
    }

    public static void createUser(String username, String password, String firstName,
                                  String lastName)
            throws FileNotFoundException, IOException{

        if(isValidUser(username)){
            File file = new File("C:\\Users\\ih9wi\\Desktop\\FinalProject\\projectData\\"+username+".txt");
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file, true));
            fileWriter.write(firstName);
            fileWriter.newLine();

            fileWriter.write(lastName);
            fileWriter.newLine();

            fileWriter.write(Double.toString(100.00));
            fileWriter.newLine();

            fileWriter.close();

            File passFile = new File("C:\\Users\\ih9wi\\Desktop\\FinalProject\\projectData\\"+"UserPasswords.txt");
            BufferedWriter passWriter = new BufferedWriter(
                    new FileWriter(passFile, true));
            PrintWriter out = new PrintWriter(passWriter);

            out.println(username);
            out.println(password);
            out.close();

        }
        else{
            AlertBox.display("Error", "Username Already Exists");}
    }
    public static void deleteUser(String username) throws IOException{
        if(!isValidUser(username)){
            if(ConfirmBox.display("Delete!?", "Are you sure you want to DELETE THIS ACCOUNT?")){
                File file = new File("C:\\Users\\ih9wi\\Desktop\\FinalProject\\projectData\\"+username+".txt");
                file.delete();

                File passFile = new File("C:\\Users\\ih9wi\\Desktop\\FinalProject\\projectData\\"+"UserPasswords.txt");

                ArrayList<String> userInfo = new ArrayList();

                try (BufferedReader br = new BufferedReader(new FileReader(passFile))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        userInfo.add(line);
                    }
                }
                catch (IOException ex) {
                    AlertBox.display("Error", "File Error");
                }

                for(int i = 0; 2*i < userInfo.size(); i++){
                    if(userInfo.get(2*i).equals(username)){
                        userInfo.remove(2*i);
                        userInfo.remove(2*i);
                        break;
                    }
                }

                BufferedWriter passWriter = new BufferedWriter(
                        new FileWriter(passFile));
                PrintWriter out = new PrintWriter(passWriter);

                for(int i = 0; i < userInfo.size(); i++){
                    out.println(userInfo.get(i));
                }
                out.close();
            }
        }
        else{
            AlertBox.display("Error", "Username Doesn't Exist");
        }
    }
}