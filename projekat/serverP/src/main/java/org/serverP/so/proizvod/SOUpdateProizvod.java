/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.serverP.so.proizvod;


import java.util.ArrayList;
import java.util.Date;

import org.serverP.db.DBBroker;
import org.serverP.so.AbstractSO;
import org.zajednickiP.domain.AbstractDomainObject;
import org.zajednickiP.domain.Autor;
import org.zajednickiP.domain.KancelarijskiProizvod;
import org.zajednickiP.domain.Knjiga;
import org.zajednickiP.domain.Pisac;
import org.zajednickiP.domain.Porudzbina;
import org.zajednickiP.domain.Proizvod;


/**
 * ova klasa predstavlja sistemsku operaciju za azuriranje proizvoda
 * azurira se i jedan red u tabeli Knjiga ili KancelarijskiProizvod u zavinosti od tipa,
 * ukoliko se azruira red u tabeli knjige  prvo ce se izbrisati svi prethodni autori knjige i onda ce se dodati novi(ako postoje)
 * @author Lenovo
 */
public class SOUpdateProizvod extends AbstractSO {

	
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
     * ova metoda treba da izvrsi sistemsku operaciju azuriranja proizvoda u bazi ,
     * azurirace se i jedan red u tabeli KancelarijskiProizvod ili Knjiga u zavisnosti od tipa,
     * ukoliko se azurira red u tabeli Knjiga prvo ce se izbrisati svi prethodni autori knjige i onda ce se dodati novi
     * @param ado apstraktni domenski objekat nad kojim se izvrsava SO
     * @throws java.lang.Exception ukoliko dodje do kreske prilikom izvrsavanja upita u bazi
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        Proizvod p=(Proizvod)ado;
        Proizvod x=new Proizvod(p.getProizvodID(), p.getCena(),p.getNaziv(), p.getTip());
        
        DBBroker.getInstance().update(x);
        if(p.getTip()==1) {
        	KancelarijskiProizvod kp=(KancelarijskiProizvod)ado;
        	DBBroker.getInstance().update(kp);
        }
        if(p.getTip()==2)
        {
        	Knjiga k=(Knjiga)ado;
        	DBBroker.getInstance().update(k);
        	
            //Autor a=new Autor();
            Autor a=new Autor(k, 0, new Pisac(null, "nista","nista", "nista"));
           // a.setKnjiga(k);
        	DBBroker.getInstance().delete(a);
        	
        	ArrayList<Autor> autori=k.getAutori();
        	for(Autor autor : autori) {
        		autor.setKnjiga(k);
        		DBBroker.getInstance().insert(autor);
        	}
        }
       
        
        

    }

}
