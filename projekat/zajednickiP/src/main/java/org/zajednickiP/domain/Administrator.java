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
 *  Predstavlja administratora koji ima svoje ime,prezime,username,password
 * @author Lenovo
 */
public class Administrator extends AbstractDomainObject {

	/**
	 * predstavlja jedinstveni identifikator administratora kao long
	 */
    private Long administratorID;
    /**
     * predstavlja ime konkretnog administratora kao string
     */
    private String ime;
    
    /**
     * predstavlja prezime konkretnog administratora kao string
     */
    private String prezime;
    
    /**
     * predstavlja username konkretnog administratora kao string
     */
    private String username;
    
    /**
     * predstavlja password konkretnog administratora kao string
     */
    private String password;

    

    /**
     * Postavlja zeljene vrednosti atributima Administratora
     * @param administratorID id administratora kao Long
     * @param ime  administratora kao String
     * @param prezime administratora kao String
     * @param username administratora kao String
     * @param password administratora kao String
     * @throws java.lang.NullPointerException -ukoliko se prosledi null vrednost za ime ili prezime ili username ili password
     * @throws java.lang.IllegalArgumentException -ukoliko se prosledi prazan string za ime ili prezime ili username i password
     */
    public Administrator(Long administratorID, String ime, String prezime, String username, String password) {
       
        setAdministratorID(administratorID);
        
        setIme(ime);
        
        setPrezime(prezime);
       
        setUsername(username);
      
        setPassword(password);
    }

    /**
     * vraca trenutnu vrednost atributa administratorID
     * @return administratorID kao long
     */
    public Long getAdministratorID() {
        return administratorID;
    }

    /**
     * postavlja administratorID na zeljenu vrednost
     * @param administratorID zeljena vrednost id-a kao Long
     */
    public void setAdministratorID(Long administratorID) {
        this.administratorID = administratorID;
    }

    /**
     * vraca trenutnu vrednost atributa username
     * @return username administratora kao String
     */
    public String getUsername() {
        return username;
    }

    /**
     * postavlja atribut username na zeljenu vrednost
     * @param username administratora kao String
     * @throws java.lang.NullPointerException ukoliko je poslata null vrednost
     * @throws java.lang.IllegalArgumentException ukoliko je poslat prazan string
     */
    public void setUsername(String username) {
    	if(username==null) {
    		throw new NullPointerException("username ne sme biti null");
    	}
    	if(username.equals("")) {
    		throw new IllegalArgumentException("username ne sme biti prazan string");
    	}
        this.username = username;
    }

    /**
     * vraca trenutnu vrednost za password administratora
     * @return password administratora kao String
     */
    public String getPassword() {
        return password;
    }

    /**
     * postavlja password administratora na zeljenu vrednost
     * @param password zeljeni kao string
     * @throws java.lang.NullPointerException ako je poslata null vrednost
     * @throws java.lang.IllegalArgumentException ukoliko je poslat prazan string
     */
    public void setPassword(String password) {
    	if(password==null) {
    		throw new NullPointerException("password ne sme biti null");
    	}
    	if(password.equals("")) {
    		throw new IllegalArgumentException("password ne sme biti prazan string");
    	}
        this.password = password;
    }

    /**
     * vraca trenutnu vrednost atributa ime administratora
     * @return ime administratora kao String
     */
    public String getIme() {
        return ime;
    }

    /**
     * postavlja zeljenu vrednost za atribut ime administratora
     * @param ime administratora kao string
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     * @throws java.lang.IllegalArgumentException ukoliko je prosledjen prazan string
     *
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
     * vraca trenutnu vrednost atributa prezime administratora
     * @return prezime administratora kao String
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * postavlja zeljenu vrednost atributa prezime administratora
     * @param prezime administratora kao String
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

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    
    
    
    
    
    

    

	

	/**
	 * Poredi dva administratora
	 * 
	 * Poredjenje se radi prema svim atributima
	 * @param obj Administrator sa kojim se poredi
	 * @return true- ako drugi administrator ima sve iste atribute kao prvi
	 * @return false-u svim drugim situacijama
	 */
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

