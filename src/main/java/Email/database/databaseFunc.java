package Email.database;

import Email.email;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public interface databaseFunc {
    void readDatabase() throws IOException, SQLException;
    void printingData();
    void connection () throws SQLException;
    void addUser(String nume, String prenume, String Email, String password, String username, int index, Date date) throws SQLException;
    int getUserIndex(String sending,int unique) throws SQLException;
    void receiveEmail(int key, email receive) throws SQLException, FileNotFoundException,IOException;
}
