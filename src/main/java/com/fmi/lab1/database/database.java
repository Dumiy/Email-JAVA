package com.fmi.lab1.database;

import com.fmi.lab1.account;
import com.fmi.lab1.email;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


public class database implements Serializable {
    private ArrayList<account> accountList = new ArrayList<account>();
    private Connection dB ;
    public database(){
        try {
            connection();
            readDatabase();

        }
        catch(IOException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void readDatabase() throws IOException, SQLException {
        FileInputStream file = new FileInputStream( new File("C:/Users/ASDERTY/IdeaProjects/PAOOO/excel/INFO.xlsx"));
        Workbook workbook = new XSSFWorkbook("C:/Users/ASDERTY/IdeaProjects/PAOOO/excel/INFO.xlsx");
        Sheet sheet = workbook.getSheetAt(0);
        int j  = 0;
        for (Row row : sheet) {
            int i = 0 ;
            Map<Integer,String> acc = new HashMap<Integer,String>();
            Cell cell1 = row.getCell(0);
            Cell cell2 = row.getCell(1);
            Cell cell3 = row.getCell(2);
            Cell cell4 = row.getCell(3);
            Cell cell5 = row.getCell(4);
            if (cell1 != null && (cell1.getStringCellValue().contains("Nume") != true && cell1.getStringCellValue().length() > 1) ) {
                acc.put(0, cell1.getStringCellValue());
                i++;
            }
            if (cell2 != null && (cell2.getStringCellValue().contains("Prenume") != true  && cell2.getStringCellValue().length() > 1)) {
                acc.put(1, cell2.getStringCellValue());
                i++;
            }
            if (cell3 != null && (cell3.getStringCellValue().contains("Adresa email") != true  && cell3.getStringCellValue().length() > 1)) {
                acc.put(2, cell3.getStringCellValue());
                i++;
            }
            if (cell4 != null && (cell4.getStringCellValue().contains("Parola email") != true && cell4.getStringCellValue().length() > 1)) {
                acc.put(3, cell4.getStringCellValue());
                i++;
            }
            if (cell5 != null && (cell5.getStringCellValue().contains("Utilizator Moodle") != true &&  cell5.getStringCellValue().length() > 1 )) {
                acc.put(4, cell5.getStringCellValue());
                i++;
            }
            if( i == 5) {
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Date date = new Date();
                String creationDate = format.format(date);
                account Tempo = new account(acc.get(0), acc.get(1), acc.get(2), acc.get(3), acc.get(4), j);
                //addUser(acc.get(0), acc.get(1), acc.get(2), acc.get(3), acc.get(4), j,date);
                this.accountList.add(Tempo);
                j++;
                i = 0;
            }
            else i = 0;
        }

    }
    public void printingData(){
        Iterator<account> accountIterator = accountList.iterator();
        while(accountIterator.hasNext()){
            accountIterator.next().print();
        }

    }

    public void connection () throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.dB = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "12345");
            Statement apel = this.dB.createStatement();
            ResultSet result = apel.executeQuery("select * from users");
            while (result.next()) {
                System.out.println(result.getString(1));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addUser(String nume,String prenume,String Email,String password,String username,int index,Date date) throws SQLException {
        PreparedStatement  result = this.dB.prepareStatement(
            " INSERT INTO users (idusers,username,firstname,lastname,email,creation,password)" +
                    "VALUES (?,?,?,?,?,?,?)"
        );
        java.sql.Date jsqlD =
                new java.sql.Date( date.getTime());
        result.setInt(1,index);
        result.setString(2,username);
        result.setString(3,nume);
        result.setString(4,prenume);
        result.setString(5,Email);
        result.setDate(6, jsqlD);
        result.setString(7,password);
        result.executeUpdate();
        }
        public int getUserIndex(String email,int unique) throws SQLException {
            PreparedStatement audit = this.dB.prepareStatement("INSERT INTO userlog (id,object,time,action)"+
                            "VALUES (?,?,?,?)");
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date();
            java.sql.Date jsqlD =
                    new java.sql.Date( date.getTime());
            Statement search = this.dB.createStatement();
            ResultSet person = search.executeQuery("SELECT idusers FROM users WHERE " + email.toUpperCase() +" = email");
            if (email.equals(person.getString(1))) {
                String action = "Search for user" + email;
                audit.setInt(1,unique);
                audit.setString(2,"User Searching of ID" + person.getString(1));
                audit.setDate(3,jsqlD);
                audit.setString(4,action);
                return person.getInt(1);

            }
            return -1;
        }
        public void receiveEmail(int key, email receive) throws SQLException, FileNotFoundException,IOException {
            ArrayList <Character> helper = new ArrayList<Character>();
            PreparedStatement audit = this.dB.prepareStatement("INSERT INTO userlog (id,object,time,action)"+
                    "VALUES (?,?,?,?)");
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date();
            java.sql.Date jsqlD =
                    new java.sql.Date( date.getTime());
            FileOutputStream file = new FileOutputStream("dummy.txt");
            ObjectOutputStream letter = new ObjectOutputStream(file);
            letter.writeObject(receive);
            FileInputStream getObject = new FileInputStream("dummy.txt");
            int i;
            while((i=getObject.read())!=-1)
                helper.add((char) i);

            for(account find : this.accountList){
                if(find.getKey() == key) {
                    find.getInbox().addEmail(receive);
                    String action = "Sended object" + receive;
                    audit.setInt(1,key);
                    audit.setString(2,helper.toString());
                    audit.setDate(3,jsqlD);
                    audit.setString(4,action);
                    break;
                }

            }
            file.close();
            letter.close();
            getObject.close();
            File temp = new File("dummy.txt");

            if(temp.delete())
            {
                System.out.println("File deleted successfully");
            }
            else
            {
                System.out.println("Failed to delete the file");
            }


        }
        public Boolean login(String username,String password){
        int temporary;
        temporary = password.hashCode();

        return true;
        //return false;
        }
    @Override
    public void finalize(){
        try{
            this.dB.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
