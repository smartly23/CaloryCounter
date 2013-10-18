package cz.fi.muni.pa165.calorycounter.backend.service.impl;

public class ToDo {
    /*
     * 1. Spring - uvolnit vsetky hardcoded vazby - teda v service vrstve nebudeme priamo volat SomeDao.create(),
     * ale to nainjektujeme; T.j. keby sme mali viac Dao implementacii na vyber, tak cez spring vyberieme jednu.
     * Detto entitymanager resp. spojenie na databazu a transakcie nekodovat priamo v service triedach, ale injektovat.
     * 2. Neskor: logovanie tiez robit cez Spring Aspect, a nie priamo v business kode. Detto vsetky URL typu
     * jdbc:derby://localhost:1527/CaloryDB nastavit v samostatnom properties subore a cez PropertyPlaceholderConfigurer.
     * 3. DataAccessException
     * 4. Premysliet, ako maju definitivne vyzerat DTO a ako budu pouzivane vo webovom rozhrani
     * 5. service klasy - tu uz by mali byt presne take metody, ake bude vyuzivat user a authuser, tzn. ze pravdepodobne
     * nebudu stacit len doterajsieCRUD metody
     * 6. kazdy implementuje jednu trieud z convert packagu, jednu zo serviceimpl a jeden test
     * 7. commitnut ten UserDaoTest!
     */
}
