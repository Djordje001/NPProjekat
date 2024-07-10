/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.serverP.so.porudzbina;


import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import org.serverP.App;
import org.serverP.db.DBBroker;
import org.serverP.so.AbstractSO;
import org.zajednickiP.domain.AbstractDomainObject;
import org.zajednickiP.domain.Autor;
import org.zajednickiP.domain.Knjiga;
import org.zajednickiP.domain.Porudzbina;
import org.zajednickiP.domain.Proizvod;
import org.zajednickiP.domain.StavkaPorudzbine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


/**
 * ova klasa predstavlja sistemsku operaciju za dodavanje porudzbine u bazu i svih njenih stavki
 * @author Lenovo
 */
public class SOAddPorudzbina extends AbstractSO {

	/**
     * ova metoda ce ispitati da li je apstraktni domenski objekat validan
     * @param ado apstraktni domenski objekat nad kojim vrsimo validaciju
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     * @throws java.lang.IllegalArgumentException ukoliko nije prosledjena instanca klase Porudzbina ili ako porudzbina nema nijednu stavku
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
            DBBroker.getInstance().insert(stavkaPorudzbine);
        }
        
         
        
        App.serijalizujPromenu();

        
        
        
    }

}
