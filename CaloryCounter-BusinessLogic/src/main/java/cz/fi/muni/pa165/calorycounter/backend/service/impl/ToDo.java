package cz.fi.muni.pa165.calorycounter.backend.service.impl;

public class ToDo {
    /*
     * 1. dokoncit ostatne DTOs a Convert classes ku kazdemu!!!
     * 2. service vrstvu, zatial mozno aj bez Springu a jeho anotacii
     * 3. Spring - uvolnit vsetky hardcoded vazby - teda v service vrstve nebudeme priamo volat SomeDao.create(),
     * ale to nainjektujeme; T.j. keby sme mali viac Dao implementacii na vyber, tak cez spring vyberieme jednu.
     * Detto entitymanager resp. spojenie na databazu a transakcie nekodovat priamo v service triedach, ale injektovat.
     * Embedded DB prerobit z Persistence Unit na Persistence Context cez Spring?
     * 4. DataAccessException
     * 5. ten AOP na opakujuce sa casti kodu
     * 6. ActivityRecordConvert: issue na r. 41
     * 7. Testy! + Javadoc!
     * 8. Neskor: logovanie tiez robit cez Spring Aspect, a nie priamo v business kode. Detto vsetky URL typu
     * jdbc:derby://localhost:1527/CaloryDB nastavit v samostatnom properties subore a cez PropertyPlaceholderConfigurer.
     */
    /*
     * Co sa ho chcem spytat:
     * 1. checkpoint assignment: "API of backend will use dedicated transfer objects, not entities" - mame
     * to teraz dobre? Ak nie, ako inak by sme mohli pracovat s DAO bez toho aby sme si konvertli DTO na DAO
     * niekde v business vrstve?
     * 3. DataAccessException - ktoru podtriedu mame vyhadzovat?
     * 4. ako dostat EntityManager do toho persistence-contextu: staci LocalEntityManagerFactoryBean?
     * (Shared EntityManager proxy for target factory [org.springframework.orm.jpa.LocalEntityManagerFactoryBean@5e4b530d])
     * 5. ci pre optimalizaciu mozme robit samostatnu DAO triedu.

     * Martin: ActivityDto, ActivityService + ActivityConvert;
     * Jano: UserStatsDto, + service + dao; vid https://www.facebook.com/photo.php?fbid=10202343599778259&set=p.10202343599778259&type=1&theater
     * Zdenek: UserActivityRecordsDao + service; + AuthUserConvert
     *
     * Nema sa v DTO namiesto Listu a Mapy vracat ich nemodifikovatelna kopia?
     * model.Activity by mala mat unikatne meno. Po pokuse vlozit do DB novu model.Activity, ktora tam uz je, by sa malo vratit ID existujucej.
     */
}
