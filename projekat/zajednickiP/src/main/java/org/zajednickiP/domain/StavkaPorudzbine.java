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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Lenovo
 */
public class StavkaPorudzbine extends AbstractDomainObject {

	
    private  Porudzbina porudzbina;
    private int rb;
    private int kolicina;
    private double cena;
    private Proizvod proizvod;

    public StavkaPorudzbine(Porudzbina porudzbina, int rb, int kolicina, double cena, Proizvod proizvod) {
       
        setPorudzbina(porudzbina);
       
        setRb(rb);
        
        setKolicina(kolicina);
      
        setCena(cena);
      
        setProizvod(proizvod);
    }

    

    @Override
    public String nazivTabele() {
        return " StavkaPorudzbine ";
    }

    @Override
    public String alijas() {
        return " sp ";
    }

    @Override
    public String join() {
        return " JOIN PROIZVOD PR ON (PR.PROIZVODID = SP.PROIZVODID) "
                + "JOIN PORUDZBINA P ON (P.PORUDZBINAID = SP.PORUDZBINAID) "
                + "JOIN KUPAC K ON (K.KUPACID = P.KUPACID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = P.ADMINISTRATORID) "
                +"LEFT JOIN KANCELARIJSKIPROIZVOD KM ON (PR.TIP = 1 AND KM.PROIZVODID = SP.PROIZVODID) "
                +"LEFT JOIN KNJIGA KJ ON (PR.TIP = 2 AND KJ.PROIZVODID = SP.PROIZVODID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<AbstractDomainObject>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("a.Ime"), rs.getString("a.Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            Kupac k = new Kupac(rs.getLong("KupacID"),
                    rs.getString("k.Ime"), rs.getString("k.Prezime"),
                    rs.getString("email"), rs.getString("tipKupca"));

            Porudzbina p = new Porudzbina(rs.getLong("porudzbinaID"),
                    rs.getTimestamp("datumVreme"), rs.getDate("datumIsporuke"),
                    rs.getString("grad"), rs.getString("adresa"),
                    rs.getDouble("cena"), rs.getDouble("popust"),
                    rs.getDouble("konacnaCena"), k, a, new ArrayList<>());
            
            //public Knjiga(Long proizvodID, double cena, String naziv,int tip,int izdanje,String opis,ArrayList<Autor> autori)
          //  public KancelarijskiProizvod(Long proizvodID, double cena, String naziv,int tip,String vrsta,String proizvodjac,double visina,double sirina,double duzina)
           
            Proizvod proizvod=new Proizvod(null, 2000,"pravim ovako samo zato jer nemam bezparam konstruktor",1);
            StavkaPorudzbine sp=new StavkaPorudzbine(p, 0, 2, 2500,proizvod);
            if(rs.getInt("tip")==1) {
            	KancelarijskiProizvod kp=new KancelarijskiProizvod(rs.getLong("proizvodID"), rs.getDouble("cena"), rs.getString("naziv"),
            			rs.getInt("tip"), rs.getString("vrsta"), rs.getString("proizvodjac"), rs.getDouble("visina"),rs.getDouble("sirina"), rs.getDouble("duzina"));
            	sp=new StavkaPorudzbine(p, rs.getInt("rb"), rs.getInt("kolicina"), rs.getDouble("cena"), kp);
            }else {
            	 Knjiga knj = new Knjiga(rs.getLong("proizvodID"),
                         rs.getDouble("cena"), rs.getString("naziv"),
                         rs.getInt("tip"), rs.getInt("izdanje"),rs.getString("opis"), new ArrayList<>());
            	 sp = new StavkaPorudzbine(p, rs.getInt("rb"), 
                         rs.getInt("kolicina"), rs.getDouble("cena"), knj);
            }
            
            lista.add(sp);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (porudzbinaID, kolicina, cena, proizvodID) ";
    }

    @Override
    public String uslov() {
        return " porudzbinaID = " + porudzbina.getPorudzbinaID();
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + porudzbina.getPorudzbinaID() + ", "
                + " " + kolicina + ", " + cena + ", " + proizvod.getProizvodID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslovZaSelect() {
        return " WHERE P.PORUDZBINAID = " + porudzbina.getPorudzbinaID();
    }

    public Porudzbina getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(Porudzbina porudzbina) {
    	if(porudzbina==null) {
    		throw new NullPointerException("porudzbina ne sme biti null");
    	}
        this.porudzbina = porudzbina;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
    	//nemam proveru vrednosti jer mi je rb autoincrement u bazi znaci ja ne zadajem vrednost
        this.rb = rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
    	if(kolicina<=0) {
    		throw new IllegalArgumentException("kolicina mora biti pozitivna");
    	}
        this.kolicina = kolicina;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
    	if(cena<=0) {
    		throw new IllegalArgumentException("cena mora biti pozitivna");
    	}
        this.cena = cena;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
    	if(proizvod==null) {
    		throw new IllegalArgumentException("proizvod ne sme biit null");
    	}
        this.proizvod = proizvod;
    }

	@Override
	public String toString() {
		return "StavkaPorudzbine [porudzbina=" + porudzbina + ", rb=" + rb + ", kolicina=" + kolicina + ", cena=" + cena
				+ ", proizvod=" + proizvod + "]";
	}



	



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StavkaPorudzbine other = (StavkaPorudzbine) obj;
		return Double.doubleToLongBits(cena) == Double.doubleToLongBits(other.cena) && kolicina == other.kolicina
				&& Objects.equals(porudzbina, other.porudzbina) && Objects.equals(proizvod, other.proizvod)
				&& rb == other.rb;
	}
    
	
	
	
    
    
    

}
