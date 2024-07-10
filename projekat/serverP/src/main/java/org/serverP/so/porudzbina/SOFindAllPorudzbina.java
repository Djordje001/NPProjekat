/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.serverP.so.porudzbina;


import java.util.ArrayList;

import org.serverP.db.DBBroker;
import org.serverP.so.AbstractSO;
import org.zajednickiP.domain.AbstractDomainObject;
import org.zajednickiP.domain.Porudzbina;


/**
 *   ova klasa predstavlja sistemsku operaciju za pronalazak svih porudzbina nekog kupca,pretrazivanje se vrsi po imenu i prezimenu kupca
 * @author Lenovo
 */
public class SOFindAllPorudzbina extends AbstractSO {

	/**
	 * lista porudzbina
	 */
    private ArrayList<Porudzbina> lista;

    
    /**
     * ova metoda ce ispitati da li je apstraktni domenski objekat validan
     * @param ado apstraktni domenski objekat nad kojim vrsimo validaciju
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     * @throws java.lang.IllegalArgumentException ukoliko nije prosledjena instanca klase Porudzbina
     */
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    	if(ado==null) {
    		throw new NullPointerException("Prosledjeni objekat je null");
    	}
        if (!(ado instanceof Porudzbina)) {
            throw new IllegalArgumentException("Prosledjeni objekat nije instanca klase Porudzbina!");
        }
    }

    
    /**
     * ova metoda treba da izvrsi sistemsku operaciju pronalaska svih porudzbina zeljenog kupca u bazi pozivanjem find metode naseg DBBrokera
     * pretraga ce se izvrsiti na osnovu imena i prezimena kupca
     * @param ado apstraktni domenski objekat nad kojim se izvrsava SO
     * @throws java.lang.Exception ukoliko dodje do kreske prilikom izvrsavanja upita u bazi
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> porudzbine = DBBroker.getInstance().find(ado);
        lista = (ArrayList<Porudzbina>) (ArrayList<?>) porudzbine;
    }

    
    /**
     * vraca trenutnu vrednost liste porudzbina
     * @return lista porudzbina
     */
    public ArrayList<Porudzbina> getLista() {
        return lista;
    }

}
