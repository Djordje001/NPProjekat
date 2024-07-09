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
public class Autor extends AbstractDomainObject {

    private transient Knjiga knjiga;
    private int rb;
    private Pisac pisac;

    public Autor(Knjiga knjiga, int rb, Pisac pisac) {
      
        setKnjiga(knjiga);
   
        setRb(rb);
      
        setPisac(pisac);
    }

    

    @Override
    public String nazivTabele() {
        return " Autor ";
    }

    @Override
    public String alijas() {
        return " a ";
    }

    @Override
    public String join() {
        return " JOIN KNJIGA KNJ ON (KNJ.PROIZVODID = A.PROIZVODID) "
                + "JOIN PISAC P ON (P.PISACID = A.PISACID) "
        		+"JOIN PROIZVOD PR ON (PR.PROIZVODID = A.PROIZVODID)";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<AbstractDomainObject>();

        //public Knjiga(Long proizvodID, double cena, String naziv,int izdanje,String opis)
        while (rs.next()) {
        	

            Knjiga k = new Knjiga(rs.getLong("ProizvodID"),
                    rs.getDouble("cena"), rs.getString("naziv"),rs.getInt("tip"),
                    rs.getInt("izdanje"), rs.getString("opis"), null);

            Pisac p = new Pisac(rs.getLong("PisacID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Email"));

            Autor autor = new Autor(k, rs.getInt("rb"), p);

            lista.add(autor);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
    	return " (proizvodID,pisacID) ";
    }

    @Override
    public String uslov() {
        return " ProizvodID = " + knjiga.getProizvodID();
    }

    @Override
    public String vrednostiZaInsert() {
    	return knjiga.getProizvodID()+", "+pisac.getPisacID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslovZaSelect() {
    	//return " a.ProizvodID = " + knjiga.getProizvodID();
    	return "WHERE A.PROIZVODID = " + knjiga.getProizvodID();
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
    	if(knjiga==null) {
    		throw new NullPointerException("knjiga ne sme biti null");
    	}
        this.knjiga = knjiga;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
    	//nemam nedozvoljene vrednosti jer mi je rb autoincrement u bazi i samim tim ja ne rukovodim koju vrednostu unosim
        this.rb = rb;
    }

    public Pisac getPisac() {
        return pisac;
    }

    public void setPisac(Pisac pisac) {
    	if(pisac==null) {
    		throw new NullPointerException("Pisac ne sme biti null");
    	}
        this.pisac = pisac;
    }

	@Override
	public String toString() {
		return "Autor [ + , rb=" + rb + ", pisac=" + pisac.getPisacID() + "]";
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		return Objects.equals(knjiga, other.knjiga) && Objects.equals(pisac, other.pisac) && rb == other.rb;
	}
    
	
	
    
    

}
