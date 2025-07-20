# Biblioteka 

## O projektu

Ovo je projekat predstavlja zavrsni deo kursa, sa ciljem da demonstrira, tj. implementira znanja stečena tokom kursa, kao što su: rad u Java programu, korišćenje baza, softverskih paterna, 
pravljenje GUI, JDBC, analiza korisničkih zahteva i modeleranje softvera. 

Sam projekat se sastoji iz dve aplikacije/projekta. Prvi je desktop aplikacija (nalazi se u folderu 'Biblioteka_DesktopApp'), koja odgovara na korisnički zahtev za projektovanjem aplikacije
koja vrši praćenje, čuvanje i ažuriranje podataka o izdavanju knjiga u biblioteci. 
Druga aplikacija je server-klijent aplikacija (nalazi se u folderu 'Biblioteka_ServerKlijentApp'), i predstavlja dalju razradu prve aplikacije, u tom smislu što razdvaja prvobitnu 
aplikaciju na serversku i klijentsku stranu.
Dok prva aplikacija ima sve željene funkcionalnosti, za drugu aplikaciju sam odabrao najkompleksniju celinu i implementirao njene funkcionalnosti, sto je u skladu sa zahtevom kursa da se 
demonstrira kompentencija u korišćenju socket-a i konkurentnog programiranja u određenom domenu. 


## Kako je napravljen 

Za pravljenje aplikacije korišćeni su: Java i MySQL.

Za pisanje Java koda korišćen je Apache NetBeans IDE 23, i stoga je za aktivaciju svih funkcionalnosti poželjno program pokretati iz NetBeans-a.


## Sadržaj GitHub repozitorijuma

U folderu 'Biblioteka_DesktopApp' se nalazi 'MyLibraryApp' folder, tj. prvoopisani projekat.

U folderu 'Biblioteka_ServerKlijentApp' se nalazi drugoopisani projekat, koji se sastoji od 3 Java projekta ('LibraryClient', 'LibraryCommon' i 'LibraryServer').

Takođe, ovde se nalaze i 'biblioteka_baza', odnosno baza sa podacima vezanim za projekat, kao i korisnički zahtev, relacioni i domenski model.

