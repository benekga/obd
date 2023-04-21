package obd;
public class SetDBnoConstraints {

    static String SQLcreateNauczycielNo = "create table NAUCZYCIEL (id int not null , nazwisko_nauczyciela char(30) not null, imie_nauczyciela char(20) not null)";
    static String SQLcreatePrzedmiotNo = "create table PRZEDMIOT (id int not null, nazwa_przedmiotu char(20) not null)";
    static String SQLcreateUczenNo = "create table UCZEN (id int not null, nazwisko_ucznia char(30) not null, imie_ucznia char(20) not null)";
    static String SQLcreateOCENAno = "create table OCENA (id int not null, wartosc_opisowa char(20) not null, wartosc_numeryczna float not null)";
    static String SQLcreateOCENIANIENo = "create table OCENIANIE (rodzaj_oceny char(1) not null, idn int not null, idp int not null, idu int not null, ido int not null, ";


    public static String getSQLcreateNauczyciel() {
        return SQLcreateNauczycielNo;
    }
    public static String getSQLcreatePrzedmiot() {
        return SQLcreatePrzedmiotNo;
    }
    public static String getSQLcreateUczen() {
        return SQLcreateUczenNo;
    }
    public static String getSQLcreateOcena() {
        return SQLcreateOCENAno;
    }
    public static String getSQLcreateOcenianie() {
        return SQLcreateOCENIANIENo;
    }
}
