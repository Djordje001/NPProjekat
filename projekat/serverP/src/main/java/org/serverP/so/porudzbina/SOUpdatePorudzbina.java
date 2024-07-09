/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.serverP.so.porudzbina;


import java.util.Date;

import org.serverP.App;
import org.serverP.db.DBBroker;
import org.serverP.so.AbstractSO;
import org.zajednickiP.domain.AbstractDomainObject;
import org.zajednickiP.domain.Porudzbina;
import org.zajednickiP.domain.StavkaPorudzbine;


/**
 *
 * @author Lenovo
 */
public class SOUpdatePorudzbina extends AbstractSO {

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

        
        DBBroker.getInstance().update(ado);

        Porudzbina p = (Porudzbina) ado;
        
        DBBroker.getInstance().delete(p.getStavkePorudzbine().get(0));

        
        for (StavkaPorudzbine stavkaPorudzbine : p.getStavkePorudzbine()) {
            DBBroker.getInstance().insert(stavkaPorudzbine);
        }
        
        
        

    }

}