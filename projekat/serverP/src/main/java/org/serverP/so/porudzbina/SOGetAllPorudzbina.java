/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.serverP.so.porudzbina;


import java.util.ArrayList;

import org.serverP.db.DBBroker;
import org.serverP.so.AbstractSO;
import org.zajednickiP.domain.AbstractDomainObject;
import org.zajednickiP.domain.Porudzbina;


/**
 *
 * @author Lenovo
 */
public class SOGetAllPorudzbina extends AbstractSO {

    private ArrayList<Porudzbina> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    	if(ado==null) {
    		throw new NullPointerException("Prosledjeni objekat je null");
    	}
        if (!(ado instanceof Porudzbina)) {
            throw new IllegalArgumentException("Prosledjeni objekat nije instanca klase Porudzbina!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> porudzbine = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Porudzbina>) (ArrayList<?>) porudzbine;
    }

    public ArrayList<Porudzbina> getLista() {
        return lista;
    }

}
