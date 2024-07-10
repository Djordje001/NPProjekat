/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.serverP.so.administrator;


import java.sql.SQLException;
import java.util.ArrayList;

import org.serverP.db.DBBroker;
import org.serverP.so.AbstractSO;
import org.zajednickiP.domain.AbstractDomainObject;
import org.zajednickiP.domain.Administrator;
import org.zajednickiP.domain.Kupac;


/**
 *ova klasa predstavlja sistemsku operaciju za izcitavanje svih administratora iz baze
 * @author Lenovo
 */
public class SOGetAllAdministrator extends AbstractSO {

	/**
	 * lista u kojoj cemo smestiti sve administratore koje izcitamo iz baze
	 */
    private ArrayList<Administrator> lista;

    /**
     * ova metoda ce ispitati da li je apstraktni domenski objekat validan
     * @param ado apstraktni domenski objekat nad kojim vrsimo validaciju
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     * @throws java.lang.IllegalArgumentException ukoliko nije prosledjena instanca klase Administrator
     */
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    	if(ado==null) {
    		throw new NullPointerException("Prosledjeni objekat je null");
    	}
        if (!(ado instanceof Administrator)) {
            throw new IllegalArgumentException("Prosledjeni objekat nije instanca klase Administrator!");
        }
    }

    /**
     * ova metoda treba da izvrsi sistemsku operaciju izcitavanja svih adminstratora u bazi pozivanjem metode select naseg DBBrokera
     * @param ado apstraktni domenski objekat nad kojim se izvrsava SO
     * @throws java.lang.Exception ukoliko dodje do kreske prilikom izvrsavanja upita u bazi
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> administratori = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Administrator>) (ArrayList<?>) administratori;
    }

    
    /**
     * vraca nam atribut listu autora
     * @return lista autora
     */
    public ArrayList<Administrator> getLista() {
        return lista;
    }

}
