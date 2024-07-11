package org.zajednickiP.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Predstavlja Kancelarijski proizvod sa atributima vrsta,proizvodjac,visina,sirina,duzina
 * nasledjuje klasu Proizvod
 * @author Lenovo
 *
 */
public class KancelarijskiProizvod extends Proizvod{
	/**
	 * vrsta Proizvoda kao String
	 */
	private String vrsta;
	
	/**
	 * proizvodjac proizvoda kao String
	 */
	private String proizvodjac;
	
	/**
	 * visina proizvoda kao double
	 */
	private double visina;
	
	/**
	 * sirina proizvoda kao double
	 */
	private double sirina;
	
	/**
	 * duzina proizvoda kao double
	 */
	private double duzina;
	
	

	/**
	 * postavlja atribute nadklase Proizvod na zeljene vrednosti i postavlja atribute ove klase na zeljene vrednost
	 * @param proizvodID id proizvoda kao Long
	 * @param cena proizvoda kao double
	 * @param naziv proizvoda kao String
	 * @param tip proizvoda kao Integer vrednost
	 * @param vrsta proizvoda kao String
	 * @param proizvodjac proizvoda kao String
	 * @param visina proizvoda kao double
	 * @param sirina proizvoda kao double
	 * @param duzina proizvoda kao duzina
	 * @throws java.lang.IllegalArgumentException ukoliko je prosledjen broj razlicit od 1,ili ako je prosledjena cena koja nije pozitivna,takodje vazi i za visinu,sirinu i duzinu
	 * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost za naziv,ili vrstu ili proizvodjaca
	 */
	public KancelarijskiProizvod(Long proizvodID, double cena, String naziv,int tip,String vrsta,String proizvodjac,double visina,double sirina,double duzina) {
		super(proizvodID, cena, naziv,tip);
		if(tip!=1) {
			throw new IllegalArgumentException("Za kancelarijskiProizvod je odredjen tip=1");
		}
		setVrsta(vrsta);
		
		setProizvodjac(proizvodjac);
		
		setVisina(visina);
		
		setSirina(sirina);
		
		setDuzina(duzina);
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * vraca trenutnu vrednost atributa vrsta 
	 * @return vrsta proizvoda kao string
	 */
	public String getVrsta() {
		return vrsta;
	}

	
	/**
	 * postavlja vrstu proizvoda na zeljenu vrednost
	 * @param vrsta proizvoda kao String
	 * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
	 * @throws java.lang.IllegalArgumentException ukoliko je prosledjen prazan string
	 */
	public void setVrsta(String vrsta) {
		if(vrsta==null) {
			throw new NullPointerException("vrsta ne sme biti null");
		}
		if(vrsta.equals("")) {
			throw new IllegalArgumentException("vrsta ne sme biti prazan string");
		}
		this.vrsta = vrsta;
	}

	
	/**
	 * vraca trenutnu vrednost atributa proizvodjac
	 * @return proizvodjac proizvoda kao String
	 */
	public String getProizvodjac() {
		return proizvodjac;
	}

	/**
	 * postavlja proizvodjaca proizvoda na zeljenu vrednost
	 * @param proizvodjac proizvoda kao String
	 * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
	 *@throws java.lang.IllegalArgumentException ukoliko je prosledjen prazan string
	 */
	public void setProizvodjac(String proizvodjac) {
		if(proizvodjac==null) {
			throw new NullPointerException("proizvodjac ne sme biti null");
		}
		if(proizvodjac.equals("")) {
			throw new IllegalArgumentException("proizvodjac ne sme biti prazan string");
		}
		this.proizvodjac = proizvodjac;
	}

	/**
	 * vraca trenutnu vrednost visine proizvoda
	 * @return visina proizvoda kao double
	 */
	public double getVisina() {
		return visina;
	}

	/**
	 * postavlja atribut visina proizvoda na zeljenu vrednost
	 * @param visina proizvoda kao double
	 * @throws java.lang.IllegalArgumentException ukoliko nije prosledjena pozitivna vrednost
	 */
	public void setVisina(double visina) {
		if(visina<=0) {
			throw new IllegalArgumentException("visina mora biti veca od 0");
		}
		this.visina = visina;
	}

	/**
	 * vraca trenutnu vrednost sirine proizvoda
	 * @return sirina proizvoda kao double
	 */
	public double getSirina() {
	
		return sirina;
	}

	/**
	 * postavlja atribut sirina proizvoda na zeljenu vrednost
	 * @param sirina proizvoda kao double
	 * @throws java.lang.IllegalArgumentException ukoliko nije prosledjena pozitivna vrednost
	 */
	public void setSirina(double sirina) {
		if(sirina<=0) {
			throw new IllegalArgumentException("sirina mora biti veca od 0");
		}
		this.sirina = sirina;
	}

	/**
	 * vraca trenutnu vrednost duzina proizvoda
	 * @return duzina proizvoda kao double
	 */
	public double getDuzina() {
		return duzina;
	}

	/**
	 * postavlja atribut duzina proizvoda na zeljenu vrednost
	 * @param duzina proizvoda kao double
	 * @throws java.lang.IllegalArgumentException ukoliko nije prosledjena pozitivna vrednost
	 */
	public void setDuzina(double duzina) {
		if(duzina<=0) {
			throw new IllegalArgumentException("duzina mora biti veca od 0");
		}
		this.duzina = duzina;
	}
	
	
	@Override
	public String nazivTabele() {
		return " KancelarijskiProizvod ";
	}


	@Override
	public String alijas() {
		return " kp ";
	}


	@Override
	public String join() {
		return " JOIN PROIZVOD P ON (P.PROIZVODID = KP.PROIZVODID) ";
	}


	@Override
	public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
		 ArrayList<AbstractDomainObject> lista = new ArrayList<AbstractDomainObject>();

	        while (rs.next()) {
	            KancelarijskiProizvod a = new KancelarijskiProizvod(rs.getLong("ProizvodID"),
	                    rs.getDouble("Cena"), rs.getString("Naziv"),rs.getInt("tip"),rs.getString("Vrsta"),rs.getString("Proizvodjac"),
	                    rs.getDouble("Visina"),rs.getDouble("Sirina"),rs.getDouble("Duzina")
	                    );

	            lista.add(a);
	        }
	       
	      

	        rs.close();
	        return lista;
	}


