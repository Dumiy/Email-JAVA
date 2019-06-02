package com.fmi.lab1;

import com.fmi.lab1.database.database;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
    database d = new database();
    account x = d.login("CLAUDIU-CRISTIAN.DUMITRASCU@my.fmi.unibuc.ro","parola.fmi123");
    x.CreateEmail(d,"CLAUDIU-CRISTIAN.DUMITRASCU@my.fmi.unibuc.ro");


    }
}
