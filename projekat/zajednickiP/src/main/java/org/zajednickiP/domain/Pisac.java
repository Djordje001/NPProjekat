/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zajednickiP.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class Pisac extends AbstractDomainObject {

    private Long pisacID;
    private String ime;
    private String prezime;
    private String email;

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    public Pisac(Long pisacID, String ime, String prezime, String email) {
        
        setPisacID(pisacID);
        
        setIme(ime);
      
        setPrezime(prezime);
 
        setEmail(email);
    }

    public Pisac() {
    }

    @Override
    public String nazivTabele() {
        return " Pisac ";
    }

    @Override
    public String alijas() {
        return " p ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<AbstractDomainObject>();

        while (rs.next()) {
            Pisac p = new Pisac(rs.getLong("PisacID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Email"));

            lista.add(p);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String uslov() {
        return " PisacID = " + pisacID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public Long getPisacID() {
        return pisacID;
    }

    public void setPisacID(Long pisacID) {
        this.pisacID = pisacID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
    	if(ime==null) {
    		throw new NullPointerException("ime ne sme biti null");
    	}
    	if(ime.equals("")) {
    		throw new IllegalArgumentException("ime ne sme biti prazan string");
    	}
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
    	if(prezime==null) {
    		throw new NullPointerException("prezime ne sme biti null");
    	}
    	if(prezime.equals("")) {
    		throw new IllegalArgumentException("prezime ne sme biti prazan string");
    	}
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
    	if(email==null) {
    		throw new NullPointerException("email ne sme biti null");
    	}
    	if(email.equals("")) {
    		throw new IllegalArgumentException("email ne sme biti prazan string");
    	}
        this.email = email;
    }

}