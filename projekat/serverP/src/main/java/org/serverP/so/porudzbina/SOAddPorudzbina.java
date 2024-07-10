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
 *
 * @author Lenovo
 */
public class SOAddPorudzbina extends AbstractSO {

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

       /* Porudzbina p = (Porudzbina) ado;

        if (!p.getDatumIsporuke().after(new Date())) {
            throw new Exception("Datum isporuke mora biti u buducnosti!");
        }

        if (p.getStavkePorudzbine().isEmpty()) {
            throw new Exception("Porudzbina mora imati barem jednu stavku!");
        }*/

    }

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
