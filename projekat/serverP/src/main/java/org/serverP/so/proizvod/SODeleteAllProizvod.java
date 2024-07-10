/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.serverP.so.proizvod;


import java.util.ArrayList;

import org.serverP.db.DBBroker;
import org.serverP.so.AbstractSO;
import org.zajednickiP.domain.AbstractDomainObject;
import org.zajednickiP.domain.Kupac;
import org.zajednickiP.domain.Proizvod;

/**
 *   ovo klasa predstavlja sistemsku operaciju za brisanje svih proizvoda iz baze odjednom
 *  automatski brisu se i svi redovi iz tabele KancelarijskiProizvod,iz tabele Knjiga i iz tabele Autori
 * @author Lenovo
 */
public class SODeleteAllProizvod extends AbstractSO {

	
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
     * ova metoda treba da izvrsi sistemsku operaciju brisanja svih redova u tabeli proizvod u bazi pozivanjem delete metode naseg DBBrokera,
     * automatski ce se obrisati i svi redovi u tabelama Knjiga,KancelarijskiProizvod i Autori
     * @param ado apstraktni domenski objekat nad kojim se izvrsava SO
     * @throws java.lang.Exception ukoliko dodje do kreske prilikom izvrsavanja upita u bazi
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        
        DBBroker.getInstance().deleteAll(ado);
    }

}