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
public class Administrator extends AbstractDomainObject {

    private Long administratorID;
    private String ime;
    private String prezime;
    private String username;
    private String password;

    

    public Administrator(Long administratorID, String ime, String prezime, String username, String password) {
       
        setAdministratorID(administratorID);
        
        setIme(ime);
        
        setPrezime(prezime);
       
        setUsername(username);
      
        setPassword(password);
    }

    public Long getAdministratorID() {
        return administratorID;
    }

    public void setAdministratorID(Long administratorID) {
        this.administratorID = administratorID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
    	if(username==null) {
    		throw new NullPointerException("username ne sme biti null");
    	}
    	if(username.equals("")) {
    		throw new IllegalArgumentException("username ne sme biti prazan string");
    	}
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
    	if(password==null) {
    		throw new NullPointerException("password ne sme biti null");
    	}
    	if(password.equals("")) {
    		throw new IllegalArgumentException("password ne sme biti prazan string");
    	}
        this.password = password;
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

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    
    
    
    
    
    

    

	

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Administrator other = (Administrator) obj;
		return Objects.equals(administratorID, other.administratorID) && Objects.equals(ime, other.ime)
				&& Objects.equals(password, other.password) && Objects.equals(prezime, other.prezime)
				&& Objects.equals(username, other.username);
	}

	@Override
    public String nazivTabele() {
        return " administrator ";
    }

    @Override
    public String alijas() {
        return " a ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<AbstractDomainObject>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            lista.add(a);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, Username, Password) ";
    }

    @Override
    public String uslov() {
        return " AdministratorID = " + administratorID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', "
                + "'" + username + "', '" + password + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Ime = '" + ime + "', Prezime = '" + prezime + "', "
                + "Username = '" + username + "', Password = '" + password + "' ";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

}

