
package Email;
import java.io.Serializable;
import java.util.Scanner;



public class email implements Serializable {
    private String contains;
    private boolean unread;
    private boolean read;
    private String title;
    private String target;
    private String CC;
    private String BCC;
    public  email(){
        Scanner input = new Scanner(System.in);
        System.out.println("Target: ");
        this.target=input.next();
        System.out.println("Title: ");
        this.title=input.next();
        System.out.println("CC: ");
        this.CC=input.next();
        System.out.println("BCC" );
        this.BCC=input.next();
        System.out.println("Contains: ");
        this.contains=input.next();
        this.unread = true;
        this.read= false;
    }
    public email(String target,String title,String sidePerson,String hidenPerson,String text)
    {
        this.target=target;
        this.title=title;
        this.CC=sidePerson;
        this.BCC=hidenPerson;
        this.contains=text;
        this.unread = true;
        this.read= false;
    }
    public String getEmail(){
        return this.target;
    }
    public void readEmail(){
        display();
        this.unread = false;
        this.read = true;
    }
    public void unreadEmail(){
        this.unread = true;
        this.read = false;
    }
    public void display(){

    }
}
