package obd;
import java.util.Scanner;

public class Konsola1 {
    private char rodzajOceny;
    private int idNauczyciela;
    private int idPrzedmiotu;
    private int idUcznia;
    private int idOceny;


    public Konsola1(char rodzajOceny, int idNauczyciela, int idPrzedmiotu, int idUcznia, int idOceny) {
        this.rodzajOceny = rodzajOceny;
        this.idNauczyciela = idNauczyciela;
        this.idPrzedmiotu = idPrzedmiotu;
        this.idUcznia = idUcznia;
        this.idOceny = idOceny;
    }

    @Override
    public String toString() {
        return "insert into OCENIANIE values('" + rodzajOceny + "'," + idNauczyciela + "," + idPrzedmiotu + "," + idUcznia + "," + idOceny + ")";
    }

    public static Konsola1 input() {
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

        Konsola1 k1 = new Konsola1(rodzajOceny, idNauczyciela, idPrzedmiotu, idUcznia, idOceny);
        return k1;
}
}







