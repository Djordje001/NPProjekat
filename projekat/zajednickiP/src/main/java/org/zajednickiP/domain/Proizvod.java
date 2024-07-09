package org.zajednickiP.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Proizvod extends AbstractDomainObject {
	private Long proizvodID;
	private double cena;
	private String naziv;
	private int tip;
	
	
	
	
	
	public Proizvod(Long proizvodID, double cena, String naziv,int tip) {
		super();
		setProizvodID(proizvodID);
	
		setCena(cena);
		
		setNaziv(naziv);
		
		setTip(tip);
	
	}
	public Long getProizvodID() {
		return proizvodID;
	}
	public void setProizvodID(Long proizvodID) {
		this.proizvodID = proizvodID;
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
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		if(naziv==null) {
    		throw new NullPointerException("naziv ne sme biti null");
    	}
    	if(naziv.equals("")) {
    		throw new IllegalArgumentException("naziv ne sme biti prazan string");
    	}
		this.naziv = naziv;
	}
	public int getTip() {
		return tip;
	}
	public void setTip(int tip) {
		//tip=1 ako je kancelarijskiProizvod,tip=2 ako je knjiga
		if(tip!=1 && tip!=2) {
			throw new IllegalArgumentException("tip mora biti 1 ili 2 ");
		}
		this.tip = tip;
	}


	@Override
	public String nazivTabele() {
		return " Proizvod ";
	}


	@Override
	public String alijas() {
		return " p ";
	}


	@Override
	public String join() {
		return 
                " LEFT JOIN KANCELARIJSKIPROIZVOD KM ON (P.TIP = 1 AND KM.PROIZVODID = P.PROIZVODID) "
                +"LEFT JOIN KNJIGA KJ ON (P.TIP = 2 AND KJ.PROIZVODID = P.PROIZVODID) ";
	}


	@Override
	public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
		 ArrayList<AbstractDomainObject> lista = new ArrayList<AbstractDomainObject>();

	        while (rs.next()) {
	        	Proizvod a;
	        	if(rs.getInt("tip")==1) {
	        		//public KancelarijskiProizvod(Long proizvodID, double cena, String naziv,int tip,String vrsta,String proizvodjac,double visina,double sirina,double duzina
	        		a=new KancelarijskiProizvod(rs.getLong("ProizvodID"), rs.getDouble("Cena"), rs.getString("Naziv"),rs.getInt("Tip"), rs.getString("Vrsta"), rs.getString("Proizvodjac"), rs.getDouble("Visina"), rs.getDouble("Sirina"), rs.getDouble("Duzina"));
	        	}else {
	        		//public Knjiga(Long proizvodID, double cena, String naziv,int tip,int izdanje,String opis,ArrayList<Autor> autori)
	        	   a=new Knjiga(rs.getLong("ProizvodID"),rs.getDouble("cena"),rs.getString("Naziv"),rs.getInt("Tip"),rs.getInt("Izdanje"),rs.getString("Opis"),new ArrayList<>());
	        	}
	            /*Proizvod a = new Proizvod(rs.getLong("ProizvodID"),
	                    rs.getDouble("Cena"), rs.getString("Naziv"),rs.getInt("Tip")
	                    );*/

	            lista.add(a);
	        }

	        rs.close();
	        return lista;
	}


	@Override
	public String koloneZaInsert() {
		return " (cena,naziv,tip) ";
	}


	@Override
	public String uslov() {
		return " proizvodID = " +proizvodID;
	}


	@Override
	public String vrednostiZaInsert() {
		return cena+", '"+naziv+"', "+tip;
	}


	@Override
	public String vrednostiZaUpdate() {
		return " Cena = "+cena+", Naziv = '"+naziv+"', Tip = "+tip+" ";
		//return " Cena = "+cena+", Naziv = '"+naziv+"' ";
		  //return " Vrsta = '" + vrsta + "', Proizvodjac = '" + proizvodjac + "', "
        //+ "Visina = " + visina + ", Sirina = " + sirina + ", "+"Duzina = "+duzina+" ";
	}


	@Override
	public String uslovZaSelect() {
		//return "";
		return "WHERE P.NAZIV = '" + naziv + "'";
	}


	@Override
	public String toString() {
		return "Proizvod [proizvodID=" + proizvodID + ", cena=" + cena + ", naziv=" + naziv + ", tip=" + tip + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proizvod other = (Proizvod) obj;
		return Double.doubleToLongBits(cena) == Double.doubleToLongBits(other.cena)
				&& Objects.equals(naziv, other.naziv) && Objects.equals(proizvodID, other.proizvodID)
				&& tip == other.tip;
	}

	

}
