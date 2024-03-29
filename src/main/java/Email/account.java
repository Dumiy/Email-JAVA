
package Email;
import Email.database.database;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class account implements Serializable,accountFunc {

    private String username;
    private String password;
    private String email;
    private String creationDate;
    private String FirstName;
    private String LastName;
    private int unique;
    private inbox Inbox;
    public account() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        System.out.println("Creating an account : \n");
        Scanner key = new Scanner(System.in);
        System.out.println("Enter username");
        this.username = key.next();
        System.out.println("Enter a password");
        this.password = key.next();
        Date date = new Date();
        this.creationDate = format.format(date);

    }
    public account(String nume,String prenume,String Email,String password,String username,int index){
        this.FirstName=nume;
        this.LastName=prenume;
        this.email = Email;
        this.username = username;
        this.password=password;
        this.unique = index;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        this.creationDate = format.format(date);
        this.Inbox = new inbox();
    }
    public void print(){
        if(this.username != null)
            System.out.print("Username: "+this.username+"\n");
        if(this.email != null)
            System.out.print("Email : " + this.email+"\n");
        if(this.password != null)
            System.out.print("Password: "+this.password+"\n");
        if(this.FirstName != null)
            System.out.print("First name : " + this.FirstName+"\n");
        if(this.LastName != null)
            System.out.print("Last name : " + this.LastName+"\n");
        System.out.print("Unique ID : " + this.unique+"\n");
        System.out.print("Account creation date:"+this.creationDate+"\n");
    }
    public void changePass() {
        System.out.println("Enter a new password");
        Scanner key = new Scanner(System.in);
        this.password = key.next();
    }


    public void CreateEmail(database Accounts, String email) throws SQLException, IOException {
        email newEmail = new email();
        int response = Accounts.getUserIndex(newEmail.getEmail(),this.unique);
        if(response == -1)
            System.out.println("ERROR");
        else
        {
        Accounts.receiveEmail(response,newEmail);
        System.out.println("DONE");
        }

    }
    public void changeUser(){
        System.out.println("Enter a new password");
        Scanner key = new Scanner(System.in);
        this.username=key.next();
    }
    public String getUsername(){

        return this.username;
    }
    public inbox getInbox(){
        return this.Inbox;
    }
    public void setInbox(inbox newly){
        this.Inbox = newly;
    }
    public int getKey(){
        return this.unique;
    }
    @Override
    public int hashCode(){

        return password.hashCode();
    }


}