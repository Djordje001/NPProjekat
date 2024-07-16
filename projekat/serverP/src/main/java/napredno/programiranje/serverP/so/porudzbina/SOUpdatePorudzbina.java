/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napredno.programiranje.serverP.so.porudzbina;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import napredno.programiranje.serverP.App;
import napredno.programiranje.serverP.db.DBBroker;
import napredno.programiranje.serverP.so.AbstractSO;
import napredno.programiranje.zajednickiP.domain.AbstractDomainObject;
import napredno.programiranje.zajednickiP.domain.Porudzbina;
import napredno.programiranje.zajednickiP.domain.StavkaPorudzbine;


/**
 *  ova klasa prestavlja sistemsku operaciju za azuriranje zeljene porudzbine i njenih stavki
 * @author Djordje Djordjevic
 */
public class SOUpdatePorudzbina extends AbstractSO {

	
	/**
     * ova metoda ce ispitati da li je apstraktni domenski objekat validan
     * @param ado apstraktni domenski objekat nad kojim vrsimo validaciju
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     * @throws java.lang.IllegalArgumentException ukoliko nije prosledjena instanca klase Porudzbina ili ako porudzbina nema nijednu stavku ili ako porudzbina ima datumIsporuke u proslosti
     */
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    	if(ado==null) {
    		throw new NullPointerException("Prosledjeni objekat je null");
    	}
        if (!(ado instanceof Porudzbina)) {
            throw new IllegalArgumentException("Prosledjeni objekat nije instanca klase Porudzbina!");
        }
        
        Porudzbina p = (Porudzbina) ado;
        if (p.getStavkePorudzbine().isEmpty()) {
            throw new IllegalArgumentException("Porudzbina mora imati barem jednu stavku!");
        }

        if (p.getDatumIsporuke().before(new Date())) {
            throw new IllegalArgumentException("Porudzbina mora imati datumIsporuke u buducnosti!");
        }
       

    }

    
    /**
     * ova metoda treba da izvrsi sistemsku operaciju azuriranja porudzbine u bazi pozivanjem update metode naseg DBBrokera
     * ova metoda ce takodje prvo izbrisati sve prethodne stavke ove porudzbine pozivanjem metode delete DBBrokera i onda ce naknadno svaku novu dodati pomocu insert metode DBBrokera
     * @param ado apstraktni domenski objekat nad kojim se izvrsava SO
     * @throws java.lang.Exception ukoliko dodje do kreske prilikom izvrsavanja upita u bazi
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        
        DBBroker.getInstance().update(ado);
        
        Porudzbina p = (Porudzbina) ado;
        System.out.println(p.getStavkePorudzbine().get(0).getPorudzbina());
        DBBroker.getInstance().delete(p.getStavkePorudzbine().get(0));

        
        for (StavkaPorudzbine stavkaPorudzbine : p.getStavkePorudzbine()) {
        	PreparedStatement ps=DBBroker.getInstance().insert(stavkaPorudzbine);
        	
        	ResultSet tableKeys = ps.getGeneratedKeys();
            tableKeys.next();
            long rb = tableKeys.getLong(1);
            
            
            
		     int x=(int) rb;
            stavkaPorudzbine.setRb(x);
        }
        
        
        App.serijalizujPromenu();

    }

}