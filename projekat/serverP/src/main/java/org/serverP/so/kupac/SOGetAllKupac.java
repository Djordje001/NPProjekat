/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.serverP.so.kupac;


import java.util.ArrayList;

import org.serverP.db.DBBroker;
import org.serverP.so.AbstractSO;
import org.zajednickiP.domain.AbstractDomainObject;
import org.zajednickiP.domain.Kupac;


/**
 *
 * @author Lenovo
 */
public class SOGetAllKupac extends AbstractSO {

    private ArrayList<Kupac> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    	if(ado==null) {
    		throw new NullPointerException("Prosledjeni objekat je null");
    	}
        if (!(ado instanceof Kupac)) {
            throw new IllegalArgumentException("Prosledjeni objekat nije instanca klase Kupac!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> kupci = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Kupac>) (ArrayList<?>) kupci;
    }

    public ArrayList<Kupac> getLista() {
        return lista;
    }

}