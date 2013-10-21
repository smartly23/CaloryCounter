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
     * 7. Testy!
     * 8. Neskor: logovanie tiez robit cez Spring Aspect, a nie priamo v business kode. Detto vsetky URL typu
     * jdbc:derby://localhost:1527/CaloryDB nastavit v samostatnom properties subore a cez PropertyPlaceholderConfigurer.
     */
}
