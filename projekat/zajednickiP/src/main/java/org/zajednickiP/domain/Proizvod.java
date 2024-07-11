package org.zajednickiP.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * predstavlja proizvod sa atributima id,cena,naziv,tip
 * @author Lenovo
 *
 */
public class Proizvod extends AbstractDomainObject {
	/**
	 * id konkretnog proizvoda kao Long
	 */
	private Long proizvodID;
	/**
	 * cena proizvoda kao double
	 */
	private double cena;
	
	/**
	 * naziv proizvoda kao String
	 */
	private String naziv;
	
	/**
	 * tip proizvoda kao integer vrednosti sa 2 moguce vrednosti:1 za kancelarijskiMaterijal,2 za knjigu
	 */
	private int tip;
	
	
	
	
	/**
	 * postavlja atribute Proizvoda na zeljene vrednosti
	 * @param proizvodID id proizvoda
	 * @param cena proizvoda
	 * @param naziv proizvoda
	 * @param tip proizvoda
	 * @throws java.lang.NullPointerException ukoliko se prosledi null vrednost za naziv
	 * @throws java.lang.IllegalArgumentException ukoliko se ne prosledi pozitivna vrednost za cenu ili se prosledi prazan string za naziv ili se za tip ne prosledi 1 ili 2
	 */
	public Proizvod(Long proizvodID, double cena, String naziv,int tip) {
		super();
		if(tip!=1 && tip!=2) {
			throw new IllegalArgumentException("tip mora biti 1 ili 2");
		}
		setProizvodID(proizvodID);
	
		setCena(cena);
		
		setNaziv(naziv);
		
		setTip(tip);
	
	}
	/**
	 * vraca trenutnu vrednost proizvodID-a
	 * @return proizvodID kao Long
	 */
	public Long getProizvodID() {
		return proizvodID;
	}
	
	/**
	 * postavlja proizvodID na zeljenu vrednost
	 * @param proizvodID proizvoda
	 */
	public void setProizvodID(Long proizvodID) {
		this.proizvodID = proizvodID;
	}
	
	/**
	 * vraca trenutnu cenu proizvoda
	 * @return cena proizvoda
	 */
	public double getCena() {
		return cena;
	}
	
	/**
	 * postavlja atribut cenu na zeljenu vrednost
	 * @param cena proizvoda
	 * @throws java.lang.IllegalArgumentException ukoliko je prosledjen broj koji nije pozitivan
	 */
	public void setCena(double cena) {
		if(cena<=0) {
			throw new IllegalArgumentException("cena mora biti pozitivna");
		}
		this.cena = cena;
	}
	
	/**
	 * vraca trenutnu vrednost naziva Proizvoda
	 * @return naziv proizvoda
	 */
	public String getNaziv() {
		return naziv;
	}
	
	/**
	 * postavlja atribut naziv na zeljenu vrednost
	 * @param naziv proizvoda kao String
	 * @throws java.lang.NullPointerException ukoliko je prosledjena null vrednost
	 * @throws java.lang.IllegalArgumentException ukoliko je prosledjene prazan string
	 */
	public void setNaziv(String naziv) {
		if(naziv==null) {
    		throw new NullPointerException("naziv ne sme biti null");
    	}
    	if(naziv.equals("")) {
    		throw new IllegalArgumentException("naziv ne sme biti prazan string");
    	}
		this.naziv = naziv;
	}
	
	/**
	 * vraca trenutnu vrednost tipa proizvoda
	 * @return tip proizvoda kao integer
	 */
	public int getTip() {
		return tip;
	}
	
	/**
	 * postavlja tip na zeljenu vrednost
	 * @param tip proizvoda kao integer
	 * @throws java.lang.IllegalArgumentException ukoliko je prosledjen broj razlicit od 1 ili 2
	 */
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
	
	/**
	 * poredi dva proizvoda po svim atributima
	 * @param obj Proizvod sa kojim se poredi.
	 * @return true -ako je drugi proizvod ima sve iste atribute
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
		Proizvod other = (Proizvod) obj;
		return  Objects.equals(proizvodID, other.proizvodID);
				
	}

	

}
