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

            Konsola k1 = new Konsola();
            // obiekt służy zbudowaniu SQL insert poprzez okno dialogowe (Scanner) z while loop, który wymusi poprawność wprowadzaonych danych
            // przy uruchomieniu metody inputNauczyciel przekazuję listę kluczy PK z tabeli nauczyciel,
            // metoda sprawdzi czy proponowane przez użytkownika idNauczyciela jest na liście kluczy głównych tabeli nauczyciel (= klucz obcy na tabeli ocenianie).

            k1.inputRodzaj();
            k1.inputNauczyciel(CheckKeys("NAUCZYCIEL", stmt));
            k1.inputPrzedmiot(CheckKeys( "PRZEDMIOT", stmt));
            k1.inputUczen(CheckKeys(     "UCZEN", stmt));
            k1.inputOcena(CheckKeys( "OCENA", stmt));
            System.out.println("Utworzony SQL insert wygląda tak: \n"+  k1.toString());

            String SQLinsert = k1.toString();
            stmt.executeQuery(SQLinsert);
            insertsConfirmation(stmt);

            conn.close();


        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private static void insertsConfirmation(Statement stmt) throws SQLException {
        String SQLcheck = "select  'UCZEŃ ' || imie_ucznia || nazwisko_ucznia as uczen, " +
                "'OTRZYMAŁ OCENĘ ' || wartosc_opisowa, 'TYPU ' ||  rodzaj_oceny, 'Z PRZEDMIOTU ' || nazwa_przedmiotu, " +
                "'WYSTAWIŁ NAUCZYCIEL ' || imie_nauczyciela || nazwisko_nauczyciela " +
                "from OCENIANIE o join NAUCZYCIEL n on o.idn = n.id join UCZEN u on o.idu = u.id join OCENA c on o.ido = c.id join PRZEDMIOT P on o.idp = p.id";

        System.out.println("\n ---tabela ocenianie---");
        ResultSet rs;
        rs = stmt.executeQuery("select * from OCENIANIE");
        while (rs.next())
            System.out.println(rs.getString(1) + "|" + rs.getString(2) + "|" + rs.getString(3) + "|" + rs.getString(4) + "|" + rs.getString(5));


        rs = stmt.executeQuery(SQLcheck);
        while (rs.next())
            System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5));
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
        stmt.execute(SetUpDBwithConstraints.getSQLcreateNauczyciel());
        stmt.execute(SetUpDBwithConstraints.getSQLcreatePrzedmiot());
        stmt.execute(SetUpDBwithConstraints.getSQLcreateUczen());
        stmt.execute(SetUpDBwithConstraints.getSQLcreateOcena());
        stmt.execute(SetUpDBwithConstraints.getSQLcreateOcenianie());
    }



    private static void setupDropTables(Statement stmt) throws SQLException {
        stmt.execute("drop table OCENIANIE");
        stmt.execute("drop table NAUCZYCIEL");
        stmt.execute("drop table PRZEDMIOT");
        stmt.execute("drop table UCZEN");
        stmt.execute("drop table OCENA");
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









