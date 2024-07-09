/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.serverP.so.stavkaPorudzbine;


import java.util.ArrayList;

import org.serverP.db.DBBroker;
import org.serverP.so.AbstractSO;
import org.zajednickiP.domain.AbstractDomainObject;
import org.zajednickiP.domain.StavkaPorudzbine;


/**
 *
 * @author Lenovo
 */
public class SOFindAllStavkaPorudzbine extends AbstractSO {

    private ArrayList<StavkaPorudzbine> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    	if(ado==null) {
    		throw new NullPointerException("Prosledjeni objekat je null");
    	}
        if (!(ado instanceof StavkaPorudzbine)) {
            throw new IllegalArgumentException("Prosledjeni objekat nije instanca klase StavkaPorudzbine!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> stavkePorudzbine = DBBroker.getInstance().find(ado);
        lista = (ArrayList<StavkaPorudzbine>) (ArrayList<?>) stavkePorudzbine;
    }

    public ArrayList<StavkaPorudzbine> getLista() {
        return lista;
    }

}