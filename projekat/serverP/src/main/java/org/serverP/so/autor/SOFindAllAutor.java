/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.serverP.so.autor;


import java.util.ArrayList;

import org.serverP.db.DBBroker;
import org.serverP.so.AbstractSO;
import org.zajednickiP.domain.AbstractDomainObject;
import org.zajednickiP.domain.Administrator;
import org.zajednickiP.domain.Autor;
import org.zajednickiP.domain.Porudzbina;


/**
 *
 * @author Lenovo
 */
public class SOFindAllAutor extends AbstractSO {

    private ArrayList<Autor> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    	
    	if(ado==null) {
    		throw new NullPointerException("Prosledjeni objekat je null");
    	}
        if (!(ado instanceof Autor)) {
            throw new IllegalArgumentException("Prosledjeni objekat nije instanca klase Autor!");
        }
      
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> autori = DBBroker.getInstance().find(ado);
        lista = (ArrayList<Autor>) (ArrayList<?>) autori;
    }

    public ArrayList<Autor> getLista() {
        return lista;
    }

}