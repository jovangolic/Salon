DROP SCHEMA IF EXISTS salon;
CREATE SCHEMA salon DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE salon;

CREATE TABLE frizeri (
	id bigint not null auto_increment,
    ime varchar(256) not null,
    cena decimal(10,2) not null,
    radniDani varchar(256) not null,
    smena int not null,
    primary key(id)
);

CREATE TABLE termini (
	id bigint auto_increment not null,
    frizerId bigint not null,
    datumIVreme datetime not null,
    ime varchar(100) not null,
    email varchar(30) not null,
    telefon varchar(40) not null,
    primary key(id),
    FOREIGN KEY(frizerId) REFERENCES frizeri (id)
);

INSERT INTO frizeri( ime, cena, radniDani, smena) VALUES( "Frizer 1",600.0,"MONDAY,WEDNESDAY,FRIDAY",1);
INSERT INTO frizeri( ime, cena, radniDani, smena) VALUES( "Frizer 2",600.0,"MONDAY,WEDNESDAY,FRIDAY",2);
INSERT INTO frizeri( ime, cena, radniDani, smena) VALUES( "Frizer 3",600.0,"MONDAY,WEDNESDAY,FRIDAY",1);
INSERT INTO frizeri( ime, cena, radniDani, smena) VALUES( "Frizer 4",700.0,"TUESDAY,THURSDAY,SATURDAY",2);
INSERT INTO frizeri( ime, cena, radniDani, smena) VALUES( "Frizer 5",700.0,"TUESDAY,THURSDAY,SATURDAY",1);
INSERT INTO frizeri( ime, cena, radniDani, smena) VALUES( "Frizer 6",700.0,"TUESDAY,THURSDAY,SATURDAY",2);


INSERT INTO termini( frizerId, datumIVreme, ime, email, telefon) VALUES(1,'2024-01-02 09:00', 'Aaa', 'a@a.com', '+38169200***7');
INSERT INTO termini( frizerId, datumIVreme, ime, email, telefon) VALUES(1,'2024-01-02 11:00', 'Bbb', 'b@b.com', '+38161357***3');
INSERT INTO termini( frizerId, datumIVreme, ime, email, telefon) VALUES(2,'2024-01-02 13:00', 'Ccc', 'c@c.com', '+38163329***2');
INSERT INTO termini( frizerId, datumIVreme, ime, email, telefon) VALUES(4,'2024-01-02 14:00', 'Ddd', 'd@d.com', '+38169066***5');
INSERT INTO termini( frizerId, datumIVreme, ime, email, telefon) VALUES(5,'2024-01-02 16:00', 'Eee', 'e@e.com', '+38161584***6');
INSERT INTO termini( frizerId, datumIVreme, ime, email, telefon) VALUES(6,'2024-01-02 18:00', 'Fff', 'f@f.com', '+38164944***8');
INSERT INTO termini( frizerId, datumIVreme, ime, email, telefon) VALUES(1,'2024-01-03 09:00', 'Ggg', 'g@g.com', '+38164554***1');
INSERT INTO termini( frizerId, datumIVreme, ime, email, telefon) VALUES(2,'2024-01-03 11:00', 'Hhh', 'h@h.com', '+38162072***0');
INSERT INTO termini( frizerId, datumIVreme, ime, email, telefon) VALUES(3,'2024-01-03 13:00', 'Iii', 'i@i.com', '+38169945***6');
INSERT INTO termini( frizerId, datumIVreme, ime, email, telefon) VALUES(4,'2024-01-03 14:00', 'Jjj', 'j@j.com', '+38169758***3');
INSERT INTO termini( frizerId, datumIVreme, ime, email, telefon) VALUES(4,'2024-01-03 16:00', 'Kkk', 'k@k.com', '+38162313***6');
INSERT INTO termini( frizerId, datumIVreme, ime, email, telefon) VALUES(5,'2024-01-03 18:00', 'Lll', 'l@l.com', '+38166193***1');
INSERT INTO termini( frizerId, datumIVreme, ime, email, telefon) VALUES(2,'2024-01-04 09:00', 'Mmm', 'm@m.com', '+38161094***0');
INSERT INTO termini( frizerId, datumIVreme, ime, email, telefon) VALUES(2,'2024-01-04 11:00', 'Nnn', 'n@n.com', '+38164535***4');
INSERT INTO termini( frizerId, datumIVreme, ime, email, telefon) VALUES(3,'2024-01-04 13:00', 'Ooo', 'o@o.com', '+38166650***1');
INSERT INTO termini( frizerId, datumIVreme, ime, email, telefon) VALUES(5,'2024-01-04 14:00', 'Ppp', 'p@p.com', '+381659331***9');
INSERT INTO termini( frizerId, datumIVreme, ime, email, telefon) VALUES(6,'2024-01-04 16:00', 'Qqq', 'q@q.com', '+38166446***0');
INSERT INTO termini( frizerId, datumIVreme, ime, email, telefon) VALUES(6,'2024-01-04 18:00', 'Rrr', 'r@r.com', '+38168796***2');
INSERT INTO termini( frizerId, datumIVreme, ime, email, telefon) VALUES(1,'2024-01-05 09:00', 'Sss', 's@s.com', '+38169499***2');
INSERT INTO termini( frizerId, datumIVreme, ime, email, telefon) VALUES(1,'2024-01-05 11:00', 'Ttt', 't@t.com', '+38163301***7');
INSERT INTO termini( frizerId, datumIVreme, ime, email, telefon) VALUES(3,'2024-01-05 13:00', 'Uuu', 'u@u.com', '+38161374***5');
INSERT INTO termini( frizerId, datumIVreme, ime, email, telefon) VALUES(6,'2024-01-05 14:00', 'Vvv', 'v@v.com', '+38165632***8');
INSERT INTO termini( frizerId, datumIVreme, ime, email, telefon) VALUES(6,'2024-01-05 16:00', 'Www', 'w@w.com', '+38162636***1');
INSERT INTO termini( frizerId, datumIVreme, ime, email, telefon) VALUES(6,'2024-01-05 18:00', 'Xxx', 'x@x.com', '+38162306***0');
INSERT INTO termini( frizerId, datumIVreme, ime, email, telefon) VALUES(1,'2024-01-06 09:00', 'Yyy', 'y@y.com', '+38165249***5');
INSERT INTO termini( frizerId, datumIVreme, ime, email, telefon) VALUES(1,'2024-01-06 11:00', 'Zzz', 'z@z.com', '+38168933***2');


select * from frizeri;
select * from termini;
