/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.serverP.so.administrator;


import java.util.ArrayList;

import org.serverP.db.DBBroker;
import org.serverP.so.AbstractSO;
import org.zajednickiP.domain.AbstractDomainObject;
import org.zajednickiP.domain.Administrator;
import org.zajednickiP.domain.Kupac;


/**
 *
 * @author Lenovo
 */
public class SOGetAllAdministrator extends AbstractSO {

    private ArrayList<Administrator> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    	if(ado==null) {
    		throw new NullPointerException("Prosledjeni objekat je null");
    	}
        if (!(ado instanceof Administrator)) {
            throw new IllegalArgumentException("Prosledjeni objekat nije instanca klase Administrator!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> administratori = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Administrator>) (ArrayList<?>) administratori;
    }

    public ArrayList<Administrator> getLista() {
        return lista;
    }

}
