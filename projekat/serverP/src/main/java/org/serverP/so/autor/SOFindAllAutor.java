/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.serverP.so.autor;


import java.util.ArrayList;

import org.serverP.db.DBBroker;
import org.serverP.so.AbstractSO;
import org.zajednickiP.domain.AbstractDomainObject;
import org.zajednickiP.domain.Administrator;
import org.zajednickiP.domain.Autor;
import org.zajednickiP.domain.Porudzbina;


/**
 * ova klasa predstavlja sistemsku operaciju za pronalazak svih autora neke knjige
 * @author Lenovo
 */
public class SOFindAllAutor extends AbstractSO {

	/**
	 * lista autora
	 */
    private ArrayList<Autor> lista;

    
    /**
     * ova metoda ce ispitati da li je apstraktni domenski objekat validan
     * @param ado apstraktni domenski objekat nad kojim vrsimo validaciju
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     * @throws java.lang.IllegalArgumentException ukoliko nije prosledjena instanca klase Autor
     */
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    	
    	if(ado==null) {
    		throw new NullPointerException("Prosledjeni objekat je null");
    	}
        if (!(ado instanceof Autor)) {
            throw new IllegalArgumentException("Prosledjeni objekat nije instanca klase Autor!");
        }
      
    }

    /**
     * ova metoda treba da izvrsi sistemsku operaciju izcitavanja svih autora neke knjige u bazi pozivanjem metode find naseg DBBrokera
     * @param ado apstraktni domenski objekat nad kojim se izvrsava SO
     * @throws java.lang.Exception ukoliko dodje do kreske prilikom izvrsavanja upita u bazi
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> autori = DBBroker.getInstance().find(ado);
        lista = (ArrayList<Autor>) (ArrayList<?>) autori;
    }

    
    /**
     * vraca nam atribut listu sa autorima
     * @return lista autora
     */
    public ArrayList<Autor> getLista() {
        return lista;
    }

}