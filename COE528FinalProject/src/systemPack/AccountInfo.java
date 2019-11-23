package systemPack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * The responsibility of this class is to provide the account information in a
 * set of variables, to be manipulated by banking functions in other classes.
 *
 * This class is a mutable class, as most variables with the exception the user export
 * file, can be changed.
 *
 * AF(c) = an abstract bank account
 *  where String userFirstName and String userLastName are the user's
 *  respective names
 *  double accountBalance is the user's current balance
 *  AccountState accountState is the user's current membership state with the
 *  bank
 *  File userFile is the location of the user's text file where the information
 *  is stored
 *
 * RI(c) = true if
 *          userFirstName != null
 *          userLastName != null
 *          userBalance > 0
 *          accountState != null
 *          userFile.exists() == true
 *
 *        = false
 *          otherwise
 *
 * @author Maheer Kazi
 */

public class AccountInfo {

    private String userFirstName;
    private String userLastName;
    private double accountBalance;
    private AccountState accountState;
    private File userFile;

    public AccountInfo(String username){

        /**
         * Requires: username of type String that corresponds to the text
         * file of the user's information
         *
         * Effects: Initialize the variables of the user's account by reading
         * the information from the text file
         */

        userFile = new File("C:\\Users\\ih9wi\\Desktop\\FinalProject\\projectData\\"+username+".txt");
        ArrayList<String> userInfo = new ArrayList();
        int n = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                userInfo.add(line);
            }
        } catch (IOException ex) {
            System.out.println("File Not Found");
        }

        userFirstName = userInfo.get(n++);
        userLastName = userInfo.get(n++);
        accountBalance = Double.parseDouble(userInfo.get(n++));

        //initialize account state
        setAccountState(new SilverState());
        accountState.checkState(this);

    }

    public void addAccountBalance(double value){
        /**
         * Requires: any valid double value.
         *
         * Modifies: modifies the accountBalance by adding the value to the
         * accountBalance. Can use negative values.
         */
        accountBalance += value;
    }

    public AccountState getAccountState() {
        /**
         * Effects: changes the accountState variable, then returns the
         * new state
         */
        checkAccountState();
        return accountState;
    }

    public void setAccountState(AccountState accountState) {
        /**
         * Requires: an accountState of type accountState.
         *
         * Modifies: modifies the accountState to the new accountState
         */
        this.accountState = accountState;
    }

    public void checkAccountState(){
        /**
         * Effects: checks the state of the account and
         */
        accountState.checkState(this);
    }

    public String getUserFirstName() {
        /**
         * Effects: returns the user's userFirstName
         */
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        /**
         * Requires: userFirstName != null
         *
         * Modifies: sets user's userFirstName to the new value
         */

        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        /**
         * Effects: returns the user's userLastName
         */

        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        /**
         * Requires: userLastName != null
         *
         * Modifies: sets user's userLastName to the new value
         */

        this.userLastName = userLastName;
    }

    public double getAccountBalance() {
        /**
         * Effects: returns the user's accountBalance
         */
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        /**
         * Requires: accountBalance > 0
         *
         * Modifies: sets the user's accountBalance to the new value.
         */

        if(accountBalance > 0){
            this.accountBalance = accountBalance;

        }
    }

    @Override
    public String toString(){

        /**
         * Effects: Returns account info, if account info is valid via the repOk
         * method.
         */

        if(repOk()){
            return ("Account Info: " +userFirstName+" "+userLastName+" - "+ "Balance: "+
                    accountBalance +" " + accountState + " Membership.");
        }
        return ("Account doesn't exist.");
    }

    public boolean repOk(){
        /**
         * Requires: accountBalance > 0, a negative balance cannot exist.
         * userFirstName && userLastName be of type String.
         */

        if(accountBalance > 0 && userLastName != null && userLastName instanceof
                String && userFirstName != null && userFirstName instanceof String){
            return true;
        }
        return false;
    }


    public void saveInfo() throws IOException{

        /**
         * Modifies: Modifies the user's text file with the updated data
         *
         * Effects: Writes the new information to the text file. Writes the
         * user's first and last name, along with the account balance.
         */

        BufferedWriter writer = new BufferedWriter(new FileWriter(userFile));

        writer.write(userFirstName);
        writer.newLine();

        writer.write(userLastName);
        writer.newLine();

        writer.write(Double.toString(accountBalance));
        writer.newLine();

        writer.close();
    }
}