use ora4;

commit;

drop table OCENIANIE;
drop table NAUCZYCIEL;  drop table PRZEDMIOT; drop table UCZEN; drop table OCENA; 

create table NAUCZYCIEL (idn int primary key not null , nazwisko_nauczyciela char(30) not null, imie_nauczyciela char(20) not null);
create table PRZEDMIOT (idp int primary key not null, nazwa_przedmiotu char(20) not null);
create table UCZEN (idu int primary key not null, nazwisko_ucznia char(30) not null, imie_ucznia char(20) not null);
create table OCENA (ido int primary key not null, wartosc_opisowa char(20) not null, wartosc_numeryczna float not null);

create table OCENIANIE (rodzaj_oceny char(1) not null, idn int not null, idp int not null, idu int not null, ido int not null, 
CONSTRAINT idn FOREIGN KEY (idn) REFERENCES NAUCZYCIEL (idn),
CONSTRAINT idp FOREIGN KEY (idp) REFERENCES PRZEDMIOT (idp),
CONSTRAINT idu FOREIGN KEY (idu) REFERENCES UCZEN (idu),
CONSTRAINT ido FOREIGN KEY (ido) REFERENCES OCENA (ido)
);

insert into NAUCZYCIEL values (1, 'Walczak', 'Henryk'); 
insert into NAUCZYCIEL values (3, 'Tomaszewski', 'Waldemar'); 
insert into NAUCZYCIEL values (104, 'Witkowska', 'Izabela');
insert into NAUCZYCIEL values (124, 'Wit', 'Bogdan');

insert into PRZEDMIOT values (1, 'Biologia'); 
insert into PRZEDMIOT values (2, 'Matematyka'); 
insert into PRZEDMIOT values (6, 'Chemia');
insert into PRZEDMIOT values (9, 'Angielski');

insert into UCZEN values (14, 'Lubicz', 'Monika'); 
insert into UCZEN values (15, 'Kowalski', 'Bartosz'); 
insert into UCZEN values (16, 'Nowak', 'Patrycja');

insert into OCENA values (5, 'Dostateczny', 3.0);
insert into OCENA values (6, 'Dostateczny Plus', 3.5);
insert into OCENA values (7, 'Dobry', 4.0);
insert into OCENA values (8, 'Dobry Plus', 4.5);


--insert into OCENIANIE values ('c', 1, 1, 14, 7);
insert into OCENIANIE values ('c',3,2,15,7);

select * from OCENIANIE;


select concat (imie_ucznia, nazwisko_ucznia) uczen, wartosc_opisowa otrzymal_ocenê, rodzaj_oceny, nazwa_przedmiotu z_przedniotu, concat(imie_nauczyciela, nazwisko_nauczyciela) wystawi³
from OCENIANIE o 
join NAUCZYCIEL n on o.idn = n.idn
join UCZEN u on o.idu = u.idu
join OCENA c on o.ido = c.ido
join PRZEDMIOT P on o.idp = p.idp
;



truncate table NAUCZYCIEL;
truncate table UCZEN;
truncate table PRZEDMIOT;
truncate table OCENIANIE;
truncate table OCENA;



select concat (imie_ucznia, nazwisko_ucznia) uczen, wartosc_opisowa otrzymal_ocenê, rodzaj_oceny, nazwa_przedmiotu z_przedniotu, concat(imie_nauczyciela, nazwisko_nauczyciela) wystawil 
from OCENIANIE o join NAUCZYCIEL n on o.idn = n.idn join UCZEN u on o.idu = u.idu join OCENA c on o.ido = c.ido join PRZEDMIOT P on o.idp = p.idp







