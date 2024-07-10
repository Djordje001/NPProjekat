/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.serverP.so.login;


import java.util.ArrayList;

import org.serverP.db.DBBroker;
import org.serverP.so.AbstractSO;
import org.zajednickiP.domain.AbstractDomainObject;
import org.zajednickiP.domain.Administrator;
import org.zajednickiP.domain.Kupac;


/**
 * ova klasa predstavlja sistemsku operaciju za login administratora na nas sistem
 * @author Lenovo
 */
public class SOLogin extends AbstractSO {
    /**
     * ulogovani administrator
     */
    Administrator ulogovani;

    
    /**
     * ova metoda ce ispitati da li je apstraktni domenski objekat validan
     * @param ado apstraktni domenski objekat nad kojim vrsimo validaciju
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     * @throws java.lang.IllegalArgumentException ukoliko nije prosledjena instanca klase Administrator
     */
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    	if(ado==null) {
    		throw new NullPointerException("Prosledjeni objekat je null");
    	}
        if (!(ado instanceof Administrator)) {
            throw new IllegalArgumentException("Prosledjeni objekat nije instanca klase Administrator!");
        }
    }

    
    /**
     * ova metoda treba da izvrsi sistemsku operaciju logovanja administratora u bazu pozivanjem select metode naseg DBBrokera kako bi videli da li postoji administrator sa takvim kredencijalima
     * ukoliko postoji vraticemo njega ukoliko ne postoji bacamo IllegalArgumentException
     * @param ado apstraktni domenski objekat nad kojim se izvrsava SO
     * @throws java.lang.Exception ukoliko dodje do kreske prilikom izvrsavanja upita u bazi
     * @throws java.lang.IllegalArgumentException ukoliko ne postoji administrator sa unetim kredencijalima
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        Administrator a = (Administrator) ado;

        ArrayList<Administrator> administratori
                = (ArrayList<Administrator>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Administrator administrator : administratori) {
            if (administrator.getUsername().equals(a.getUsername())
                    && administrator.getPassword().equals(a.getPassword())) {
                ulogovani = administrator;
                return;
            }
        }

        throw new IllegalArgumentException("Ne postoji administrator sa tim kredencijalima.");
        
    }

    /**
     * vraca trenutnu vrednost atributa ulogovani
     * @return ulogovani administrator
     */
    public Administrator getUlogovani() {
        return ulogovani;
    }
    
    

}
