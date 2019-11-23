package systemPack;

import scenePack.AlertBox;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class Authentication {

    public static boolean authenticateManager(String username, String password){

        return(username.equals("admin") &&  password.equals("admin") );
    }

    public static boolean authenticateUser(String username, String password){

        File file = new File("C:\\Users\\ih9wi\\Desktop\\FinalProject\\projectData\\"
                + "UserPasswords.txt");
        ArrayList<String> userInfo = new ArrayList();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                userInfo.add(line);
            }
        } catch (IOException ex) {
            AlertBox.display("Error", "File Error");
            return false;
        }

        for(int i = 0; 2*i < userInfo.size();i++){
            if(userInfo.get(2*i).equals(username) &&
                    userInfo.get(2*i + 1).equals(password)){
                return true;
            }
        }
        return false;
    }
}
