/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.serverP.so.proizvod;

import org.serverP.db.DBBroker;
import org.serverP.so.AbstractSO;
import org.zajednickiP.domain.AbstractDomainObject;
import org.zajednickiP.domain.Porudzbina;
import org.zajednickiP.domain.Proizvod;

/**
 *
 * @author Lenovo
 */
public class SODeleteProizvod extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    	if(ado==null) {
    		throw new NullPointerException("Prosledjeni objekat je null");
    	}
        if (!(ado instanceof Proizvod)) {
            throw new IllegalArgumentException("Prosledjeni objekat nije instanca klase Proizvod!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
    	Proizvod p=(Proizvod)ado;
    	Proizvod x=new Proizvod(p.getProizvodID(), 2, "nista", 1);
    	//x.setProizvodID(p.getProizvodID());
        DBBroker.getInstance().delete(x);
    }

}