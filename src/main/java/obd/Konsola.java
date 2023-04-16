package obd;
import java.util.Scanner;

public class Konsola {
    static String SQLinsert;

    public static String getSQLinsert() {
        return SQLinsert;
    }

    public static void setSQLinsert(char rodzajOceny, int idNauczyciela, int idPrzedmiotu, int idUcznia, int idOceny) {
        Konsola.SQLinsert = "insert into OCENIANIE values('"+rodzajOceny+"',"+idNauczyciela +","+idPrzedmiotu +"," +idUcznia +"," + idOceny+")";
    }

    public static void konsola() {
        Scanner myObject = new Scanner(System.in);
        System.out.println("'\n Wprowadź atrybuty oceny: " +
                "rodzaj oceny ('C' lub 'S'), " +
                "id nauczyciela, " +
                "id przedmiotu, " +
                "id ucznia, " +
                "id oceny");
        char rodzajOceny = myObject.next().charAt(0);
        int idNauczyciela = myObject.nextInt();
        int idPrzedmiotu = myObject.nextInt();
        int idUcznia = myObject.nextInt();
        int idOceny = myObject.nextInt();

        setSQLinsert(rodzajOceny, idNauczyciela, idPrzedmiotu, idUcznia, idOceny);

        System.out.println(SQLinsert);
    }

}


