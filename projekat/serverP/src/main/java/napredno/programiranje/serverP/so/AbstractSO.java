/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napredno.programiranje.serverP.so;


import java.sql.SQLException;

import napredno.programiranje.serverP.db.DBBroker;
import napredno.programiranje.zajednickiP.domain.AbstractDomainObject;

/**
 *ova klasa predstavlja abstraktnu sistemsku operaciju sa apstraktnim metodama:validate i execute koje moraju da implementiraju sistemske operacije koje nasledjuju ovu klasu
 *pored te 2 metode sadrzi i templeteMethodPatern u vidu metode templeteExecute , i pored ove sadrzi  metode commit i rollback koje su genericke za sve sistemske operacije zato su i impementirane u ovoj klasi
 * @author Djordje Djordjevic
 */
public abstract class AbstractSO {
    
	/**
	 * Ova metoda treba da proveri da li je zahtev validan 
	 * @param ado apstraktni domenski objekat koji se proverava da li je validan
	 * @throws java.lang.Exception ukoliko je zahtev nevalidan
	 */
    protected abstract void validate(AbstractDomainObject ado) throws Exception;
    
    /**
     * ova metoda treba da izvrsi konkretnu sistemsku operaciju 
     * @param ado apstraktni domenski objekat nad kojim se izvrsava SO
     * @throws java.lang.Exception ukoliko dodje do kreske prilikom izvrsavanja upita u bazi
     */
    protected abstract void execute(AbstractDomainObject ado) throws Exception;

    /**
     * templete metod patern koji se sastoji iz pozivanja metode validate i execute podklasa ove klase, i pozivanja generickih metoda commit i rollback ove klase
     * @param ado apstraktni domenski objekat koji se prvo proverava da li je validan pa se onda izvrsava sistemska operacija
     * @throws java.langException ukoliko u bilo kom trenutku dodje do greske koja je rezultovana losom validacijom ili losem izvrsavanjem nad bazom
     */
    public void templateExecute(AbstractDomainObject ado) throws Exception {
        try {
            validate(ado);
            execute(ado);
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    /**
     * permanentno zapisuje sve promene izvrsene u tekucoj transakciji u bazu podataka
     * @throws java.sql.SQLException ukoliko se desio prekid veze sa bazom,ukoliko postoji neautorizovani pristup nad commit operacijom, ili ako je transakcija vec zatvorena
     */
    public void commit() throws SQLException {
        DBBroker.getInstance().getConnection().commit();
    }

    
    /**
     * Vraća sve promene izvršene u tekućoj transakciji u prvobitno stanje, poništavajući ih
     * @throws java.sql.SQLException ukoliko se desio prekid veze sa bazom,ukoliko postoji neautorizovani pristup nad commit operacijom, ili ako je transakcija vec zatvorena
     */
    public void rollback() throws SQLException {
        DBBroker.getInstance().getConnection().rollback();
    }
}
