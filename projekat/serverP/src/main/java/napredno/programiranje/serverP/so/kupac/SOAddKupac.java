/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napredno.programiranje.serverP.so.kupac;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import napredno.programiranje.serverP.db.DBBroker;
import napredno.programiranje.serverP.so.AbstractSO;
import napredno.programiranje.zajednickiP.domain.AbstractDomainObject;
import napredno.programiranje.zajednickiP.domain.Kupac;

/**
 * ova klasa predstavlja sismtesku operaciju za dodavanje novog kupca
 * @author Djordje Djordjevic
 */
public class SOAddKupac extends AbstractSO {

	/**
     * ova metoda ce ispitati da li je apstraktni domenski objekat validan
     * @param ado apstraktni domenski objekat nad kojim vrsimo validaciju
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     * @throws java.lang.IllegalArgumentException ukoliko nije prosledjena instanca klase Kupac ili ako u bazi postoji vec kupac sa zeljenim mejlom
     */
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    	
    	if(ado==null) {
    		throw new NullPointerException("Prosledjeni objekat je null");
    	}
        if (!(ado instanceof Kupac)) {
            throw new IllegalArgumentException("Prosledjeni objekat nije instanca klase Kupac!");
        }

        Kupac k = (Kupac) ado;

        ArrayList<Kupac> kupci = (ArrayList<Kupac>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Kupac kupac : kupci) {
            if (kupac.getEmail().equals(k.getEmail())) {
                throw new IllegalArgumentException("Kupac sa tim emailom vec postoji!");
            }
        }

    }

    /**
     * ova metoda treba da izvrsi sistemsku operaciju dodavanja kupca u bazu pozivanjem insert metode naseg DBBrokera
     * @param ado apstraktni domenski objekat nad kojim se izvrsava SO
     * @throws java.lang.Exception ukoliko dodje do kreske prilikom izvrsavanja upita u bazi
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        //DBBroker.getInstance().insert(ado);
        
        PreparedStatement ps=DBBroker.getInstance().insert(ado);
        ResultSet tableKeys=ps.getGeneratedKeys();
        tableKeys.next();
        Long kupacID=tableKeys.getLong(1);
        Kupac kupac=(Kupac) ado;
        kupac.setKupacID(kupacID);
        
    }

}