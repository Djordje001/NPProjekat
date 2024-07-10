/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zajednickiP.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * predstavlja konkretnog pisca sa atributima pisacID,ime,prezime,email
 * @author Lenovo
 */
public class Pisac extends AbstractDomainObject {

	/**
	 * id pisca kao LOng
	 */
    private Long pisacID;
    /**
     * ime Pisca kao String
     */
    private String ime;
    /**
     * prezime Pisca kao String
     */
    private String prezime;
    
    /**
     * email Pisca kao String
     */
    private String email;

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    /**
     * postavlja sve atribute na zeljene vrednost
     * @param pisacID id pisca kao long
     * @param ime pisca kao String
     * @param prezime pisca kao String
     * @param email pisca kao String
     * @throws java.lang.NullPointerException ukoliko se prosledi null vrednost za ime,prezime ili email
     * @throws java.lang.IllegalArgumentException ukoliko se prosledi prazan string za ime,prezime ili email
     */
    public Pisac(Long pisacID, String ime, String prezime, String email) {
        
        setPisacID(pisacID);
        
        setIme(ime);
      
        setPrezime(prezime);
 
        setEmail(email);
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

    /**
     * vraca trenutnu vrednost atributa pisacID
     * @return pisacID pisca kao Long
     */
    public Long getPisacID() {
        return pisacID;
    }

    /**
     * postavlja atribut pisacID na zeljenu vrednost
     * @param pisacID pisca kao Long
     */
    public void setPisacID(Long pisacID) {
        this.pisacID = pisacID;
    }

    /**
     * vraca trenutnu vrednost atributa ime 
     * @return ime pisca kao String
     */
    public String getIme() {
        return ime;
    }

    /**
     * postavlja vrednost atributa ime na zeljenu vrednost
     * @param ime pisca kao String
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     * @throws java.lang.IllegalArgumentException ukoliko je prosledjen prazan string
     */
    public void setIme(String ime) {
    	if(ime==null) {
    		throw new NullPointerException("ime ne sme biti null");
    	}
    	if(ime.equals("")) {
    		throw new IllegalArgumentException("ime ne sme biti prazan string");
    	}
        this.ime = ime;
    }

   /**
    * vraca trenutnu vrednost atributa prezime konkretnog pisca
    * @return prezime pisca kao String
    */
    public String getPrezime() {
        return prezime;
    }
    
    /**
     * postavlja atribut prezime na zeljenu vrednost
     * @param prezime pisca kao String
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     * @throws java.lang.IllegalArgumentException ukoliko je prosledjen prazan string
     */
    public void setPrezime(String prezime) {
    	if(prezime==null) {
    		throw new NullPointerException("prezime ne sme biti null");
    	}
    	if(prezime.equals("")) {
    		throw new IllegalArgumentException("prezime ne sme biti prazan string");
    	}
        this.prezime = prezime;
    }

    
    /**
     * vraca trenutnu vrednost atributa email
     * @return email pisca kao String
     */
    public String getEmail() {
        return email;
    }

    
    /**
     * postavlja email na zeljenu vrednost
     * @param email pisca kao String
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     * @throws java.lang.IllegalArgumentException ukoliko je prosledjen prazan string
     */
    public void setEmail(String email) {
    	if(email==null) {
    		throw new NullPointerException("email ne sme biti null");
    	}
    	if(email.equals("")) {
    		throw new IllegalArgumentException("email ne sme biti prazan string");
    	}
        this.email = email;
    }

	
    /**
     * poredi dva pisca po svim atributima
     * @param obj Pisac sa kojim se poredi
     * @return true -ukoliko imaju sve jednake atribute
     * @return false - u svim suprotnim situacijama
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pisac other = (Pisac) obj;
		return Objects.equals(email, other.email) && Objects.equals(ime, other.ime)
				&& Objects.equals(pisacID, other.pisacID) && Objects.equals(prezime, other.prezime);
	}
    
    
    

}