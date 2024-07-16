/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napredno.programiranje.serverP.so.porudzbina;


import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import napredno.programiranje.serverP.App;
import napredno.programiranje.serverP.db.DBBroker;
import napredno.programiranje.serverP.so.AbstractSO;
import napredno.programiranje.zajednickiP.domain.AbstractDomainObject;
import napredno.programiranje.zajednickiP.domain.Autor;
import napredno.programiranje.zajednickiP.domain.Knjiga;
import napredno.programiranje.zajednickiP.domain.Porudzbina;
import napredno.programiranje.zajednickiP.domain.Proizvod;
import napredno.programiranje.zajednickiP.domain.StavkaPorudzbine;


/**
 * ova klasa predstavlja sistemsku operaciju za dodavanje porudzbine u bazu i svih njenih stavki
 * @author Djordje Djordjevic
 */
public class SOAddPorudzbina extends AbstractSO {

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
     * ova metoda treba da izvrsi sistemsku operaciju dodavanja porudzbine u bazu pozivanjem insert metode naseg DBBrokera,
     * takodje i za svaku stavkuPorudzbine pozivace se metoda insert naseg dbBrokera 
     * @param ado apstraktni domenski objekat nad kojim se izvrsava SO
     * @throws java.lang.Exception ukoliko dodje do kreske prilikom izvrsavanja upita u bazi
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        
        
        PreparedStatement ps = DBBroker.getInstance().insert(ado);
        
        
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long porudzbinaID = tableKeys.getLong(1);
        
        
        Porudzbina p = (Porudzbina) ado;
        p.setPorudzbinaID(porudzbinaID);
        
        
        for (StavkaPorudzbine stavkaPorudzbine : p.getStavkePorudzbine()) {
            stavkaPorudzbine.setPorudzbina(p);
           // DBBroker.getInstance().insert(stavkaPorudzbine);
            
            
            
           PreparedStatement w=DBBroker.getInstance().insert(stavkaPorudzbine);
        	
        	ResultSet u = w.getGeneratedKeys();
            u.next();
            long rb = u.getLong(1);
            
            
            
		     int x=(int) rb;
            stavkaPorudzbine.setRb(x);
        }
        
         
        
        App.serijalizujPromenu();

        
        
        
    }

}
