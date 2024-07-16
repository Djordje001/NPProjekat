/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napredno.programiranje.serverP.so.pisac;


import java.util.ArrayList;

import napredno.programiranje.serverP.db.DBBroker;
import napredno.programiranje.serverP.so.AbstractSO;
import napredno.programiranje.zajednickiP.domain.AbstractDomainObject;
import napredno.programiranje.zajednickiP.domain.Pisac;


/**
 * predstavlja sistemsku operaciju za izcitavanje svih pisaca iz baze
 * @author Djordje Djordjevic
 */
public class SOGetAllPisac extends AbstractSO {
	/**
	 * lista sa piscima
	 */
    private ArrayList<Pisac> lista;

    /**
     * ova metoda ce ispitati da li je apstraktni domenski objekat validan
     * @param ado apstraktni domenski objekat nad kojim vrsimo validaciju
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     * @throws java.lang.IllegalArgumentException ukoliko nije prosledjena instanca klase Pisac
     */
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    	if(ado==null) {
    		throw new NullPointerException("Prosledjeni objekat je null");
    	}
        if (!(ado instanceof Pisac)) {
            throw new IllegalArgumentException("Prosledjeni objekat nije instanca klase Pisac!");
        }
    }
    
    /**
     * ova metoda treba da izvrsi sistemsku operaciju izcitavanja svih pisaca u bazi pozivanjem metode select naseg DBBrokera
     * @param ado apstraktni domenski objekat nad kojim se izvrsava SO
     * @throws java.lang.Exception ukoliko dodje do kreske prilikom izvrsavanja upita u bazi
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> pisci = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Pisac>) (ArrayList<?>) pisci;
    }

    
    /**
     * vracanje trenutne vrednost liste pisaca
     * @return lista pisaca
     */
    public ArrayList<Pisac> getLista() {
        return lista;
    }

}
