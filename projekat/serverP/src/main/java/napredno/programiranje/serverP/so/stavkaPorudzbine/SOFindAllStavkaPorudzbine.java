/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napredno.programiranje.serverP.so.stavkaPorudzbine;


import java.util.ArrayList;

import napredno.programiranje.serverP.db.DBBroker;
import napredno.programiranje.serverP.so.AbstractSO;
import napredno.programiranje.zajednickiP.domain.AbstractDomainObject;
import napredno.programiranje.zajednickiP.domain.StavkaPorudzbine;


/**
 *  Ova klasa predstavlja sistemsku operaciju za izcitavanje svih stavki zeljene porudzbine
 * @author Djordje Djordjevic
 */
public class SOFindAllStavkaPorudzbine extends AbstractSO {

	/**
	 * lista sa stavkama
	 */
    private ArrayList<StavkaPorudzbine> lista;

    
    /**
     * ova metoda ce ispitati da li je apstraktni domenski objekat validan
     * @param ado apstraktni domenski objekat nad kojim vrsimo validaciju
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     * @throws java.lang.IllegalArgumentException ukoliko nije prosledjena instanca klase StavkaPorudzbina
     */
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    	if(ado==null) {
    		throw new NullPointerException("Prosledjeni objekat je null");
    	}
        if (!(ado instanceof StavkaPorudzbine)) {
            throw new IllegalArgumentException("Prosledjeni objekat nije instanca klase StavkaPorudzbina!");
        }
    }

    
    /**
     * ova metoda treba da izvrsi sistemsku operaciju pronalaska svih stavki zeljene porudzbine pozivanjem  find metode naseg DBBrokera
     * pretraga ce se izvrsiti po porudzbinaID-u
     * @param ado apstraktni domenski objekat nad kojim se izvrsava SO
     * @throws java.lang.Exception ukoliko dodje do kreske prilikom izvrsavanja upita u bazi
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> stavkePorudzbine = DBBroker.getInstance().find(ado);
        lista = (ArrayList<StavkaPorudzbine>) (ArrayList<?>) stavkePorudzbine;
    }

    /**
     * vraca trenutnu vrednost atributa lista
     * @return lista stavki
     */
    public ArrayList<StavkaPorudzbine> getLista() {
        return lista;
    }

}