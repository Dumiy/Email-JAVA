
package Email;
import java.util.ArrayList;



public class inbox {
    private ArrayList<email> Inbox ;
    private Integer lenght;
    public inbox(){
        this.Inbox = new ArrayList<email>();
    }
    public void addEmail(email receive){
        this.Inbox.add(receive);
        this.lenght = Inbox.size();
    }
    public Integer getLenght(){
        return this.lenght;
    }
    public ArrayList returnInbox(inbox current){
        if (current.lenght != 0)
            return current.Inbox;
        else
            return null;
    }

}
