package obd;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            setupDropTables(stmt);
            createTablesAndConstraints(stmt);
            sampleInserts(stmt);
            printSampleTables(stmt);
            checkPrimaryKeys(stmt);

            //c 3 2 15 7
            //Konsola.konsola();
            //String insert = Konsola.getSQLinsert();
            //Konsola1 k1 = new Konsola1('c', 3, 2, 15, 7);

            Konsola1 k1 = Konsola1.input();
            String insert = k1.toString();

            System.out.println(insert);
            stmt.execute(insert);
            stmt.execute("commit");

            String SQLcheck = "select concat (imie_ucznia, nazwisko_ucznia) uczen, wartosc_opisowa otrzymal_ocenę, rodzaj_oceny, nazwa_przedmiotu z_przedniotu, concat(imie_nauczyciela, nazwisko_nauczyciela) wystawil \n" +
                    "from OCENIANIE o join NAUCZYCIEL n on o.idn = n.id join UCZEN u on o.idu = u.id join OCENA c on o.ido = c.id join PRZEDMIOT P on o.idp = p.id\n";

            System.out.println("\n ---tabela ocenianie---");
            ResultSet rs;
            rs = stmt.executeQuery("select * from OCENIANIE");
            while (rs.next())
                System.out.println(rs.getString(1) + "  " + rs.getString(2) + "" + rs.getString(3) + "" + rs.getString(4) + "" + rs.getString(5));


            System.out.println("\n --- uczen                              otrzymal_ocenę       rodzaj_oceny       z_przedmiotu      wystawił nauczyciel---");
            rs = stmt.executeQuery(SQLcheck);
            while (rs.next())
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5));


            conn.close();


        } catch (Exception e) {
            System.out.println(e);
        }


    }

    private static void printSampleTables(Statement stmt) throws SQLException {
        System.out.println("---tabela nauczyciele----------------------------------");
        ResultSet rs = stmt.executeQuery("select * from NAUCZYCIEL");
        while (rs.next())
            System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));

        System.out.println("\n ---tabela przedmioty--------------------------------");
        rs = stmt.executeQuery("select * from PRZEDMIOT");
        while (rs.next())
            System.out.println(rs.getInt(1) + "  " + rs.getString(2));

        System.out.println("\n ---tabela uczniowie---------------------------------");
        rs = stmt.executeQuery("select * from UCZEN");
        while (rs.next())
            System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));

        System.out.println("\n ---tabela oceny--------------------------------------");
        rs = stmt.executeQuery("select * from OCENA");
        while (rs.next())
            System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
    }

    private static void sampleInserts(Statement stmt) throws SQLException {
        stmt.execute("insert into NAUCZYCIEL values (1, 'Walczak', 'Justyna')");
        stmt.execute("insert into NAUCZYCIEL values (3, 'Tomaszewska', 'Anna')");
        stmt.execute("insert into NAUCZYCIEL values (5, 'Jankowski', 'Henryk')");
        stmt.execute("insert into UCZEN values (14, 'Lubicz', 'Monika')");
        stmt.execute("insert into UCZEN values (15, 'Kowalski', 'Bartosz')");
        stmt.execute("insert into UCZEN values (16, 'Nowak', 'Patrycja')");
        stmt.execute("insert into PRZEDMIOT values (1, 'Biologia')");
        stmt.execute("insert into PRZEDMIOT values (2, 'Matematyka')");
        stmt.execute("insert into PRZEDMIOT values (9, 'Angielski')");
        stmt.execute("insert into OCENA values (8, 'dobry plus', 4.5)");
        stmt.execute("insert into OCENA values (7, 'dobry', 4.0)");
        stmt.execute("insert into OCENA values (6, 'dostateczny plus', 3.5)");
    }

    private static void createTablesAndConstraints(Statement stmt) throws SQLException {
        stmt.execute(SetUpDBContstraintsON.getSQLcreateNauczyciel());
        stmt.execute(SetUpDBContstraintsON.getSQLcreatePrzedmiot());
        stmt.execute(SetUpDBContstraintsON.getSQLcreateUczen());
        stmt.execute(SetUpDBContstraintsON.getSQLcreateOcena());
        stmt.execute(SetUpDBContstraintsON.getSQLcreateOcenianie());
    }

    private static void setupDropTables(Statement stmt) throws SQLException {
        stmt.execute("drop table OCENIANIE");
        stmt.execute("drop table NAUCZYCIEL");
        stmt.execute("drop table PRZEDMIOT");
        stmt.execute("drop table UCZEN");
        stmt.execute("drop table OCENA");
    }

    private static void checkPrimaryKeys(Statement stmt) {
        List<Integer> kluczeNauczyciel = CheckKeys("NAUCZYCIEL", stmt);
        List<Integer> kluczePrzedmiot = CheckKeys( "PRZEDMIOT ", stmt);
        List<Integer> kluczeUczen = CheckKeys(     "UCZEN     ", stmt);
        List<Integer> kluczeOcena = CheckKeys(     "OCENA     ", stmt);

        System.out.println("\ndopuszczalne wartości ID w tabeli NAUCZYCIEL: "+kluczeNauczyciel.toString());
        System.out.println("dopuszczalne wartości ID w tabeli PRZEDMIOT: "+kluczePrzedmiot.toString());
        System.out.println("dopuszczalne wartości ID w tabeli UCZEN: "+kluczeUczen.toString());
        System.out.println("dopuszczalne wartości ID w tabeli OCENA: "+kluczeOcena.toString());
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        String dbURL = "jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";
        String username = "bgaworc1";
        String password = "bgaworc1";
        Connection conn = DriverManager.getConnection(dbURL, username, password);
        return conn;
    }


    private static List<Integer> CheckKeys(String nameOfTable, Statement st) {
        boolean answer = false;

        String queryToCheckKey = "select id from "+nameOfTable;

        List<Integer> primaryKeys = new ArrayList<Integer>();


        ResultSet rs = null;
        try {
            rs = st.executeQuery(queryToCheckKey);

            while (rs.next())
                primaryKeys.add(rs.getInt(1));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return primaryKeys;
    }


}









