/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.serverP.so.pisac;


import java.util.ArrayList;

import org.serverP.db.DBBroker;
import org.serverP.so.AbstractSO;
import org.zajednickiP.domain.AbstractDomainObject;
import org.zajednickiP.domain.Pisac;


/**
 *
 * @author Lenovo
 */
public class SOGetAllPisac extends AbstractSO {

    private ArrayList<Pisac> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    	if(ado==null) {
    		throw new NullPointerException("Prosledjeni objekat je null");
    	}
        if (!(ado instanceof Pisac)) {
            throw new IllegalArgumentException("Prosledjeni objekat nije instanca klase Pisac!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> pisci = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Pisac>) (ArrayList<?>) pisci;
    }

    public ArrayList<Pisac> getLista() {
        return lista;
    }

}
