package obd;
public class SetUpDBContstraintsON {
    static String SQLcreateNauczyciel = "create table NAUCZYCIEL (idn int primary key not null , nazwisko_nauczyciela char(30) not null, imie_nauczyciela char(20) not null)";
    static String SQLcreatePrzedmiot = "create table PRZEDMIOT (idp int primary key not null, nazwa_przedmiotu char(20) not null)";
    static String SQLcreateUczen = "create table UCZEN (idu int primary key not null, nazwisko_ucznia char(30) not null, imie_ucznia char(20) not null)";
    static String SQLcreateOCENA = "create table OCENA (ido int primary key not null, wartosc_opisowa char(20) not null, wartosc_numeryczna float not null)";
    static String SQLcreateOCENIANIE = "create table OCENIANIE (rodzaj_oceny char(1) not null, idn int not null, idp int not null, idu int not null, ido int not null, " +
            "CONSTRAINT idn FOREIGN KEY (idn) REFERENCES NAUCZYCIEL (idn), " +
            "CONSTRAINT idp FOREIGN KEY (idp) REFERENCES PRZEDMIOT (idp)," +
            "CONSTRAINT idu FOREIGN KEY (idu) REFERENCES UCZEN (idu)," +
            "CONSTRAINT ido FOREIGN KEY (ido) REFERENCES OCENA (ido))";

    public static String getSQLcreateNauczyciel() {
        return SQLcreateNauczyciel;
    }
    public static String getSQLcreatePrzedmiot() {
        return SQLcreatePrzedmiot;
    }

    public static String getSQLcreateUczen() {
        return SQLcreateUczen;
    }

    public static String getSQLcreateOcena() {
        return SQLcreateOCENA;
    }

    public static String getSQLcreateOcenianie() {
        return SQLcreateOCENIANIE;
    }
}