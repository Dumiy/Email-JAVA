package com.fmi.lab1;

import com.fmi.lab1.database.database;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
    database d = new database();
    d.login("CLAUDIU-CRISTIAN.DUMITRASCU@my.fmi.unibuc.ro","parola.fmi123");


    }
}
