package org.zajednickiP.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class KancelarijskiProizvod extends Proizvod{
	private String vrsta;
	private String proizvodjac;
	private double visina;
	private double sirina;
	private double duzina;
	
	

	public KancelarijskiProizvod(Long proizvodID, double cena, String naziv,int tip,String vrsta,String proizvodjac,double visina,double sirina,double duzina) {
		super(proizvodID, cena, naziv,tip);
		setVrsta(vrsta);
		
		setProizvodjac(proizvodjac);
		
		setVisina(visina);
		
		setSirina(sirina);
		
		setDuzina(duzina);
		// TODO Auto-generated constructor stub
	}

	public String getVrsta() {
		return vrsta;
	}

	public void setVrsta(String vrsta) {
		if(vrsta==null) {
			throw new NullPointerException("vrsta ne sme biti null");
		}
		if(vrsta.equals("")) {
			throw new IllegalArgumentException("vrsta ne sme biti prazan string");
		}
		this.vrsta = vrsta;
	}

	public String getProizvodjac() {
		return proizvodjac;
	}

	public void setProizvodjac(String proizvodjac) {
		if(proizvodjac==null) {
			throw new NullPointerException("proizvodjac ne sme biti null");
		}
		if(proizvodjac.equals("")) {
			throw new IllegalArgumentException("proizvodjac ne sme biti prazan string");
		}
		this.proizvodjac = proizvodjac;
	}

	public double getVisina() {
		return visina;
	}

	public void setVisina(double visina) {
		if(visina<=0) {
			throw new IllegalArgumentException("visina mora biti veca od 0");
		}
		this.visina = visina;
	}

	public double getSirina() {
	
		return sirina;
	}

	public void setSirina(double sirina) {
		if(sirina<=0) {
			throw new IllegalArgumentException("sirina mora biti veca od 0");
		}
		this.sirina = sirina;
	}

	public double getDuzina() {
		return duzina;
	}

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
				Double.doubleToLongBits(duzina) == Double.doubleToLongBits(other.duzina)
				&& Objects.equals(proizvodjac, other.proizvodjac)
				&& Double.doubleToLongBits(sirina) == Double.doubleToLongBits(other.sirina)
				&& Double.doubleToLongBits(visina) == Double.doubleToLongBits(other.visina)
				&& Objects.equals(vrsta, other.vrsta);
	}

	
	
	
	
	
	
}
