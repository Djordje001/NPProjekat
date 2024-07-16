/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.serverP.so.proizvod;

import org.serverP.db.DBBroker;
import org.serverP.so.AbstractSO;
import org.zajednickiP.domain.AbstractDomainObject;
import org.zajednickiP.domain.Porudzbina;
import org.zajednickiP.domain.Proizvod;

/**
 * ova klasa predstavlja sistemsku operaciju za brisanje zeljenog proizvoda
 * izbrisace se jedan red u tabeli proizvod i jedan red u tabeli KancelarijskiProizvid ili Knjiga u zavinosti od tipa 
 * takodje ukoliko se brise red iz tabele knjiga izbrisace se i svi redovi u tabeli Autor koji odgovoaraju toj knjizi
 * @author Djordje Djordjevic
 */
public class SODeleteProizvod extends AbstractSO {

	
	/**
     * ova metoda ce ispitati da li je apstraktni domenski objekat validan
     * @param ado apstraktni domenski objekat nad kojim vrsimo validaciju
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     * @throws java.lang.IllegalArgumentException ukoliko nije prosledjena instanca klase Proizvod
     */
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    	if(ado==null) {
    		throw new NullPointerException("Prosledjeni objekat je null");
    	}
        if (!(ado instanceof Proizvod)) {
            throw new IllegalArgumentException("Prosledjeni objekat nije instanca klase Proizvod!");
        }
    }

    
    /**
     * ova metoda treba da izvrsi sistemsku operaciju brisanja zeljenog proizvoda u bazi pozivanjem delete metode naseg DBBrokera,
     * automatski ce se obrisati red u tabeli Knjiga ili KancelarijskiProizvod u zavinosti od tipa ,ukoliko se brise knjiga obrisace se i svi redovi u tabeli autor koji odgovaraju toj knjizi
     * @param ado apstraktni domenski objekat nad kojim se izvrsava SO
     * @throws java.lang.Exception ukoliko dodje do kreske prilikom izvrsavanja upita u bazi
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
    	Proizvod p=(Proizvod)ado;
    	Proizvod x=new Proizvod(p.getProizvodID(), 2, "nista", 1);
    	//x.setProizvodID(p.getProizvodID());
        DBBroker.getInstance().delete(x);
    }

}