	@Override
	public String koloneZaInsert() {
		return " (proizvodID,vrsta,proizvodjac,visina,sirina,duzina) ";
	}


	@Override
	public String uslov() {
		return " proizvodID = " +getProizvodID();
	}


	@Override
	public String vrednostiZaInsert() {
		//return cena+", '"+naziv+"'";
		// return "'" + ime + "', '" + prezime + "', "
         //+ "'" + username + "', '" + password + "'";
		 return getProizvodID()+", '"+vrsta+"', '"+proizvodjac+"', "
				 +visina+", "+sirina+", "+duzina;
	}


	@Override
	public String vrednostiZaUpdate() {
		//return " Cena = "+cena+", Naziv = '"+naziv+"' ";
		  return " Vrsta = '" + vrsta + "', Proizvodjac = '" + proizvodjac + "', "
          + "Visina = " + visina + ", Sirina = " + sirina + ", "+"Duzina = "+duzina+" ";
	}


	@Override
	public String uslovZaSelect() {
		return "";
	}

	@Override
	public String toString() {
		return super.toString()+
				"KancelarijskiProizvod [ vrsta=" + vrsta + ", proizvodjac=" + proizvodjac + ", visina=" + visina
				+ ", sirina=" + sirina + ", duzina=" + duzina + "]";
	}

	

	/**
	 * poredi dva kancelarijska proizvoda
	 * 
	 * poredjenje se radi prema svim atributima
	 * 
	 * @param obj KancelarijskiProizvod sa kojim se poredi
	 * @return true ukoliko drugi par ima sve iste atribute
	 * @return false u svim ostalim situacijama.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KancelarijskiProizvod other = (KancelarijskiProizvod) obj;
		return super.equals(obj) &&
				
				 Objects.equals(proizvodjac, other.proizvodjac)
				
				&& Objects.equals(vrsta, other.vrsta);
	}

	
	
	
	
	
	
}
