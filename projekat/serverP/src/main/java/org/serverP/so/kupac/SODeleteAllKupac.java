/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.serverP.so.kupac;


import java.util.ArrayList;

import org.serverP.db.DBBroker;
import org.serverP.so.AbstractSO;
import org.zajednickiP.domain.AbstractDomainObject;
import org.zajednickiP.domain.Kupac;

/**
 * ova klasa predstavlja sistemsku operaciju za brisanje svih kupaca u bazi odjednom
 * @author Lenovo
 */
public class SODeleteAllKupac extends AbstractSO {

	/**
     * ova metoda ce ispitati da li je apstraktni domenski objekat validan
     * @param ado apstraktni domenski objekat nad kojim vrsimo validaciju
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     * @throws java.lang.IllegalArgumentException ukoliko nije prosledjena instanca klase Kupac
     */
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    	
    	if(ado==null) {
    		throw new NullPointerException("Prosledjeni objekat je null");
    	}
        if (!(ado instanceof Kupac)) {
            throw new IllegalArgumentException("Prosledjeni objekat nije instanca klase Kupac!");
        }

      

    }

    /**
     * ova metoda treba da izvrsi sistemsku operaciju brisanja svih kupaca u bazi pozivanjem deleteAll metode naseg DBBrokera
     * @param ado apstraktni domenski objekat nad kojim se izvrsava SO
     * @throws java.lang.Exception ukoliko dodje do kreske prilikom izvrsavanja upita u bazi
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        
        DBBroker.getInstance().deleteAll(ado);
    }

}