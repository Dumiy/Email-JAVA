
package Email;

import Email.database.database;

import java.io.IOException;
import java.sql.SQLException;

public interface accountFunc {
    void print();
    void CreateEmail(database Accounts, String email) throws SQLException, IOException;
    void changeUser();
    String getUsername();
    inbox getInbox();
    void setInbox(inbox a);
    int getKey();
    int hashCode();
}

