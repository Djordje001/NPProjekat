/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napredno.programiranje.zajednickiP.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;



/**
 *predstavlja autora konkretne knjige i ima podatke konkretnog pisca
 * @author Djordje Djordjevic
 */
public class Autor extends AbstractDomainObject {

	/**
	 * Knjiga kojoj pripada konkretni autor
	 */
    private Knjiga knjiga;
    
    /**
     * redni broj konkretnog autora kao integer vrednost
     */
    private int rb;
    
    /**
     * Pisac knjige cije podatke uzima konkretni autor
     */
    private Pisac pisac;

    /**
     * postavlja zeljene vrednosti svim atributima autora 
     * @param knjiga kojoj pripada autor
     * @param rb redni broj autora
     * @param pisac knjige
     * @throws java.lang.NullPointerException ukoliko se prosledi null za knjigu ili pisca
     */
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
                    rs.getInt("izdanje"), rs.getString("opis"), new ArrayList<>());

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

    /**
     * Vraca kojoj knjizi pripada konkretni autor
     * @return knjiga autora
     */
    public Knjiga getKnjiga() {
        return knjiga;
    }

    /**
     * postavlja knjigu kojoj pripada konkretni autor
     * @param knjiga autora
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     */
    public void setKnjiga(Knjiga knjiga) {
    	if(knjiga==null) {
    		throw new NullPointerException("knjiga ne sme biti null");
    	}
        this.knjiga = knjiga;
    }

    /**
     * vraca trenutnu vrednost rednog broja autora 
     * @return rb redni broj autora kao int
     */
    public int getRb() {
        return rb;
    }

    /**
     * postavlja atribut rb na zeljenu vrednost 
     * @param rb redni broj autora kao int
     */
    public void setRb(int rb) {
    	//nemam nedozvoljene vrednosti jer mi je rb autoincrement u bazi i samim tim ja ne rukovodim koju vrednostu unosim
        this.rb = rb;
    }

    /**
     * vraca pisca kome pripada konkretni autor knjige
     * @return pisac 
     */
    public Pisac getPisac() {
        return pisac;
    }

    /**
     * Postavlja pisca na zeljenu vrednost
     * @param pisac kome pripada konkretni autor
     * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
     */
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

	

	/**
	 * poredi dva autora po svim atributima
	 * @param obj Autor sa kojim se poredi
	 * @return true-ako drugi autor ima sve iste atribute 
	 * @return false-u svim ostalim situacijama
	 */
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
