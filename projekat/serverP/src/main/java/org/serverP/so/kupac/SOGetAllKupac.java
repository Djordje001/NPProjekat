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
 * ova klasa predstavlja sistemsku operaciju za izcitavanje svih kupaca iz baze
 * @author Lenovo
 */
public class SOGetAllKupac extends AbstractSO {

	/**
	 * lista sa kupcima
	 */
    private ArrayList<Kupac> lista;

    
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
     * ova metoda treba da izvrsi sistemsku operaciju izcitavanja svih kupaca u bazi pozivanjem metode select naseg DBBrokera
     * @param ado apstraktni domenski objekat nad kojim se izvrsava SO
     * @throws java.lang.Exception ukoliko dodje do kreske prilikom izvrsavanja upita u bazi
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> kupci = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Kupac>) (ArrayList<?>) kupci;
    }

    
    /**
     * uzimavanje trenutne vrednosti liste autora
     * @return lista autora
     */
    public ArrayList<Kupac> getLista() {
        return lista;
    }

}