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
 * predstavlja konkretnog kupca sa atributima kupacID,ime,prezime,email,tipKupca
 * @author Lenovo
 */
public class Kupac extends AbstractDomainObject {
    /**
     * id kupca kao Long
     */
    private Long kupacID;
    
    /**
     * ime kupca kao String
     */
    private String ime;
    
    /**
     * prezime kupca kao String
     */
    private String prezime;
    
    /**
     * email kupca kao String
     */
    private String email;
    
    /**
     * tipKupca kao String
     */
    private String tipKupca;

    @Override
    public String toString() {
        return ime + " " + prezime + " (Tip: " + tipKupca + ")";
    }

    /**
     * postavlja atribute Kupca na zeljene vrednost
     * @param kupacID id kupca kao long
     * @param ime kupca kao String
     * @param prezime kupca kao String
     * @param email kupca kao String
     * @param tipKupca kupca kao String
     * @throws java.lang.NullPointerException ukoliko se prosledi null vrednost za ime ili prezime ili email ili tip Kucpca
     * @throws java.lang.IllegalArgumentException ukoliko se prosledi prazan string za ime,prezime,email ili tipKupca
     */
    public Kupac(Long kupacID, String ime, String prezime, String email, String tipKupca) {
       
        setKupacID(kupacID);
        
        setIme(ime);
       
        setPrezime(prezime);
       
        setEmail(email);
        
        setTipKupca(tipKupca);
       
    }

    
    @Override
    public String nazivTabele() {
        return " Kupac ";
    }

    @Override
    public String alijas() {
        return " k ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<AbstractDomainObject>();

        while (rs.next()) {
            Kupac k = new Kupac(rs.getLong("KupacID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("email"), rs.getString("tipKupca"));

            lista.add(k);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, email, tipKupca) ";
    }

    @Override
    public String uslov() {
        return " KupacID = " + kupacID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', "
                + "'" + email + "', '" + tipKupca + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Ime = '" + ime + "', Prezime = '" + prezime + "', "
                + "email = '" + email + "', tipKupca = '" + tipKupca + "' ";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    
    /**
     * vraca trenutnu vrednost atributa kupacID
     * @return kupacID kao Long
     */
    public Long getKupacID() {
        return kupacID;
    }

    /**
     * postavlja vrednost atributa kupacID na zeljenu vrednost
     * @param kupacID kao Long
     */
    public void setKupacID(Long kupacID) {
        this.kupacID = kupacID;
    }

    /**
     * vraca trenutnu vrednost atributa ime kupca
     * @return ime kupca kao String
     */
    public String getIme() {
        return ime;
    }

    /**
     * postavlja vrednost atributa ime na zeljenu vrednost
     * @param ime kupca kao String
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
     * vraca trenutnu vrednost atributa prezime
     * @return prezime kupca kao string
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * postavlja atribut prezime na zeljenu vrednost
     * @param prezime kupca kao String
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
     * vraca trenutnu vrednost atributa email kupca
     * @return email kupca kao String
     */
    public String getEmail() {
        return email;
    }

    /**
     * postavlja email kupca na zeljenu vrednost
     * @param email kupca kao String
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     * @throws java.lang.IllegalArgumentException ukoliko je prosledjen prazan string
     */
    public void setEmail(String email) {
    	if(email==null) {
    		throw new NullPointerException("email ne sme biti null");
    	}
    	if(email.equals("")) {
    		throw new IllegalArgumentException("email ne sme biti prazna string");
    	}
        this.email = email;
    }

    /**
     * vraca trenutnu vrednost atributa tip kupca
     * @return tipKupca kao String
     */
    public String getTipKupca() {
        return tipKupca;
    }

    /**
     * postavlja atribut tipKupca na zeljenu vrednost
     * @param tipKupca kao String
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     * @throws java.lang.IllegalArgumentException ukoliko je prosledjen prazan string
     */
    public void setTipKupca(String tipKupca) {
    	if(tipKupca==null) {
    		throw new NullPointerException("tipKupca ne sme biti null");
    	}
    	if(ime.equals("")) {
    		throw new IllegalArgumentException("tipKupca ne sme biti prazna string");
    	}
        this.tipKupca = tipKupca;
    }

	

    /**
     * poredi dva kupca po svim atributima
     * @param obj Kupac sa kojim se poredi
     * @return true - ukoliko drugi kupac ima sve iste atribute
     * @return false - u svim ostalim situacijama
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kupac other = (Kupac) obj;
		return Objects.equals(email, other.email) && Objects.equals(ime, other.ime)
				&& Objects.equals(kupacID, other.kupacID) && Objects.equals(prezime, other.prezime)
				&& Objects.equals(tipKupca, other.tipKupca);
	}
    
    
    
}