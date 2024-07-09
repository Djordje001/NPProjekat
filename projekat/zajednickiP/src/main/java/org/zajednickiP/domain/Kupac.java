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
 *
 * @author Lenovo
 */
public class Kupac extends AbstractDomainObject {
    
    private Long kupacID;
    private String ime;
    private String prezime;
    private String email;
    private String tipKupca;

    @Override
    public String toString() {
        return ime + " " + prezime + " (Tip: " + tipKupca + ")";
    }

    public Kupac(Long kupacID, String ime, String prezime, String email, String tipKupca) {
       
        setKupacID(kupacID);
        
        setIme(ime);
       
        setIme(prezime);
       
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

    public Long getKupacID() {
        return kupacID;
    }

    public void setKupacID(Long kupacID) {
        this.kupacID = kupacID;
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
    		throw new IllegalArgumentException("email ne sme biti prazna string");
    	}
        this.email = email;
    }

    public String getTipKupca() {
        return tipKupca;
    }

    public void setTipKupca(String tipKupca) {
    	if(tipKupca==null) {
    		throw new NullPointerException("tipKupca ne sme biti null");
    	}
    	if(ime.equals("")) {
    		throw new IllegalArgumentException("tipKupca ne sme biti prazna string");
    	}
        this.tipKupca = tipKupca;
    }

	

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