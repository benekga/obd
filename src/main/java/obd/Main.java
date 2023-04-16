package obd;
import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try{
            Connection conn = getConnection();

            Statement stmt=conn.createStatement();
            stmt.execute("drop table OCENIANIE");
            stmt.execute("drop table NAUCZYCIEL");
            stmt.execute("drop table PRZEDMIOT");
            stmt.execute("drop table UCZEN");
            stmt.execute("drop table OCENA");

            stmt.execute(SetUpDBContstraintsON.getSQLcreateNauczyciel());
            stmt.execute(SetUpDBContstraintsON.getSQLcreatePrzedmiot());
            stmt.execute(SetUpDBContstraintsON.getSQLcreateUczen());
            stmt.execute(SetUpDBContstraintsON.getSQLcreateOcena());
            stmt.execute(SetUpDBContstraintsON.getSQLcreateOcenianie());

            stmt.execute("insert into NAUCZYCIEL values (1, 'Walczak', 'Justyna')");
            stmt.execute("insert into NAUCZYCIEL values (3, 'Tomaszewska', 'Anna')");
            stmt.execute("insert into NAUCZYCIEL values (5, 'Jankowski', 'Henryk')");

            stmt.execute("insert into UCZEN values (141, 'Lubicz', 'Monika')");
            stmt.execute("insert into UCZEN values (321, 'Kowalski', 'Bartosz')");
            stmt.execute("insert into UCZEN values (149, 'Nowak', 'Patrycja')");

            stmt.execute("insert into PRZEDMIOT values (1, 'Biologia')");
            stmt.execute("insert into PRZEDMIOT values (2, 'Matematyka')");
            stmt.execute("insert into PRZEDMIOT values (9, 'Angielski')");

            stmt.execute("insert into OCENA values (8, 'dobry plus', 4.5)");
            stmt.execute("insert into OCENA values (7, 'dobry', 4.0)");
            stmt.execute("insert into OCENA values (6, 'dostateczny plus', 3.5)");


            System.out.println("---tabela nauczyciele---");
            ResultSet rs=stmt.executeQuery("select * from NAUCZYCIEL");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));

            System.out.println("\n ---tabela uczniowie---");
            rs=stmt.executeQuery("select * from UCZEN");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));

            System.out.println("\n ---tabela oceny---");
            rs=stmt.executeQuery("select * from OCENA");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));

            System.out.println("\n ---tabela przedmioty---");
            rs=stmt.executeQuery("select * from PRZEDMIOT");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2));

            conn.close();

        }catch(Exception e){ System.out.println(e);}

        Konsola.konsola();

    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");

//step2 create  the connection object
        String dbURL = "jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";
        String username = "bgaworc1";
        String password = "bgaworc1";
        Connection conn = DriverManager.getConnection(dbURL, username, password);
        return conn;
    }
}








/*
Name:
ora4
Username:
Twoja nazwa konta (=login name)
Password:
(zostanie podane przez prowadzącego)
[v] Save Password
Hostname:
ora4.ii.pw.edu.pl	(było: localhost)
Port:
1521	(zostawiamy domyślną wartość)
(*) Service name:
pdb1.ii.pw.edu.pl	(zamiast domyślnego zaznaczenia: (*) SID: xe)
 */









