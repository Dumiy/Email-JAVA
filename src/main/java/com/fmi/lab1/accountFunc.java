<<<<<<< HEAD
package com.fmi.lab1;

import com.fmi.lab1.database.database;

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
=======
package com.fmi.lab1;

import com.fmi.lab1.database.database;

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
>>>>>>> 09cd27a5507523bbde0818cbc630ee96363bb076
